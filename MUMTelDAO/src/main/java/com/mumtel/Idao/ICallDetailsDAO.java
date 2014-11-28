package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Users;

public interface ICallDetailsDAO extends IGenericDAO<CallDetail, Integer>{
	public long getPagedCallDetailListCount(String searchCriteria);
	public List<CallDetail> getPagedCallDetailList(int start,int fetchSize,String criteriaString);
}
