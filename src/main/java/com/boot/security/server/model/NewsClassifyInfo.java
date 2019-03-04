package com.boot.security.server.model;

import java.util.Date;

public class NewsClassifyInfo extends BaseEntity<Long> {

	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
