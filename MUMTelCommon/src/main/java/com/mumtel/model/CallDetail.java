package com.mumtel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CallDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7939409376286110098L;
	@Id
	@GeneratedValue
	private int callID;
	private String fromTel;
	private String toTel;
	@OneToOne
	private Country fromCallingCode;
	@OneToOne
	private Country toCallingCode;
	private int duration;
	private Date callDateandTime;
	public CallDetail(String fromTel, String toTel,
			Country fromCallingCode, Country toCallingCode, int duration,
			Date callDateandTime) {
		super();
		this.fromTel = fromTel;
		this.toTel = toTel;
		this.fromCallingCode = fromCallingCode;
		this.toCallingCode = toCallingCode;
		this.duration = duration;
		this.callDateandTime = callDateandTime;
	}
	public String getFromTel() {
		return fromTel;
	}
	public void setFromTel(String fromTel) {
		this.fromTel = fromTel;
	}
	public String getToTel() {
		return toTel;
	}
	public void setToTel(String toTel) {
		this.toTel = toTel;
	}
	public Country getFromCallingCode() {
		return fromCallingCode;
	}
	public void setFromCallingCode(Country fromCallingCode) {
		this.fromCallingCode = fromCallingCode;
	}
	public Country getToCallingCode() {
		return toCallingCode;
	}
	public void setToCallingCode(Country toCallingCode) {
		this.toCallingCode = toCallingCode;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getCallDateandTime() {
		return callDateandTime;
	}
	public void setCallDateandTime(Date callDateandTime) {
		this.callDateandTime = callDateandTime;
	}
	
}
