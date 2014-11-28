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
import com.mumtel.model.CallDetail;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CallRatesService implements ICallRatesService{

	@Autowired
	private ICallRatesDAO callRatesDAO;
	public void createCallRates(CallRates callRates) {
		// TODO Auto-generated method stub
		callRatesDAO.create(callRates);
	}

	public void createAll(List<CallRates> callRates) {
		// TODO Auto-generated method stub
		callRatesDAO.createAll(callRates);
	}

	public void updateCallRates(CallRates callRates) {
		// TODO Auto-generated method stub
		callRatesDAO.update(callRates);
	}

	public void deleteCallRates(CallRates callRates) {
		// TODO Auto-generated method stub
		callRatesDAO.delete(callRates);
	}

	public CallRates getCallRates(int code) {
		// TODO Auto-generated method stub
		return callRatesDAO.get(code);
	}

	public List<CallRates> getAllcallRates() {
		// TODO Auto-generated method stub
		return getAllcallRates();
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

	public List<CallRates> getAllcallRates(int countryCode, int serviceCode) {
		// TODO Auto-generated method stub
		return callRatesDAO.getAllcallRates(countryCode, serviceCode);
	}


}
