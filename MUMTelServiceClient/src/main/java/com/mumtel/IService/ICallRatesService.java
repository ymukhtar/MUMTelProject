package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.CallRates;

public interface ICallRatesService {

	public void createCallRates(CallRates callRates);
	public void createAll(List<CallRates> callRates);
	public void updateCallRates(CallRates callRates);
	public void deleteCallRates(CallRates callRates);
	public CallRates getCallRates(int code);
	public List<CallRates> getAllcallRates();
	public long getPagedCallRatesListCount(String searchCriteria);
	public List<CallRates> getPagedCallRatesList(int start,int fetchSize,String criteriaString);
	public List<CallRates> getAllcallRates(int countryCode,int serviceCode);
}
