package com.mumtel.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICountryService;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CountryService extends GenericService<Country, Integer> implements ICountryService{

	private static final Logger logger=Logger.getLogger(CountryService.class);
	
	@Autowired
	private ICountryDAO countryDAO;

	public Country getCountry(String countryName) {
		return countryDAO.get(countryName);
	}
	public long getPagedCountryListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return countryDAO.getPagedCountryListCount(searchCriteria);
	}

	public List<Country> getPagedCountryList(int start, int fetchSize,
			 String criteriaString) {
		// TODO Auto-generated method stub
		return countryDAO.getPagedCountryList(start, fetchSize, criteriaString);
	}

	@Override
	public void setGenericDAO() {
		// TODO Auto-generated method stub
		logger.debug("DAO has been SETUP");
		genericDAO=countryDAO;
	}

}
