package com.mumtel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Customer extends Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855329774581173051L;
	@NotEmpty(message="Telophone should not be empty.")
	private String telephone;
	@OneToOne
	private SalesRepCustomerRef salesRepAssigned;
	@OneToMany(mappedBy="customer")
	private List<Bill> bills;
	
	public Customer(String telephone) {
		super();
		this.telephone = telephone;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String lastName, String emailAddress,String tel) {
		super(firstName, lastName, emailAddress);
		this.telephone=tel;
		// TODO Auto-generated constructor stub
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public SalesRepCustomerRef getSalesRepAssigned() {
		return salesRepAssigned;
	}

	public void setSalesRepAssigned(SalesRepCustomerRef salesRepAssigned) {
		this.salesRepAssigned = salesRepAssigned;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public String getTelephone() {
		return telephone;
	}

	@Override
	public String toString() {
		return "Customer [telephone=" + telephone + ", getPersonID()="
				+ getPersonID() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getAddress()="
				+ getAddress() + ""
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
}
