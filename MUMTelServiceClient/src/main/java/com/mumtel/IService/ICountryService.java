package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.Country;
import com.mumtel.model.Users;

public interface ICountryService {

	public void createCountry(Country country);
	public void createAll(List<Country> country);
	public void updateCountry(Country country);
	public void deleteCountry(Country country);
	public Country getCountry(int code);
	public Country getCountry(String countryName);
	public List<Country> getAllCountry();
	public long getPagedCountryListCount(String searchCriteria);
	public List<Country> getPagedCountryList(int start,int fetchSize,String criteriaString);
}
