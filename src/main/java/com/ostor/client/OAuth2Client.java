package com.ostor.client;

import java.net.URI;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.ostor.util.Constants;
import com.ostor.valueobjects.OAuth2TokenResponse;

public class OAuth2Client {
	WebTarget service = null;
	private Properties genericProperties = Constants.getGenericProperties();
	
	public OAuth2Client() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		this.service = client.target(getBaseURI());
	}

	/*
	 * Below CLient call gets Auth token from Dropbox for 
	 * given dropbox OAuth2 code, during OAuth2 Authorization code flow
	 */
	public OAuth2TokenResponse getToken(String code) {
		Form form = new Form();
        form.param("client_id", genericProperties.getProperty("dropbox.appkey"));
        form.param("client_secret", genericProperties.getProperty("dropbox.appsecret"));
        form.param("code", code);
        form.param("grant_type", "authorization_code");
        form.param("redirect_uri", genericProperties.getProperty("dropbox.authorize.callbackurl"));
        
        Response response = service.path("oauth2").path("token").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.form(form), Response.class);
		if(response.getStatus()==200){
			return response.readEntity(OAuth2TokenResponse.class);
		}
		return null;
				
	}

	private URI getBaseURI() {
		return UriBuilder.fromUri(genericProperties.getProperty("dropbox.api.baseuri")).build();
	}

}
