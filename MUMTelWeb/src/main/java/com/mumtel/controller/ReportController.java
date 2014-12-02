package com.mumtel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICallRatesService;
import com.mumtel.IService.ICallServicesService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.ICustomerService;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;
import com.mumtel.model.CustomerBillReport;
import com.mumtel.model.MonthlyTrafficReportVO;
import com.mumtel.utils.PrettyPrintingMap;

@Controller
@RequestMapping("/report/")
public class ReportController {

	private static final Logger logger = Logger
			.getLogger(ReportController.class);

	@Autowired
	private ICountryService countryService;
	@Autowired
	private ICallServicesService callServicesService;
	@Autowired
	private ICallRatesService callRateService;
	@Autowired
	private ICallDetailsService callDetailService;
	
	@Autowired
	private ICustomerService customerService;
	

	@RequestMapping(method = RequestMethod.GET, value = "country_list_report/pdf")
	public ModelAndView generateCountryListPdfReport(HttpServletResponse res, ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<Country> countriesList = countryService.getAll();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				countriesList);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("countryListPdfReport", parameterMap);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "country_list_report/xls")
	public ModelAndView generateCountryListXlsReport(HttpServletResponse res, ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<Country> countriesList = countryService.getAll();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				countriesList);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("countryListXlsReport", parameterMap);
		
		res.setHeader("Content-disposition", "attachment; filename=Calling_Codes.xls");
		res.setContentType("application/vnd.ms-excel");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "monthly_traffic_report/pdf")
	public ModelAndView generateMonthlyTrafficPdfReport(
			HttpServletResponse res, ModelAndView modelAndView,@RequestParam("month") int month,@RequestParam("year") int year) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<MonthlyTrafficReportVO> report = callDetailService.getMonthlyTrafficVO(month, year);	
		logger.debug(Arrays.toString(report.toArray()));
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(report);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("monthlyTrafficPdfReport", parameterMap);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "monthly_traffic_report/xls")
	public ModelAndView generateMonthlyTrafficXlsReport(
			HttpServletResponse res, ModelAndView modelAndView,@RequestParam("month") int month,@RequestParam("year") int year) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<MonthlyTrafficReportVO> report = callDetailService.getMonthlyTrafficVO(month, year);	
		logger.debug(Arrays.toString(report.toArray()));
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(report);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("monthlyTrafficXlsReport", parameterMap);
		
		res.setHeader("Content-disposition", "attachment; filename=Monthly_Traffic_Report.xls");
		res.setContentType("application/vnd.ms-excel");

		return modelAndView;
	}	
	

	@RequestMapping(method = RequestMethod.GET, value = "customer_bills/pdf")
	public ModelAndView generateCustomerBillsPdfReport(ModelAndView modelAndView,@RequestParam("phone") String phone,@RequestParam("month") String month,@RequestParam("year") String year) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<CustomerBillReport> customerBill = customerService.getBillDetailOfCustomer(phone, month, year);
		
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(customerBill);		

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("customerBillsPdfReport", parameterMap);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "customer_bills/xls")
	public ModelAndView generateCustomerBillsXlsReport(HttpServletResponse res,ModelAndView modelAndView,@RequestParam("phone") String phone,@RequestParam("month") String month,@RequestParam("year") String year) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<CustomerBillReport> customerBill = customerService.getBillDetailOfCustomer(phone, month, year);
		
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(customerBill);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("customerBillsXlsReport", parameterMap);
		
		res.setHeader("Content-disposition", "attachment; filename=Customer_Bill.xls");
		res.setContentType("application/vnd.ms-excel");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rate_sheet/pdf")
	public ModelAndView generateRateSheetPdfReport(HttpServletResponse res,ModelAndView modelAndView,@RequestParam("countryCode") int countryCode,@RequestParam("serviceCode") int serviceCode) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<CallRates> callRateList=callRateService.getAllcallRates(countryCode, serviceCode);
		if(logger.isDebugEnabled()){
			for(CallRates r:callRateList){
				logger.debug(r);
			}
		}
	
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(callRateList);
		
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("service", callServicesService.get(serviceCode).getDescription());
		parameterMap.put("sourceCountry", countryService.get(countryCode).getCountryName());
		parameterMap.put("peakPeriodStartTime", callRateList.get(0).getServiceCountry().getPeakTime().getPeakPeriodStart());
		parameterMap.put("offPeakPeriodStartTime", callRateList.get(0).getServiceCountry().getPeakTime().getOffPeakPeriodStart());		
		modelAndView = new ModelAndView("RateSheetPdfReport", parameterMap);
		logger.debug("---->"+parameterMap.get("peakPeriodStartTime"));

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rate_sheet/xls")
	public ModelAndView generateRateSheetXlsReport(HttpServletResponse res, ModelAndView modelAndView,@RequestParam("countryCode") int countryCode,@RequestParam("serviceCode") int serviceCode) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<CallRates> callRateList=callRateService.getAllcallRates(countryCode, serviceCode);
		if(logger.isDebugEnabled()){
			for(CallRates r:callRateList){
				logger.debug(r);
			}
		}
	
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(callRateList);		
		
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("service", callServicesService.get(serviceCode).getDescription());
		parameterMap.put("sourceCountry", countryService.get(countryCode).getCountryName());
		modelAndView = new ModelAndView("RateSheetXlsReport", parameterMap);
		
//		Calendar cal = Calendar.getInstance();
//	    cal.setTime(new Date());
//	    int year = cal.get(Calendar.YEAR);
//	    int month = cal.get(Calendar.MONTH);
//	    int day = cal.get(Calendar.DAY_OF_MONTH);
		
		res.setHeader("Content-disposition", "attachment; filename="+callServicesService.get(serviceCode).getDescription()+"_"+countryService.get(countryCode).getCountryName() +".xls");
		res.setContentType("application/vnd.ms-excel");
		
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "monthly_sales_rep_comission/pdf")
	public ModelAndView generateMonthlySalesRepComissionPdfReport(
			ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		ArrayList<String> usersList = new ArrayList();
		usersList.add("test1");

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(usersList);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("monthlySalesRepComissionPdfReport",
				parameterMap);

		return modelAndView;
	}

}
