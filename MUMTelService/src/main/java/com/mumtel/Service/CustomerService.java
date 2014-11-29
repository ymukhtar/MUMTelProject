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

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CustomerService extends GenericService<Customer, Integer> implements ICustomerService{

	@Autowired
	private ICustomerDAO cusgtomerDAO;
	@Override
	public void setGenericDAO() {
		// TODO Auto-generated method stub
		genericDAO=cusgtomerDAO;
	}

}
