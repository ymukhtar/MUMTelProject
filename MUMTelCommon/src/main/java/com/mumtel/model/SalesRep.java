package com.mumtel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SalesRep extends Person implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7447607470769999958L;
	private String businesssPhone;
	@Embedded
	private Address businesssAddress;
	@OneToMany(mappedBy="salesRep")
	private List<SalesRepCustomerRef> customersReferred;
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
		return "SalesRep [businesssPhone=" + businesssPhone
				+ ", businesssAddress=" + businesssAddress + ", getPersonID()="
				+ getPersonID() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getAddress()="
				+ getAddress() + ""
				+ "]";
	}
	
	
}
