package com.mumtel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.ICustomerService;
import com.mumtel.IService.ISalesRepService;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.Idao.ICustomerDAO;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.Idao.ISalesRepDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.SalesRep;
import com.mumtel.model.SalesRepCommisionReport;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class SalesRepService extends GenericService<SalesRep, Long> implements ISalesRepService{

	@Autowired
	private ISalesRepDAO salesRepDAO;
	@Override
	public void setGenericDAO() {
		// TODO Auto-generated method stub
		genericDAO=salesRepDAO;
	}
	public long getPagedSalesRepListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return salesRepDAO.getPagedSalesRepListCount(searchCriteria);
	}
	public List<SalesRep> getPagedSalesRepList(int start, int fetchSize,
			String criteriaString) {
		// TODO Auto-generated method stub
		return salesRepDAO.getPagedSalesRepList(start, fetchSize, criteriaString);
	}
	public List<SalesRepCommisionReport> getCommissionDetail(String id,String month,String year) {
		// TODO Auto-generated method stub
		return salesRepDAO.getCommissionDetail(id, month, year);
	}
}
