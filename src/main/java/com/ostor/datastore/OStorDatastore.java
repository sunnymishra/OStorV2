package com.ostor.datastore;

import com.ostor.valueobjects.OStorAuthInfo;

public interface OStorDatastore {
	public OStorAuthInfo getAuthInfo(String accountId);

	public void setAuthInfo(OStorAuthInfo newAuthInfo);
}
