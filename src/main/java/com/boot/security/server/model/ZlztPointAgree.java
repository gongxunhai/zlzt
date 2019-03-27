package com.boot.security.server.model;

import java.util.Date;

public class ZlztPointAgree extends BaseEntity<Long> {

	private Integer userId;
	private Integer agreeId;
	private String type;
	private String url ;
    private String name;
    private String image;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgreeId() {
        return agreeId;
    }

    public void setAgreeId(Integer agreeId) {
        this.agreeId = agreeId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
