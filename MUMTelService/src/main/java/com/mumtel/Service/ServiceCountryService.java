package com.mumtel.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.IServiceCountryService;
import com.mumtel.Idao.ICallRatesDAO;
import com.mumtel.Idao.IServiceCountryDAO;
import com.mumtel.Idao.IServiceDAO;
import com.mumtel.model.CallRates;
import com.mumtel.model.ServiceCountry;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ServiceCountryService extends
		GenericService<ServiceCountry, Integer> implements
		IServiceCountryService {

	private static Logger logger = Logger
			.getLogger(ServiceCountryService.class);
	@Autowired
	private IServiceCountryDAO serviceCountryDAO;
	@Autowired
	private IServiceDAO serviceDAO;
	@Autowired
	private ICallRatesDAO callRatesDAO;

	public long getPagedServiceCountryListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return serviceCountryDAO
				.getPagedServiceCountryListCount(searchCriteria);
	}

	public List<ServiceCountry> getPagedServiceCountryList(int start,
			int fetchSize, String criteriaString) {
		// TODO Auto-generated method stub
		return serviceCountryDAO.getPagedServiceCountryList(start, fetchSize,
				criteriaString);
	}

	public void createAllServiceCountryAndCallRates(
			Map<ServiceCountry, List<CallRates>> map) {

		List<ServiceCountry> serviceCountryList = serviceCountryDAO.getAll();
		// create all ServiceCountries
		// serviceCountryDAO.createAll(map.keySet());
		ServiceCountry sc = null;
		for (Map.Entry<ServiceCountry, List<CallRates>> entry : map.entrySet()) {
			sc = entry.getKey();
			if (!serviceCountryList.contains(sc)) {
				logger.debug("creating** service country" + sc);
				serviceCountryDAO.create(sc);
			} else {
				sc = serviceCountryList.get(serviceCountryList.indexOf(sc));
			}
			callRatesDAO.createAll(entry.getValue(), sc);
		}

	}

	@Override
	public void setGenericDAO() {
		// TODO Auto-generated method stub
		genericDAO = callRatesDAO;
	}

	/**
 * 
 */
	public ServiceCountry getServiceCountry(int countryCode, String serviceName) {
		return serviceCountryDAO.getServiceCountry(countryCode, serviceName);
	}

	public boolean createNewService(ServiceCountry serviceCountry) {
		com.mumtel.model.Service service = serviceCountry.getService();
		serviceDAO.create(service);
		serviceCountry.setDateCreated(new Date(System.currentTimeMillis()));
		serviceCountryDAO.create(serviceCountry);
		return false;
	}
}
