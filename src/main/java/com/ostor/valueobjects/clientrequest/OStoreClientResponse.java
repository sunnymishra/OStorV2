package com.ostor.valueobjects.clientrequest;

import javax.ws.rs.core.Response.Status;

public class OStoreClientResponse<T> {
	private Status status;
	private T response = null;
	
	public OStoreClientResponse() {}

	public OStoreClientResponse(Status status, T response) {
		super();
		this.status = status;
		this.response = response;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	
	
}
