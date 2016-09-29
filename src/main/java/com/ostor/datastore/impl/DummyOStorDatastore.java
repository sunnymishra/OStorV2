package com.ostor.datastore.impl;

import java.util.HashMap;
import java.util.Map;

import com.ostor.datastore.OStorDatastore;
import com.ostor.valueobjects.OStorAuthInfo;

public class DummyOStorDatastore implements OStorDatastore{
	private Map<String, OStorAuthInfo> authInfoMap = new HashMap<>();
	  
	  private DummyOStorDatastore() {}
	  
	  public static OStorDatastore instance = new DummyOStorDatastore();
	  
	  public static OStorDatastore getInstance(){
		  return instance;
	  }
	  
	  public OStorAuthInfo getAuthInfo(String accountId){
	    return authInfoMap.get(accountId);
	  }
	  
	  public void setAuthInfo(OStorAuthInfo newAuthInfo){
		  OStorAuthInfo storedAuthInfo = authInfoMap.get(newAuthInfo.getAccountId());
		  if(null!=storedAuthInfo){
			  storedAuthInfo=newAuthInfo;
		  }else{
			  authInfoMap.put(newAuthInfo.getAccountId(), newAuthInfo);
		  }
	  }
}
