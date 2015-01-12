package com.csgit.cao.model;

import com.google.gson.annotations.SerializedName;

public class Mod_BlobStore {

	@SerializedName("servingUrl")
	private String servingUrl;
	@SerializedName("blobKey")
	private String blobKey;
	
	public String getServingUrl() {
		return servingUrl;
	}
	public void setServingUrl(String servingUrl) {
		this.servingUrl = servingUrl;
	}
	public String getBlobKey() {
		return blobKey;
	}
	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}
}
