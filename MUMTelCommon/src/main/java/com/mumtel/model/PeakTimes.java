package com.mumtel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	@Override
	public String toString() {
		return "PeakTimes [peaktimeID=" + peaktimeID + ", peakPeriodStart="
				+ peakPeriodStart + ", offPeakPeriodStart="
				+ offPeakPeriodStart + "]";
	}
	
}
