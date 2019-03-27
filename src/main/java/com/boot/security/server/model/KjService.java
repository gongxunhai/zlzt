package com.boot.security.server.model;



public class KjService extends BaseEntity<Long> {

	private String descript;
	private Integer type;
	private Integer serviceWay;
	private String price;
	private String introduction;
	private String flow;
	private Integer record;
	private Integer commentId;
	private String name;
	private String image;
	private String flowImageA;
	private String flowImageB;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getServiceWay() {
		return serviceWay;
	}
	public void setServiceWay(Integer serviceWay) {
		this.serviceWay = serviceWay;
	}
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getFlowImageA() {
        return flowImageA;
    }
    public void setFlowImageA(String flowImageA) {
        this.flowImageA = flowImageA;
    }
    public String getFlowImageB() {
        return flowImageB;
    }
    public void setFlowImageB(String flowImageB) {
        this.flowImageB = flowImageB;
    }
}
