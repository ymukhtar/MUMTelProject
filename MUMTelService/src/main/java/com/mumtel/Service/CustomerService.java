package com.mumtel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.ICustomerService;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.Idao.ICustomerDAO;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.CustomerBillReport;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CustomerService extends GenericService<Customer, Long> implements ICustomerService{

	@Autowired
	private ICustomerDAO customerDAO;
	@Override		
	public void setGenericDAO() {
		// TODO Auto-generated method stub
		genericDAO=customerDAO;
	}
	public long getPagedCustomerListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return customerDAO.getPagedCustomerListCount(searchCriteria);
	}
	public List<Customer> getPagedCustomerList(int start, int fetchSize,
			String criteriaString) {
		// TODO Auto-generated method stub
		return customerDAO.getPagedCustomerList(start, fetchSize, criteriaString);
	}
	public List<CustomerBillReport> getBillDetailOfCustomer(String phone,
			String month, String year) {
		// TODO Auto-generated method stub
		return customerDAO.getBillDetailOfCustomer(phone, month, year);
	}

}
