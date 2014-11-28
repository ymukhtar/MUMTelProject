package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Service;

public interface IServiceDAO extends IGenericDAO<Service, Integer>{
	public long getPagedServiceListCount(String searchCriteria);
	public List<Service> getPagedServiceList(int start,int fetchSize,String criteriaString);
	public Service getByServiceName(String name);
}
