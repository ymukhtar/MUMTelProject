package com.mumtel.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICallServicesService;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.Idao.IServiceDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Service;

@org.springframework.stereotype.Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CallServicesService extends GenericService<Service, Integer> implements ICallServicesService{

	@Autowired
	private IServiceDAO serviceDAO;
	
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


	@Override
	public void setGenericDAO() {
		genericDAO=serviceDAO;
	}


}
