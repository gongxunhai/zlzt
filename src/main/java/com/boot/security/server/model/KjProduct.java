package com.boot.security.server.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class KjProduct extends BaseEntity<Long> {

	private String buyNum;
	private String cMan;
	private String cPhone;
	private Integer fId;
	private String haveNum;
	private String history;
	private String name;
	private String pData;
	private String pDetail;
	private String image;
	private String place;
	private String price;
	private Integer pointNum;
	private Integer careNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pubTime;

	private String salePrice;
	private Integer sId;
	private String talk;
	private String xfArea;
	private String fIdName;
	private String sIdName;
	private Integer userId;
	private Integer status;

	public String getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
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
	public Integer getFId() {
		return fId;
	}
	public void setFId(Integer fId) {
		this.fId = fId;
	}
	public String getHaveNum() {
		return haveNum;
	}
	public void setHaveNum(String haveNum) {
		this.haveNum = haveNum;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPData() {
		return pData;
	}
	public void setPData(String pData) {
		this.pData = pData;
	}
	public String getPDetail() {
		return pDetail;
	}
	public void setPDetail(String pDetail) {
		this.pDetail = pDetail;
	}
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getSId() {
		return sId;
	}
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	public String getTalk() {
		return talk;
	}
	public void setTalk(String talk) {
		this.talk = talk;
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

    public String getpData() {
        return pData;
    }

    public void setpData(String pData) {
        this.pData = pData;
    }

    public String getpDetail() {
        return pDetail;
    }

    public void setpDetail(String pDetail) {
        this.pDetail = pDetail;
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
