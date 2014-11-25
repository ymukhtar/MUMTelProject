package com.mumtel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8190072958066535002L;
	@Id
	@GeneratedValue
	private int billID;
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	@Temporal(TemporalType.DATE)
	private Date toDate;
	private float billamount;
	@ManyToOne
	@JoinColumn(name="customerID")
	private Customer customer;
	public Bill(Date from, Date to, float billamount,
			Customer customer) {
		super();
		this.fromDate = from;
		this.toDate = to;
		this.billamount = billamount;
		this.customer = customer;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public float getBillamount() {
		return billamount;
	}
	public void setBillamount(float billamount) {
		this.billamount = billamount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Bill [billID=" + billID + ", from=" + fromDate + ", to=" + toDate
				+ ", billamount=" + billamount + ", customer=" + customer + "]";
	}
	
	
}
