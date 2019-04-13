package com.boot.security.server.model;

import java.util.Date;

public class KjServiceProduct extends BaseEntity<Long> {

	private String name;
	private String href;
	private String image;
	private String content;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
