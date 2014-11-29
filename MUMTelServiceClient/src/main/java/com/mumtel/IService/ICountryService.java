package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.Users;

public interface ICountryService extends IGenericService<Country, Integer>{

	public Country getCountry(String countryName);
	public long getPagedCountryListCount(String searchCriteria);
	public List<Country> getPagedCountryList(int start,int fetchSize,String criteriaString);
}
