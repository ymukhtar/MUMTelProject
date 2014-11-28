package com.mumtel.Idao;

import java.util.List;
import java.util.Set;

import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.Users;

public interface IServiceDAO extends IGenericDAO<Service, Integer>{
	public long getPagedServiceListCount(String searchCriteria);
	public List<Service> getPagedServiceList(int start,int fetchSize,String criteriaString);
}
