package com.mumtel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@OneToOne(cascade=CascadeType.ALL)
	private SalesRepCustomerRef salesRepAssigned;
	@OneToMany(mappedBy="customer")
	private List<Bill> bills;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((telephone == null) ? 0 : telephone.hashCode());
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
		Customer other = (Customer) obj;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

	@ManyToOne
	private ServiceCountry serviceCountry;
	
	public Customer(String telephone) {
		super();
		this.telephone = telephone;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceCountry getServiceCountry() {
		return serviceCountry;
	}

	public void setServiceCountry(ServiceCountry serviceCountry) {
		this.serviceCountry = serviceCountry;
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
