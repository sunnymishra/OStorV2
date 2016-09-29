package com.ostor.valueobjects;

public class OStorAuthInfo {
	private String accountId;
	private String authToken;
	private String teamId;
	
	public OStorAuthInfo(OAuth2TokenResponse oauth2Response) {
		super();
		if(oauth2Response==null)
			return;
		this.accountId = oauth2Response.getAccount_id();
		this.authToken =  oauth2Response.getAccess_token();
		this.teamId =  oauth2Response.getTeam_id();
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	
}
