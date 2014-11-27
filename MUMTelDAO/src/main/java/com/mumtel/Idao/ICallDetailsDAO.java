package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Users;

public interface ICallDetailsDAO {
	public void create(CallDetail callDetail);
	public void createAll(List<CallDetail> callDetail);
	public void update(CallDetail callDetail);
	public void delete(CallDetail callDetail);
	public CallDetail get(int code);
	public List<CallDetail> getAll();
	public long getPagedCallDetailListCount(String searchCriteria);
	public List<CallDetail> getPagedCallDetailList(int start,int fetchSize,String criteriaString);
}