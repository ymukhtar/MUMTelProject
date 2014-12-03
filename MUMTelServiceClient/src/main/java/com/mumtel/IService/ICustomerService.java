package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.Authorities;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.CustomerBillReport;
import com.mumtel.model.Service;
import com.mumtel.model.Users;
/**
 * 
 * @author Awais Tariq
 *
 */

public interface ICustomerService extends IGenericService<Customer, Long>{
	public long getPagedCustomerListCount(String searchCriteria);
	public Customer getCustomerbyPhone(String searchCriteria);
	public List<Customer> getPagedCustomerList(int start,int fetchSize,String criteriaString);
	public List<CustomerBillReport> getBillDetailOfCustomer(String phone,String month,String year);
}
