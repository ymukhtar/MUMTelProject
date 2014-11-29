package com.mumtel.IService;

import java.util.List;
import java.util.Set;

import com.mumtel.model.Authorities;
import com.mumtel.model.Service;

public interface ICallServicesService extends IGenericService<Service, Integer>{
	public Service getServiceByName(String desc);
	public long getPagedServiceListCount(String searchCriteria);
	public List<Service> getPagedServiceList(int start,int fetchSize,String criteriaString);
}
