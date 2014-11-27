package com.mumtel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8647028150067313610L;
	@Id
	private int serviceCode;
	private String description;
	private int countryCode;
	
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Service(int countryCode, String description) {
		super();
		this.countryCode = countryCode;
		this.description = description;
	}
	public int getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
