package com.boot.security.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PjRequire extends BaseEntity<Long> {

	private String addFile;
	private String cMoney;
	private String cWays;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private Integer fId;
	private Integer pointNum;
	private Integer careNum;

    public String getcMoney() {
        return cMoney;
    }

    public void setcMoney(String cMoney) {
        this.cMoney = cMoney;
    }

    public String getcWays() {
        return cWays;
    }

    public void setcWays(String cWays) {
        this.cWays = cWays;
    }

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

    private String man;
	private String name;
	private String phone;
	private String place;
	private Integer sId;
	private String target;
	private String unit;
	private String xfArea;
	private String fIdName;
	private String sIdName;
	private Integer userId;
	private Integer status;

	public String getAddFile() {
		return addFile;
	}
	public void setAddFile(String addFile) {
		this.addFile = addFile;
	}
	public String getCMoney() {
		return cMoney;
	}
	public void setCMoney(String cMoney) {
		this.cMoney = cMoney;
	}
	public String getCWays() {
		return cWays;
	}
	public void setCWays(String cWays) {
		this.cWays = cWays;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getFId() {
		return fId;
	}
	public void setFId(Integer fId) {
		this.fId = fId;
	}
	public String getMan() {
		return man;
	}
	public void setMan(String man) {
		this.man = man;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Integer getSId() {
		return sId;
	}
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getXfArea() {
		return xfArea;
	}
	public void setXfArea(String xfArea) {
		this.xfArea = xfArea;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPointNum() {
        return pointNum;
    }

    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }

    public Integer getCareNum() {
        return careNum;
    }

    public void setCareNum(Integer careNum) {
        this.careNum = careNum;
    }
}
