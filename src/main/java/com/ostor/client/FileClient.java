package com.ostor.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.ostor.util.Constants;
import com.ostor.util.Constants.OperationType;
import com.ostor.valueobjects.clientrequest.CreateFolderArg;
import com.ostor.valueobjects.clientrequest.FileMetadata;
import com.ostor.valueobjects.clientrequest.FolderMetadata;
import com.ostor.valueobjects.clientrequest.ListFolderRequest;
import com.ostor.valueobjects.clientrequest.ListFolderResult;
import com.ostor.valueobjects.clientrequest.OStoreClientResponse;

public class FileClient {
	WebTarget apiService = null;
	WebTarget contentService = null;
	
	private Properties genericProperties = Constants.getGenericProperties();
	public static FileClient INSTANCE = new FileClient();
	
	private FileClient() {
		ClientConfig config = new ClientConfig();
		
		Client apiClient = ClientBuilder.newClient(config);
		this.apiService = apiClient.target(getBaseURI(OperationType.API)).path("2").path("files");
		
		Client contentClient = ClientBuilder.newClient(config);
		this.contentService = contentClient.target(getBaseURI(OperationType.CONTENT)).path("2").path("files");
	}

	/*
	 * Below Client call gets Auth token from Dropbox for 
	 * given dropbox OAuth2 code, during OAuth2 Authorization code flow
	 */
	public ListFolderResult listFolder(String authToken, ListFolderRequest request) {
//		request.setInclude_deleted(false);
		// TODO set other request parameters too
		
        Response response = apiService.path("list_folder").request(MediaType.APPLICATION_JSON_TYPE)
        		.header("Authorization", "Bearer ".concat(authToken))
				.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), Response.class);
		if(response.getStatus()==200){
			return response.readEntity(ListFolderResult.class);
		}
		return null;
	}

	/*
	 * Below Client call Creates new Folder in Dropbox
	 */
	public OStoreClientResponse<FolderMetadata> createFolder(String authToken, CreateFolderArg request) {
//		request.setInclude_deleted(false);
		// TODO set other request parameters too
		
        Response response = apiService.path("create_folder").request(MediaType.APPLICATION_JSON_TYPE)
        		.header("Authorization", "Bearer ".concat(authToken))
				.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), Response.class);
        OStoreClientResponse<FolderMetadata> finalResponse = new OStoreClientResponse<FolderMetadata>();
        finalResponse.setStatus(Status.fromStatusCode(response.getStatus()));
		if(response.getStatus()==200){
			finalResponse.setResponse(response.readEntity(FolderMetadata.class));
		}
		return finalResponse;
	}
	
	/*
	 * Below Client call Uploads files as InputStream in Dropbox while passing file metadata as Post request Header
	 */
	public OStoreClientResponse<FileMetadata> upload(String authToken, String inputFileMetadata, InputStream uploadFIS) {
        Response response = contentService.path("upload").request(MediaType.APPLICATION_JSON_TYPE)
        		.header("Authorization", "Bearer ".concat(authToken))
        		.header("Dropbox-API-Arg", "{\"path\":\""+ inputFileMetadata + "\"}")
				.post(Entity.entity(uploadFIS, MediaType.APPLICATION_OCTET_STREAM_TYPE), Response.class);
        
        OStoreClientResponse<FileMetadata> finalResponse = new OStoreClientResponse<FileMetadata>();
        finalResponse.setStatus(Status.fromStatusCode(response.getStatus()));
		if(response.getStatus()==200){
			finalResponse.setResponse(response.readEntity(FileMetadata.class));
		}
		
		try {
			uploadFIS.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalResponse;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private URI getBaseURI(OperationType operationType) {
		URI uri=null;
		switch(operationType){
			case API : {
				uri=UriBuilder.fromUri(genericProperties.getProperty("dropbox.api.baseuri")).build();
				break;
			}
			case CONTENT : {
				uri=UriBuilder.fromUri(genericProperties.getProperty("dropbox.content.baseuri")).build();
				break;
			}
		}
		return uri;
	}
	

}
