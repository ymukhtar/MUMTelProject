package com.mumtel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
public class PeakTimes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5995403426307114898L;
	@Id
	@GeneratedValue
	private int peaktimeID;
	
	private int peakPeriodStart;
	
	private int offPeakPeriodStart;

	@Transient
	private int peakPeriodStartHr;
	@Transient
	private int peakPeriodStartMin;
	@Transient
	private int offPeakPeriodStartHr;
	@Transient
	private int offPeakPeriodStartMin;
	
	 public PeakTimes() {
	
	}
	
	@OneToOne
	@JoinColumn(name = "SC_CODE")
	private ServiceCountry serviceCountry;
	
	
	public ServiceCountry getServiceCountry() {
		return serviceCountry;
	}
	public void setServiceCountry(ServiceCountry serviceCountry) {
		this.serviceCountry = serviceCountry;
	}
	public PeakTimes(ServiceCountry serviceCountry,
			int peakPeriodStart, int offPeakPeriodStart) {
		super();
		this.serviceCountry=serviceCountry;
		this.peakPeriodStart = peakPeriodStart;
		this.offPeakPeriodStart = offPeakPeriodStart;
	}
	public int getPeaktimeID() {
		return peaktimeID;
	}
	public void setPeaktimeID(int peaktimeID) {
		this.peaktimeID = peaktimeID;
	}

	public int getPeakPeriodStart() {
		return peakPeriodStart;
	}
	public void setPeakPeriodStart(int peakPeriodStart) {
		this.peakPeriodStart = peakPeriodStart;
	}
	public int getOffPeakPeriodStart() {
		return offPeakPeriodStart;
	}
	public void setOffPeakPeriodStart(int offPeakPeriodStart) {
		this.offPeakPeriodStart = offPeakPeriodStart;
	}
	public int getPeakPeriodStartHr() {
		if(peakPeriodStart!=0)
			peakPeriodStartHr = peakPeriodStart / 100;
		return peakPeriodStartHr;
	
	}
	public void setPeakPeriodStartHr(int peakPeriodStartHr) {
		this.peakPeriodStartHr = peakPeriodStartHr;
	}
	public int getPeakPeriodStartMin() {
		if(peakPeriodStart!=0)
			peakPeriodStartMin = peakPeriodStart % 100;
		return peakPeriodStartMin;
	}
	public void setPeakPeriodStartMin(int peakPeriodStartMin) {
		this.peakPeriodStartMin = peakPeriodStartMin;
	}
	public int getOffPeakPeriodStartHr() {
		if(offPeakPeriodStart!=0)			
			offPeakPeriodStartHr=offPeakPeriodStart/100;
		return offPeakPeriodStartHr;
	}
	
	public void setOffPeakPeriodStartHr(int offPeakPeriodStartHr) {
		this.offPeakPeriodStartHr = offPeakPeriodStartHr;
	}
	
	public int getOffPeakPeriodStartMin() {
		if(offPeakPeriodStart!=0)
			offPeakPeriodStartMin=offPeakPeriodStart%100;
		return offPeakPeriodStartMin;
	}
	
	public void setOffPeakPeriodStartMin(int offPeakPeriodStartMin) {
		this.offPeakPeriodStartMin = offPeakPeriodStartMin;
	}
	@Override
	public String toString() {
		return "PeakTimes [peaktimeID=" + peaktimeID + ", peakPeriodStart="
				+ peakPeriodStart + ", offPeakPeriodStart="
				+ offPeakPeriodStart +serviceCountry.toString()+ "]";
	}
}
