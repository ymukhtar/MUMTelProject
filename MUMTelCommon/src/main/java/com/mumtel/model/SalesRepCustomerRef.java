package com.mumtel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SalesRepCustomerRef implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6085416920347377418L;
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private SalesRep salesRep;
	@OneToOne
	private Customer customer;
	private Date dateCreated;
	private float commision;
	public SalesRepCustomerRef(SalesRep salesRep, Customer customer,
			Date dateCreated, float commision) {
		super();
		this.salesRep = salesRep;
		this.customer = customer;
		this.dateCreated = dateCreated;
		this.commision = commision;
	}
	public SalesRep getSalesRep() {
		return salesRep;
	}
	public void setSalesRep(SalesRep salesRep) {
		this.salesRep = salesRep;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public float getCommision() {
		return commision;
	}
	public void setCommision(float commision) {
		this.commision = commision;
	}
	@Override
	public String toString() {
		return "SalesRepCustomerRef [id=" + id + ", salesRep=" + salesRep
				+ ", customer=" + customer + ", dateCreated=" + dateCreated
				+ ", commision=" + commision + "]";
	}
}
