package com.mumtel.Idao;

import java.util.List;
import java.util.Set;

import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.ServiceCountry;
import com.mumtel.model.Users;

public interface IServiceCountryDAO extends IGenericDAO<ServiceCountry, Integer>{
	public long getPagedServiceCountryListCount(String searchCriteria);
	public List<ServiceCountry> getPagedServiceCountryList(int start,int fetchSize,String criteriaString);
}
