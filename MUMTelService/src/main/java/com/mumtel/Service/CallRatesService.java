package com.mumtel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICallRatesService;
import com.mumtel.IService.ICountryService;
import com.mumtel.Idao.ICallDetailsDAO;
import com.mumtel.Idao.ICallRatesDAO;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CallRatesService extends GenericService<CallRates, Integer> implements ICallRatesService{

	@Autowired
	private ICallRatesDAO callRatesDAO;
	
	public CallRatesService()
	{
	}
	
	public long getPagedCallRatesListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return callRatesDAO.getPagedCallRatesListCount(searchCriteria);
	}

	public List<CallRates> getPagedCallRatesList(int start,
			int fetchSize, String criteriaString) {
		// TODO Auto-generated method stub
		return callRatesDAO.getPagedCallRatesList(start, fetchSize, criteriaString);
	}

	public List<CallRates> getAllcallRates(int countryCode, int serviceCode,int month,int year) {
		// TODO Auto-generated method stub
		return callRatesDAO.getAllcallRates(countryCode, serviceCode,month,year);
	}

	@Override
	public void setGenericDAO() {
		// TODO Auto-generated method stub
		genericDAO=callRatesDAO;
	}


}
