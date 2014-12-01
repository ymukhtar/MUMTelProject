package com.mumtel.Idao;

import com.mumtel.model.PeakTimes;


public interface IPeakTimeDAO extends IGenericDAO<PeakTimes, Integer>{ 

	public PeakTimes getLatestPeakTime(int serviceCode,int countryCode);
}
