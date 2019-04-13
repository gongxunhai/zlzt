package com.boot.security.server.model;

import java.util.Date;

public class ZlztContactUs extends BaseEntity<Long> {

	private String phone;
	private String address;
	private String mail;

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
