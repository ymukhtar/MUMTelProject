package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Country;

public interface ICountryDAO extends IGenericDAO<Country, Integer>{
	public Country get(String countryName);
	public long getPagedCountryListCount(String searchCriteria);
	public List<Country> getPagedCountryList(int start,int fetchSize,String criteriaString);
}
