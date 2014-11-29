package com.mumtel.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mumtel.model.CallRates;
import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.ServiceCountry;
import com.mumtel.model.Users;

public interface IServiceCountryService extends IGenericService<ServiceCountry, Integer>{
	
	public long getPagedServiceCountryListCount(String searchCriteria);
	public List<ServiceCountry> getPagedServiceCountryList(int start,int fetchSize,String criteriaString);
	public void createAllServiceCountryAndCallRates(Map<ServiceCountry,List<CallRates>> map );
}
