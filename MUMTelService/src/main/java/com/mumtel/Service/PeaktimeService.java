package com.mumtel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.IPeaktimeService;
import com.mumtel.Idao.IPeakTimeDAO;
import com.mumtel.model.PeakTimes;
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class PeaktimeService extends GenericService<PeakTimes, Integer> implements IPeaktimeService{

	@Autowired
	private IPeakTimeDAO peakTimeDAO;
	
	@Override
	public void setGenericDAO() {
		// TODO Auto-generated method stub
		genericDAO=peakTimeDAO;
	}

}
