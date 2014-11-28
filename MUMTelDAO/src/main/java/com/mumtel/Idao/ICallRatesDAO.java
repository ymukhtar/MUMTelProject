package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.CallDetail;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.Users;

public interface ICallRatesDAO extends IGenericDAO<CallRates, Integer>{
	public long getPagedCallRatesListCount(String searchCriteria);
	public List<CallRates> getPagedCallRatesList(int start,int fetchSize,String criteriaString);
}
