package com.mumtel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SalesRepCommisionReport implements Serializable{
	
	@Id
	private long id;
	private String name;
	private String callsDuration;
	private String telephone;
	private BigDecimal callCost;
	private BigDecimal commission;
	
	public SalesRepCommisionReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalesRepCommisionReport(long id, String name,
			String callsDuration, String telephone, BigDecimal callCost,
			BigDecimal commission) {
		super();
		this.id = id;
		this.name = name;
		this.callsDuration = callsDuration;
		this.telephone = telephone;
		this.callCost = callCost;
		this.commission = commission;
	}

	public long getPersonID() {
		return id;
	}
	public void setPersonID(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCallsDuration() {
		return callsDuration;
	}
	public void setCallsDuration(String callsDuration) {
		this.callsDuration = callsDuration;
	}
	public BigDecimal getCallCost() {
		return callCost;
	}
	public void setCallCost(BigDecimal callCost) {
		this.callCost = callCost;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
