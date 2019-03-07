package com.boot.security.server.model;

import java.util.Date;

public class ZlztUser extends BaseEntity<Long> {

	private String username;
	private String password;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private String telephone;
	private String email;
	private Date birthday;
	private Integer sex;
	private Integer status;
	private String inforAutA;
	private String inforAutB;
	private Integer formJob;
	private Integer interestJob;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getInforAutA() {
		return inforAutA;
	}
	public void setInforAutA(String inforAutA) {
		this.inforAutA = inforAutA;
	}
	public String getInforAutB() {
		return inforAutB;
	}
	public void setInforAutB(String inforAutB) {
		this.inforAutB = inforAutB;
	}
	public Integer getFormJob() {
		return formJob;
	}
	public void setFormJob(Integer formJob) {
		this.formJob = formJob;
	}
	public Integer getInterestJob() {
		return interestJob;
	}
	public void setInterestJob(Integer interestJob) {
		this.interestJob = interestJob;
	}

}
