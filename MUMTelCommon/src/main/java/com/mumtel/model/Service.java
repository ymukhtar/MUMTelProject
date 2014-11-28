package com.mumtel.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8647028150067313610L;
	
	@Id
	@GeneratedValue
	private int serviceCode;
	@Column(unique=true)
	private String description;
	@OneToMany(cascade =CascadeType.ALL ,mappedBy="service")
	private Set<ServiceCountry> servicesCountryList=new HashSet<ServiceCountry>();
	
	public Service() {
		super();
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		Service other = (Service) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}



	public Service(String description) {
		super();
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addServiceCountry(ServiceCountry serviceCountry){
		this.servicesCountryList.add(serviceCountry);
	}

	public Set<ServiceCountry> getServicesCountryList() {
		return servicesCountryList;
	}

	public void setServicesCountryList(Set<ServiceCountry> servicesCountryList) {
		this.servicesCountryList = servicesCountryList;
	}

	@Override
	public String toString() {
		return "Service [serviceCode=" + serviceCode + ", description="
				+ description + "]";
	}
	
	
}
