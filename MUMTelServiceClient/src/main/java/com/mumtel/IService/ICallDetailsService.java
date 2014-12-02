package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.MonthlyTrafficReportVO;
import com.mumtel.model.Users;

public interface ICallDetailsService extends IGenericService<CallDetail, Integer>{
	public long getPagedCallDetailListCount(String searchCriteria);
	public List<CallDetail> getPagedCallDetailList(int start,int fetchSize,String criteriaString);
	List<MonthlyTrafficReportVO> getMonthlyTrafficVO(int month, int year);
}
