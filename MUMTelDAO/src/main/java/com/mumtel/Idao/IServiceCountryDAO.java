package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.ServiceCountry;

public interface IServiceCountryDAO extends IGenericDAO<ServiceCountry, Integer>{
	public long getPagedServiceCountryListCount(String searchCriteria);
	public List<ServiceCountry> getPagedServiceCountryList(int start,int fetchSize,String criteriaString);
	public ServiceCountry getServiceCountry(int countryCode,String serviceName);
	public ServiceCountry getServiceCountry(int countryCode, int serviceCode);
}
