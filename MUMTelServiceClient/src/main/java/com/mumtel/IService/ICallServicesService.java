package com.mumtel.IService;

import java.util.List;
import java.util.Set;

import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.Users;

public interface ICallServicesService {

	public void createService(Service service);
	public void createAll(Set<Service> service);
	public void updateService(Service service);
	public void deleteService(Service service);
	public Service getService(int code);
	public List<Service> getAllServices();
	public long getPagedServiceListCount(String searchCriteria);
	public List<Service> getPagedServiceList(int start,int fetchSize,String criteriaString);
}
