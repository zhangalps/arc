package com.arc.news.utils.querytools;

public class PhoneNumber {
	private String phoneNumber;
	private String province;
	private String city;
	private String phoneType;
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	
	@Override
	public String toString() {
	    return "PhoneNumberInfo{" +
	        "phoneNumber='" + phoneNumber + '\'' +
	        ", province='" + province + '\'' +
	        ", city='" + city + '\'' +
	        ", phoneType='" + phoneType + '\'' +
	        '}';
	  }
}
