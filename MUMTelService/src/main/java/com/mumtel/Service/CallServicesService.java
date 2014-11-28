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
		// TODO Auto-generated method stub
		serviceDAO.create(service);
	}

	public void createAll(Set<Service> service) {
		// TODO Auto-generated method stub
		serviceDAO.createAll(service);
	}

	public void updateService(Service service) {
		// TODO Auto-generated method stub
		serviceDAO.update(service);
	}

	public void deleteService(Service service) {
		// TODO Auto-generated method stub
		serviceDAO.delete(service);
	}

	public Service getService(int code) {
		// TODO Auto-generated method stub
		return serviceDAO.get(code);
	}

	public List<Service> getAllServices() {
		// TODO Auto-generated method stub
		return serviceDAO.getAll();
	}

	public long getPagedServiceListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return serviceDAO.getPagedServiceListCount(searchCriteria);
	}

	public List<Service> getPagedServiceList(int start,
			int fetchSize, String criteriaString) {
		// TODO Auto-generated method stub
		return serviceDAO.getPagedServiceList(start, fetchSize, criteriaString);
	}


}
