package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.Authorities;
import com.mumtel.model.Customer;
import com.mumtel.model.CustomerBillReport;
import com.mumtel.model.SalesRep;
import com.mumtel.model.SalesRepCommisionReport;
import com.mumtel.model.Service;
import com.mumtel.model.Users;
/**
 * 
 * @author Awais Tariq
 *
 */

public interface ISalesRepService extends IGenericService<SalesRep, Long>{
	public long getPagedSalesRepListCount(String searchCriteria);
	public List<SalesRep> getPagedSalesRepList(int start,int fetchSize,String criteriaString);
	public List<SalesRepCommisionReport> getCommissionDetail(String id,String month,String year);
}
