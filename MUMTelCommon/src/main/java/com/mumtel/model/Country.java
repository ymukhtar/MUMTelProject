package com.mumtel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Country implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179486040726759668L;
	@Id
	private int callingCode;
	private String countryName;
	@ManyToMany
	@JoinTable(name = "SERVICE_COUNTRY", joinColumns = { @JoinColumn(name = "callingCode") }, inverseJoinColumns = { @JoinColumn(name = "serviceCode") })
	private Set<Service> servicesList=new HashSet<Service>();
	
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(int callingCode, String countryName) {
		super();
		this.callingCode = callingCode;
		this.countryName = countryName;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + callingCode;
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
		Country other = (Country) obj;
		if (callingCode != other.callingCode)
			return false;
		return true;
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
	public void addService(Service service){
		this.servicesList.add(service);
	}
	
}
