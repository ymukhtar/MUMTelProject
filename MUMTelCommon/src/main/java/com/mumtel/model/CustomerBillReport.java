package com.mumtel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CustomerBillReport implements Serializable{
	
	@Id
	private long id;
	private Date callDate;
	private String callTime;
	private String callDuration;
	private String toTelephone;
	private String toCountryName;
	private String callRate;
	private String callCost;
	public CustomerBillReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerBillReport(long id, Date callDate, String callTime,
			String callDuration, String toTelephone, String toCountryName,
			String callRate, String callCost) {
		super();
		this.id = id;
		this.callDate = callDate;
		this.callTime = callTime;
		this.callDuration = callDuration;
		this.toTelephone = toTelephone;
		this.toCountryName = toCountryName;
		this.callRate = callRate;
		this.callCost = callCost;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCallDate() {
		return callDate;
	}
	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getCallDuration() {
		return callDuration;
	}
	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}
	public String getToTelephone() {
		return toTelephone;
	}
	public void setToTelephone(String toTelephone) {
		this.toTelephone = toTelephone;
	}
	public String getToCountryName() {
		return toCountryName;
	}
	public void setToCountryName(String toCountryName) {
		this.toCountryName = toCountryName;
	}
	public String getCallRate() {
		return callRate;
	}
	public void setCallRate(String callRate) {
		this.callRate = callRate;
	}
	public String getCallCost() {
		return callCost;
	}
	public void setCallCost(String callCost) {
		this.callCost = callCost;
	}
	

}
