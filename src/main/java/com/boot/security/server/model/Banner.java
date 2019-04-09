package com.boot.security.server.model;

import java.util.Date;

public class Banner extends BaseEntity<Long> {

	private String type;
	private String url;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
