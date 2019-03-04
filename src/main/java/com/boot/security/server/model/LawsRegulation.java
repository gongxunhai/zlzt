package com.boot.security.server.model;

import java.util.Date;

public class LawsRegulation extends BaseEntity<Long> {

	private String addFile;
	private String company;
	private String name;
	private Integer pageView;
	private String symbol;
	private String fId;
	private String sId;
	private String content;
	private String fIdName;
	private String sIdName;

	public String getAddFile() {
		return addFile;
	}
	public void setAddFile(String addFile) {
		this.addFile = addFile;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPageView() {
		return pageView;
	}
	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public String getfIdName() {
        return fIdName;
    }

    public void setfIdName(String fIdName) {
        this.fIdName = fIdName;
    }

    public String getsIdName() {
        return sIdName;
    }

    public void setsIdName(String sIdName) {
        this.sIdName = sIdName;
    }
}
