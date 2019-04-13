package com.boot.security.server.model;

import java.util.Date;

public class KjServiceNav extends BaseEntity<Long> {

	private Integer fId;
	private Integer sId;
	private String name;
	private String href;
	private String fIdName;
	private String sIdName;

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

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
