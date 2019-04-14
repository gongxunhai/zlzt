package com.boot.security.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ZlztDatainfo extends BaseEntity<Long> {

	private Integer fId;
	private Integer sId;
	private Integer tId;
	private Integer cId;
	private String country;
	private String openId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date openDay;
	private String applyId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date applyDay;
	private String title;
	private String titleFy;
	private String zy;
	private String zyFy;
	private String powerAsk;
	private String powerAFy;
	private Integer powerAN;
	private String applyMan;
	private String applyPlace;
	private String createMan;
	private String lawS;
	private String nowLawS;
	private String zlType;
	private String ipcMC;
	private String ipcMCFy;
	private String ipc;
	private String ipcFy;
	private String cpec;
	private String cpecFy;
	private String beUsedNum;
	private String value;
	private String zlImage;
	private String zlText;
	private String homeImage;

	private String name ;
	private Integer count ;
	private List<ZlztDatainfo> children;
	private String keyname ;
	private Integer classifyId;
	private Integer type;

	private String openDayStr;
	private String applyDayStr;
	private String fromTable;

	private Integer pointNum;
	private Integer careNum;

	private String fIdName;
	private String sIdName;
	private String tIdName;
	private String cIdName;

	private String secret;
	private int userId;
	private int careId;
	private int agreeId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ZlztDatainfo> getChildren() {
		return children;
	}

	public void setChildren(List<ZlztDatainfo> children) {
		this.children = children;
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

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getOpenDay() {
		return openDay;
	}
	public void setOpenDay(Date openDay) {
		this.openDay = openDay;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleFy() {
		return titleFy;
	}
	public void setTitleFy(String titleFy) {
		this.titleFy = titleFy;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZyFy() {
		return zyFy;
	}
	public void setZyFy(String zyFy) {
		this.zyFy = zyFy;
	}
	public String getPowerAsk() {
		return powerAsk;
	}
	public void setPowerAsk(String powerAsk) {
		this.powerAsk = powerAsk;
	}
	public String getPowerAFy() {
		return powerAFy;
	}
	public void setPowerAFy(String powerAFy) {
		this.powerAFy = powerAFy;
	}

	public Integer getPowerAN() {
		return powerAN;
	}

	public void setPowerAN(Integer powerAN) {
		this.powerAN = powerAN;
	}

	public String getApplyMan() {
		return applyMan;
	}
	public void setApplyMan(String applyMan) {
		this.applyMan = applyMan;
	}
	public String getApplyPlace() {
		return applyPlace;
	}
	public void setApplyPlace(String applyPlace) {
		this.applyPlace = applyPlace;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public String getLawS() {
		return lawS;
	}
	public void setLawS(String lawS) {
		this.lawS = lawS;
	}
	public String getNowLawS() {
		return nowLawS;
	}
	public void setNowLawS(String nowLawS) {
		this.nowLawS = nowLawS;
	}
	public String getZlType() {
		return zlType;
	}
	public void setZlType(String zlType) {
		this.zlType = zlType;
	}
	public String getIpcMC() {
		return ipcMC;
	}
	public void setIpcMC(String ipcMC) {
		this.ipcMC = ipcMC;
	}
	public String getIpcMCFy() {
		return ipcMCFy;
	}
	public void setIpcMCFy(String ipcMCFy) {
		this.ipcMCFy = ipcMCFy;
	}
	public String getIpc() {
		return ipc;
	}
	public void setIpc(String ipc) {
		this.ipc = ipc;
	}
	public String getIpcFy() {
		return ipcFy;
	}
	public void setIpcFy(String ipcFy) {
		this.ipcFy = ipcFy;
	}
	public String getCpec() {
		return cpec;
	}
	public void setCpec(String cpec) {
		this.cpec = cpec;
	}
	public String getCpecFy() {
		return cpecFy;
	}
	public void setCpecFy(String cpecFy) {
		this.cpecFy = cpecFy;
	}
	public String getBeUsedNum() {
		return beUsedNum;
	}
	public void setBeUsedNum(String beUsedNum) {
		this.beUsedNum = beUsedNum;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getZlImage() {
		return zlImage;
	}
	public void setZlImage(String zlImage) {
		this.zlImage = zlImage;
	}
	public String getZlText() {
		return zlText;
	}
	public void setZlText(String zlText) {
		this.zlText = zlText;
	}

	public String getHomeImage() {
		return homeImage;
	}

	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}

	public Integer getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOpenDayStr() {
		return openDayStr;
	}

	public void setOpenDayStr(String openDayStr) {
		this.openDayStr = openDayStr;
	}

	public String getApplyDayStr() {
		return applyDayStr;
	}

	public void setApplyDayStr(String applyDayStr) {
		this.applyDayStr = applyDayStr;
	}

	public String getFromTable() {
		return fromTable;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
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

    public String gettIdName() {
        return tIdName;
    }

    public void settIdName(String tIdName) {
        this.tIdName = tIdName;
    }

    public String getcIdName() {
        return cIdName;
    }

    public void setcIdName(String cIdName) {
        this.cIdName = cIdName;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
