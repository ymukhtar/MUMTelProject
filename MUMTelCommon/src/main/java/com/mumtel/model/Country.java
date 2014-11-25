package com.mumtel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Country implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179486040726759668L;
	@Id
	private int callingCode;
	private String countryName;
	public Country(int callingCode, String countryName) {
		super();
		this.callingCode = callingCode;
		this.countryName = countryName;
	}
	public int getCallingCode() {
		return callingCode;
	}
	public void setCallingCode(int callingCode) {
		this.callingCode = callingCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Override
	public String toString() {
		return "Country [callingCode=" + callingCode + ", countryName="
				+ countryName + "]";
	}
	
}
