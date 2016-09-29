package com.ostor.valueobjects.clientrequest;

public class ListFolderRequest {
	private String path;
	private boolean recursive=false;
	private boolean include_media_info=false;
	private boolean include_deleted=false;
	private boolean include_has_explicit_shared_members=false;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	public boolean isInclude_media_info() {
		return include_media_info;
	}

	public void setInclude_media_info(boolean include_media_info) {
		this.include_media_info = include_media_info;
	}

	public boolean isInclude_deleted() {
		return include_deleted;
	}

	public void setInclude_deleted(boolean include_deleted) {
		this.include_deleted = include_deleted;
	}

	public boolean isInclude_has_explicit_shared_members() {
		return include_has_explicit_shared_members;
	}

	public void setInclude_has_explicit_shared_members(boolean include_has_explicit_shared_members) {
		this.include_has_explicit_shared_members = include_has_explicit_shared_members;
	}
	
	
}
