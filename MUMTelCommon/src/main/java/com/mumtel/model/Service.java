package com.mumtel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8647028150067313610L;
	@Id
	private int serviceCode;
	private String description;
	@ManyToMany(mappedBy="servicesList")
	private Set<Country> countryList=new HashSet<Country>();
	
	public Service() {
		super();
	}
	
	public void addCountry(Country country){
		this.countryList.add(country);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + serviceCode;
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
		if (serviceCode != other.serviceCode)
			return false;
		return true;
	}

	public Service(Country country, String description) {
		super();
		addCountry(country);
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
	
}
