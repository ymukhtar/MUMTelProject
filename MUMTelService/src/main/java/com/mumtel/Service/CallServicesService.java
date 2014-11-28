package com.mumtel.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICallServicesService;
import com.mumtel.Idao.IServiceDAO;
import com.mumtel.model.Service;

@org.springframework.stereotype.Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CallServicesService implements ICallServicesService{

	@Autowired
	private IServiceDAO serviceDAO;
	public void createService(Service service) {
		
		serviceDAO.create(service);
	}

	public void createAll(Set<Service> service) {
		
		serviceDAO.createAll(service);
	}

	public void updateService(Service service) {
		
		serviceDAO.update(service);
	}

	public void deleteService(Service service) {
		
		serviceDAO.delete(service);
	}

	public Service getService(int code) {
		
		return serviceDAO.get(code);
	}

	public List<Service> getAllServices() {
		
		return serviceDAO.getAll();
	}

	public long getPagedServiceListCount(String searchCriteria) {
		
		return serviceDAO.getPagedServiceListCount(searchCriteria);
	}

	public List<Service> getPagedServiceList(int start,
			int fetchSize, String criteriaString) {
		
		return serviceDAO.getPagedServiceList(start, fetchSize, criteriaString);
	}

	public Service getServiceByName(String desc) {
		return serviceDAO.getByServiceName(desc);
	}


}
