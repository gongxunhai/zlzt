package com.boot.security.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class KjZlsuper extends BaseEntity<Long> {

	private String transform;
	private String cMan;
	private String cPhone;
	private String dealMoney;
	private String dealWay;
	private Integer fId;
	private String lawS;
	private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pubTime;
	private Integer sId;
	private String useArea;
	private String xfArea;
	private String zlDetails;
	private String zlId;
	private String image;
	private String zlType;
	private String zlYear;
	private String fIdName;
	private String sIdName;
	private Integer userId;
	private Integer status;
	private Integer pointNum;
	private Integer careNum;
	private int userAppId;
	private int careId;
	private int agreeId;

	public int getUserAppId() {
		return userAppId;
	}

	public void setUserAppId(int userAppId) {
		this.userAppId = userAppId;
	}

	public int getCareId() {
		return careId;
	}

	public void setCareId(int careId) {
		this.careId = careId;
	}

	public int getAgreeId() {
		return agreeId;
	}

	public void setAgreeId(int agreeId) {
		this.agreeId = agreeId;
	}

	public String getTransform() {
		return transform;
	}

	public void setTransform(String transform) {
		this.transform = transform;
	}
	public String getCMan() {
		return cMan;
	}
	public void setCMan(String cMan) {
		this.cMan = cMan;
	}
	public String getCPhone() {
		return cPhone;
	}
	public void setCPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	public String getDealMoney() {
		return dealMoney;
	}
	public void setDealMoney(String dealMoney) {
		this.dealMoney = dealMoney;
	}
	public String getDealWay() {
		return dealWay;
	}
	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}
	public Integer getFId() {
		return fId;
	}
	public void setFId(Integer fId) {
		this.fId = fId;
	}
	public String getLawS() {
		return lawS;
	}
	public void setLawS(String lawS) {
		this.lawS = lawS;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public Integer getSId() {
		return sId;
	}
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	public String getUseArea() {
		return useArea;
	}
	public void setUseArea(String useArea) {
		this.useArea = useArea;
	}
	public String getXfArea() {
		return xfArea;
	}
	public void setXfArea(String xfArea) {
		this.xfArea = xfArea;
	}
	public String getZlDetails() {
		return zlDetails;
	}
	public void setZlDetails(String zlDetails) {
		this.zlDetails = zlDetails;
	}
	public String getZlId() {
		return zlId;
	}
	public void setZlId(String zlId) {
		this.zlId = zlId;
	}
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getZlType() {
		return zlType;
	}
	public void setZlType(String zlType) {
		this.zlType = zlType;
	}
	public String getZlYear() {
		return zlYear;
	}
	public void setZlYear(String zlYear) {
		this.zlYear = zlYear;
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

    public String getcMan() {
        return cMan;
    }

    public void setcMan(String cMan) {
        this.cMan = cMan;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
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
