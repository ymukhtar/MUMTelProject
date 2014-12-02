package com.mumtel.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MonthlyTrafficReportVO {

	@Id
	private int reportID;
	
	private String service;
	
	private String fromCountry;
	
	private String toCountry;
	
	private double totalMinutesOfCall;
	
	
	public MonthlyTrafficReportVO() {
		super();
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	public double getTotalMinutesOfCall() {
		return totalMinutesOfCall;
	}

	public void setTotalMinutesOfCall(double totalMinutesOfCall) {
		this.totalMinutesOfCall = totalMinutesOfCall;
	}

	public MonthlyTrafficReportVO(String service, String fromCountry,
			String toCountry, long totalMinutesOfCall) {
		super();
		this.service = service;
		this.fromCountry = fromCountry;
		this.toCountry = toCountry;
		this.totalMinutesOfCall = totalMinutesOfCall;
	}

	@Override
	public String toString() {
		return "MonthlyTrafficReportVO [reportID=" + reportID + ", service="
				+ service + ", fromCountry=" + fromCountry + ", toCountry="
				+ toCountry + ", totalMinutesOfCall=" + totalMinutesOfCall
				+ "]";
	}
	
	
}
