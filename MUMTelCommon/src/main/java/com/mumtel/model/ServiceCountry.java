package com.mumtel.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ServiceCountry {

	@Id
	@GeneratedValue
	private int serviceCountryID;
	@ManyToOne
	private Country country;
	@ManyToOne
	private Service service;
	private Date dateCreated;
	
	
	
	public ServiceCountry() {
		super();
	}
	
	public ServiceCountry(Country country, Service service, Date dateCreated) {
		super();
		this.country = country;
		this.service = service;
		this.dateCreated = dateCreated;
	}
	public int getServiceCountryID() {
		return serviceCountryID;
	}
	public void setServiceCountryID(int serviceCountryID) {
		this.serviceCountryID = serviceCountryID;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "ServiceCountry [serviceCountryID=" + serviceCountryID
				+ ", country=" + country + ", service=" + service
				+ ", dateCreated=" + dateCreated + "]";
	}
	
	
	
}
