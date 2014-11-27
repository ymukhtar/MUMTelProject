package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Country;

public interface ICountryDAO {
	public void create(Country country);
	public void createAll(List<Country> country);
	public void update(Country country);
	public void delete(Country country);
	public Country get(int code);
	public Country get(String countryName);
	public List<Country> getAll();
	public long getPagedCountryListCount(String searchCriteria);
	public List<Country> getPagedCountryList(int start,int fetchSize,String criteriaString);
}
