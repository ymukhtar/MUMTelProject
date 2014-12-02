package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.Users;


public interface ICustomerDAO extends IGenericDAO<Customer, Long>{
	public long getPagedCustomerListCount(String searchCriteria);
	public List<Customer> getPagedCustomerList(int start,int fetchSize,String criteriaString);
}
