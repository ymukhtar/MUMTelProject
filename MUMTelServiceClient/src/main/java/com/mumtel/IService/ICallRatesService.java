package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.CallRates;

public interface ICallRatesService extends IGenericService<CallRates, Integer>{
	public long getPagedCallRatesListCount(String searchCriteria);
	public List<CallRates> getPagedCallRatesList(int start,int fetchSize,String criteriaString);
	public List<CallRates> getAllcallRates(int countryCode,int serviceCode);
}
