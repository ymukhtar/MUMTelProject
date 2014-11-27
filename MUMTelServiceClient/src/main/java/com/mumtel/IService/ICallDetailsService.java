package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Users;

public interface ICallDetailsService {

	public void createCallDetail(CallDetail callDetail);
	public void createAll(List<CallDetail> callDetail);
	public void updateCallDetail(CallDetail callDetail);
	public void deleteCallDetail(CallDetail callDetail);
	public CallDetail getCallDetail(int code);
	public List<CallDetail> getAllCallDetail();
	public long getPagedCallDetailListCount(String searchCriteria);
	public List<CallDetail> getPagedCallDetailList(int start,int fetchSize,String criteriaString);
}
