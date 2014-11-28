package com.mumtel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CallRates implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5405022800235857908L;
	@Id
	@GeneratedValue
	private int id;
	@OneToOne
	private Country tocallingCode;
	private float peakPeriodRate;
	private float offPeakPeriodRate;
	private Date dateFrom;
	private Date dateTo;
	@ManyToOne
	private ServiceCountry serviceCountry;
	
	public CallRates(){
		
	}
	
	public CallRates(Country tocallingCode, float peakPeriodRate,
			float offPeakPeriodRate, Date dateFrom, Date dateTo) {
		super();
		this.tocallingCode = tocallingCode;
		this.peakPeriodRate = peakPeriodRate;
		this.offPeakPeriodRate = offPeakPeriodRate;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	
	
	public ServiceCountry getServiceCountry() {
		return serviceCountry;
	}

	public void setServiceCountry(ServiceCountry serviceCountry) {
		this.serviceCountry = serviceCountry;
	}

	public Country getTocallingCode() {
		return tocallingCode;
	}
	public void setTocallingCode(Country tocallingCode) {
		this.tocallingCode = tocallingCode;
	}
	public float getPeakPeriodRate() {
		return peakPeriodRate;
	}
	public void setPeakPeriodRate(float peakPeriodRate) {
		this.peakPeriodRate = peakPeriodRate;
	}
	public float getOffPeakPeriodRate() {
		return offPeakPeriodRate;
	}
	public void setOffPeakPeriodRate(float offPeakPeriodRate) {
		this.offPeakPeriodRate = offPeakPeriodRate;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	@Override
	public String toString() {
		return "CallRates [id=" + id + ", tocallingCode=" + tocallingCode
				+ ", peakPeriodRate=" + peakPeriodRate + ", offPeakPeriodRate="
				+ offPeakPeriodRate + ", dateFrom=" + dateFrom + ", dateTo="
				+ dateTo + "]";
	}
	
	
}
