package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.CustomerBillReport;
import com.mumtel.model.Users;


public interface ICustomerDAO extends IGenericDAO<Customer, Long>{
	public long getPagedCustomerListCount(String searchCriteria);
	public Customer getCustomerbyPhone(String searchCriteria);
	public List<Customer> getPagedCustomerList(int start,int fetchSize,String criteriaString);
	public List<CustomerBillReport> getBillDetailOfCustomer(String phone,String month,String year);
}
