package com.mumtel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICountryService;
import com.mumtel.Idao.ICallDetailsDAO;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CallDetailsService extends GenericService<CallDetail, Integer> implements ICallDetailsService{

	@Autowired
	private ICallDetailsDAO callDetailsDAO;
	
	public CallDetailsService()
	{
	}

	public long getPagedCallDetailListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		return callDetailsDAO.getPagedCallDetailListCount(searchCriteria);
	}

	public List<CallDetail> getPagedCallDetailList(int start, int fetchSize,
			 String criteriaString) {
		// TODO Auto-generated method stub
		return callDetailsDAO.getPagedCallDetailList(start, fetchSize, criteriaString);
	}

	@Override
	public void setGenericDAO() {
		genericDAO=callDetailsDAO;
		
	}

}
