package com.mumtel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICountryService;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.model.Country;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CountryService implements ICountryService{

	@Autowired
	private ICountryDAO countryDAO;
	
	public void createCountry(Country country) {
		// TODO Auto-generated method stub
		countryDAO.create(country);
	}

	public void createAll(List<Country> country) {
		// TODO Auto-generated method stub
		countryDAO.createAll(country);
	}

	public void updateCountry(Country country) {
		// TODO Auto-generated method stub
		countryDAO.update(country);
	}

	public void deleteCountry(Country country) {
		// TODO Auto-generated method stub
		countryDAO.delete(country);
	}

	public Country getCountry(int code) {
		// TODO Auto-generated method stub
		return countryDAO.get(code);
	}

	public List<Country> getAllCountry() {
		// TODO Auto-generated method stub
		return countryDAO.getAll();
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

}
