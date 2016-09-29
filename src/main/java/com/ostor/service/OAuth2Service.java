package com.ostor.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ostor.client.OAuth2Client;
import com.ostor.datastore.OStorDatastore;
import com.ostor.datastore.impl.DummyOStorDatastore;
import com.ostor.util.Constants;
import com.ostor.valueobjects.OAuth2TokenResponse;
import com.ostor.valueobjects.OStorAuthInfo;
 
/**
 * Root resource (exposed at "file" path)
 */
@Path("/")
public class OAuth2Service {
	private Properties genericProperties = Constants.getGenericProperties();
	// Consider changing this hardcoded state to some dynamic util method generate code, which should be uniquely able to identiy 
	// the request sent to Dropbox /oauth2/authorize with response coming from Dropbox /oauth2/authorize
	private String dropboxAuthorizeState="1qaz2wsx";
	private OAuth2Client oAuth2Client = new OAuth2Client();
	
    @GET
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public String login() {
    	URI uri = null;
    	try {
    	    uri = new URI(genericProperties.getProperty("dropbox.authorize.url")
    	    			.concat("?")
	    	    		.concat("response_type" + "=" + genericProperties.getProperty("dropbox.authorize.responsetype"))
	    	    		.concat("&client_id" + "=" + genericProperties.getProperty("dropbox.appkey"))
	    	    		.concat("&redirect_uri" + "=" + genericProperties.getProperty("dropbox.authorize.callbackurl"))
	    	    		.concat("&state" + "=" + dropboxAuthorizeState));
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}

    	Response response = Response.seeOther(uri).build();
    	throw new WebApplicationException(response);
    }

    @GET
    @Path("oauth2callback")
    @Produces(MediaType.TEXT_PLAIN)
    public String callback(@QueryParam("code") String code, @QueryParam("state") String state) throws Exception {
    	if(!dropboxAuthorizeState.equals(state))
    		throw new Exception("Authorization response hijacked");
    	// Below we are making a API client call
    	OAuth2TokenResponse tokenResponse = oAuth2Client.getToken(code);
    	// Below we are storing the API response into our datastore
    	OStorDatastore datastore = DummyOStorDatastore.getInstance();
    	datastore.setAuthInfo(new OStorAuthInfo(tokenResponse));
    	// Below we are returning the API JSON response as string
    	ObjectMapper objectMapper = new ObjectMapper();
    	String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tokenResponse);
    	return result;
    }
}
