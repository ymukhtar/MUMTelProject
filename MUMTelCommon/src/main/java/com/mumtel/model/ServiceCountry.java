package com.mumtel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ServiceCountry {

	@Id
	@GeneratedValue
	private int serviceCountryID;
	@ManyToOne
	@JoinColumn(name="country_code")
	private Country country;
	@ManyToOne
	@JoinColumn(name="service_code")
	private Service service;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@OneToMany(mappedBy="serviceCountry")
	private List<CallRates> callRateList=new ArrayList<CallRates>();
	
	
	
	
	public ServiceCountry() {
		super();
	}
	
	public ServiceCountry(Country country, Service service, Date dateCreated) {
		super();
		this.country = country;
		this.service = service;
		this.dateCreated = dateCreated;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + serviceCountryID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceCountry other = (ServiceCountry) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (serviceCountryID != other.serviceCountryID)
			return false;
		return true;
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
