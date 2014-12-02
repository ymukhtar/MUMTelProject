package com.mumtel.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mumtel.model.CallRates;
import com.mumtel.model.PeakTimes;
import com.mumtel.model.ServiceCountry;

public interface IServiceCountryService extends IGenericService<ServiceCountry, Integer>{
	public long getPagedServiceCountryListCount(String searchCriteria);
	public List<ServiceCountry> getPagedServiceCountryList(int start,int fetchSize,String criteriaString);
	public void createAllServiceCountryAndCallRates(Map<ServiceCountry,List<CallRates>> map );
	public ServiceCountry getServiceCountry(int countryCode,String serviceName);
	public ServiceCountry getServiceCountry(int countryCode,int serviceCode);
	public boolean createNewService(ServiceCountry serviceCountry);
	public PeakTimes getPeakTime(int serviceCode, int countryCode);
	public boolean createAllServiceCountry(Set<ServiceCountry> scSet);
}
