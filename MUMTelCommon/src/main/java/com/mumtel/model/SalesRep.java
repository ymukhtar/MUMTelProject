package com.mumtel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
public class SalesRep extends Person implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7447607470769999958L;
	private String businesssPhone;
	@Embedded
	@Valid
	private Address businesssAddress;
	
	
	@OneToMany(mappedBy="salesRep")
	
	private List<SalesRepCustomerRef> customersReferred;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businesssPhone == null) ? 0 : businesssPhone.hashCode());
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
		SalesRep other = (SalesRep) obj;
		if (businesssPhone == null) {
			if (other.businesssPhone != null)
				return false;
		} else if (!businesssPhone.equals(other.businesssPhone))
			return false;
		return true;
	}
	
	public SalesRep() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalesRep(String businesssPhone, Address businesssAddress,String firstName, String lastName, String emailAddress) {
		super(firstName, lastName, emailAddress);
		this.businesssPhone = businesssPhone;
		this.businesssAddress = businesssAddress;
	}
	public String getBusinesssPhone() {
		return businesssPhone;
	}
	public void setBusinesssPhone(String businesssPhone) {
		this.businesssPhone = businesssPhone;
	}
	public Address getBusinesssAddress() {
		return businesssAddress;
	}
	public void setBusinesssAddress(Address businesssAddress) {
		this.businesssAddress = businesssAddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<SalesRepCustomerRef> getCustomersReferred() {
		return customersReferred;
	}
	public void setCustomersReferred(List<SalesRepCustomerRef> customersReferred) {
		this.customersReferred = customersReferred;
	}
	@Override
	public String toString() {
		return getFirstName()+" "+getLastName();
	}
	
	
}
