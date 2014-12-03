package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.SalesRep;
import com.mumtel.model.SalesRepCommisionReport;
import com.mumtel.model.Users;


public interface ISalesRepDAO extends IGenericDAO<SalesRep, Long>{
	public long getPagedSalesRepListCount(String searchCriteria);
	public List<SalesRep> getPagedSalesRepList(int start,int fetchSize,String criteriaString);
	public List<SalesRepCommisionReport> getCommissionDetail(String id,String month,String year);
}
