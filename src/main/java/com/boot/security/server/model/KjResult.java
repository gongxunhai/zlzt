package com.boot.security.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class KjResult extends BaseEntity<Long> {

	private String area;
	private String cMan;
	private String cPhone;
	private String cWays;
	private Integer fId;
	private String knowledge;
	private String mNeed;
	private String name;
	private String predict;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pubTime;
	private Integer sId;
	private String source;
	private String tdetails;
	private String teMaturity;
	private String tIndex;
	private String tlevel;
	private String useRange;
	private String xfArea;
	private String image;
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

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getCWays() {
		return cWays;
	}
	public void setCWays(String cWays) {
		this.cWays = cWays;
	}
	public Integer getFId() {
		return fId;
	}
	public void setFId(Integer fId) {
		this.fId = fId;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
    public String getmNeed() {
        return mNeed;
    }
    public void setmNeed(String mNeed) {
        this.mNeed = mNeed;
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPredict() {
		return predict;
	}
	public void setPredict(String predict) {
		this.predict = predict;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTdetails() {
		return tdetails;
	}
	public void setTdetails(String tdetails) {
		this.tdetails = tdetails;
	}
	public String getTeMaturity() {
		return teMaturity;
	}
	public void setTeMaturity(String teMaturity) {
		this.teMaturity = teMaturity;
	}
	public String getTIndex() {
		return tIndex;
	}
	public void setTIndex(String tIndex) {
		this.tIndex = tIndex;
	}
	public String getTlevel() {
		return tlevel;
	}
	public void setTlevel(String tlevel) {
		this.tlevel = tlevel;
	}
	public String getUseRange() {
		return useRange;
	}
	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	public String getXfArea() {
		return xfArea;
	}
	public void setXfArea(String xfArea) {
		this.xfArea = xfArea;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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

    public String gettIndex() {
        return tIndex;
    }

    public void settIndex(String tIndex) {
        this.tIndex = tIndex;
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
