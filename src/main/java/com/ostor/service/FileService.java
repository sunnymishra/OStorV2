package com.ostor.service;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ostor.client.FileClient;
import com.ostor.util.Util;
import com.ostor.valueobjects.clientrequest.CreateFolderArg;
import com.ostor.valueobjects.clientrequest.FileMetadata;
import com.ostor.valueobjects.clientrequest.FolderMetadata;
import com.ostor.valueobjects.clientrequest.ListFolderRequest;
import com.ostor.valueobjects.clientrequest.ListFolderResult;
import com.ostor.valueobjects.clientrequest.OStoreClientResponse;
 
/**
 * Root resource (exposed at "file" path)
 */
@Path("file")
public class FileService {
//	private FileClient fileClient = new FileClient();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @GET
    @Path("list_folder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFolder(@HeaderParam("Authorization") String authToken, ListFolderRequest request){
    	authToken = Util.processAuthorization(authToken);
    	if(authToken==null)
    		return Response.status(Status.UNAUTHORIZED).build();
    	// Below we are making a API client call
    	ListFolderResult response = FileClient.INSTANCE.listFolder(authToken, request);
    	// Below we are returning the API JSON response as string
    	ObjectMapper objectMapper = new ObjectMapper();
    	String result=null;
		try {
			result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("create_folder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFolder(@HeaderParam("Authorization") String authToken, CreateFolderArg arguments) throws Exception {
    	authToken = Util.processAuthorization(authToken);
    	if(authToken==null)
    		return Response.status(Status.UNAUTHORIZED).build();
    	// Below we are making a API client call
    	OStoreClientResponse<FolderMetadata> response = FileClient.INSTANCE.createFolder(authToken, arguments);
    	// Below we are returning the API JSON response as string
    	Response finalResponse=null;
    	if(response.getStatus().equals(Status.OK))
    		finalResponse = Response.ok(response, MediaType.APPLICATION_JSON).build();
    	else
    		finalResponse = Response.status(response.getStatus()).build();
    	return finalResponse;
    }
    
    @POST
    @Path("upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@HeaderParam("Authorization") String authToken, @HeaderParam("Dropbox-API-Arg") String inputFileMetadata, InputStream is) {
    	authToken = Util.processAuthorization(authToken);
    	if(authToken==null)
    		return Response.status(Status.UNAUTHORIZED).build();
    	// Below we are making a API client call
    	OStoreClientResponse<FileMetadata> response = FileClient.INSTANCE.upload(authToken, inputFileMetadata, is);
    	// Below we are returning the API JSON response as string
    	Response finalResponse=null;
    	if(response.getStatus().equals(Status.OK))
    		finalResponse = Response.ok(response, MediaType.APPLICATION_JSON).build();
    	else
    		finalResponse = Response.status(response.getStatus()).build();
    	return finalResponse;
    }
    
    
    
    
    
    
    
    
    
    

}
