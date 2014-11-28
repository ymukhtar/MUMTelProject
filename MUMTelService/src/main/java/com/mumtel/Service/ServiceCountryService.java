package com.mumtel.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.IServiceCountryService;
import com.mumtel.Idao.ICallRatesDAO;
import com.mumtel.Idao.IServiceCountryDAO;
import com.mumtel.model.CallRates;
import com.mumtel.model.ServiceCountry;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class ServiceCountryService implements IServiceCountryService{

	private static Logger logger = Logger.getLogger(ServiceCountryService.class);
	@Autowired
	private IServiceCountryDAO serviceCountryDAO;
	
	@Autowired
	private ICallRatesDAO callRatesDAO;
	
	public void createCountryService(ServiceCountry country) {
		// TODO Auto-generated method stub
		serviceCountryDAO.create(country);
	}

	public void createAll(Set<ServiceCountry> country) {
		// TODO Auto-generated method stub
		serviceCountryDAO.createAll(country);
	}

	public void updateCountryService(ServiceCountry country) {
		// TODO Auto-generated method stub
		serviceCountryDAO.update(country);
	}

	public void deleteCountryService(ServiceCountry country) {
		// TODO Auto-generated method stub
		serviceCountryDAO.delete(country);
	}

	public ServiceCountry getCountryService(int code) {
		// TODO Auto-generated method stub
		return serviceCountryDAO.get(code);
	}
	
	public List<ServiceCountry> getAllCountry() {
		// TODO Auto-generated method stub
		return serviceCountryDAO.getAll();
	}
	public long getPagedServiceCountryListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return serviceCountryDAO.getPagedServiceCountryListCount(searchCriteria);
	}

	public List<ServiceCountry> getPagedServiceCountryList(int start, int fetchSize,
			 String criteriaString) {
		// TODO Auto-generated method stub
		return serviceCountryDAO.getPagedServiceCountryList(start, fetchSize, criteriaString);
	}

	public void createAllServiceCountryAndCallRates(Map<ServiceCountry, List<CallRates>> map) {
		
		List<ServiceCountry> serviceCountryList=serviceCountryDAO.getAll();
		//create all ServiceCountries
		//serviceCountryDAO.createAll(map.keySet());
		ServiceCountry sc=null;
		for(Map.Entry<ServiceCountry,List<CallRates>> entry:map.entrySet()){
			sc=entry.getKey();
			if(!serviceCountryList.contains(sc)){
				logger.debug("creating** service country"+sc);
				serviceCountryDAO.create(sc);
			}
			callRatesDAO.createAll(entry.getValue(),sc);
		}
		
	}
}
