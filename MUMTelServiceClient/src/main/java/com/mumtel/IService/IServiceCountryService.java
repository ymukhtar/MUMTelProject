package com.mumtel.IService;

import java.util.List;
import java.util.Set;

import com.mumtel.model.Country;
import com.mumtel.model.ServiceCountry;
import com.mumtel.model.Users;

public interface IServiceCountryService {

	public void createCountryService(ServiceCountry serviceCountry);
	public void createAll(Set<ServiceCountry> serviceCountry);
	public void updateCountryService(ServiceCountry serviceCountry);
	public void deleteCountryService(ServiceCountry serviceCountry);
	public ServiceCountry getCountryService(int code);
	public List<ServiceCountry> getAllCountry();
	public long getPagedServiceCountryListCount(String searchCriteria);
	public List<ServiceCountry> getPagedServiceCountryList(int start,int fetchSize,String criteriaString);
}
