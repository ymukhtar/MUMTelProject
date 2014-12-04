package com.mumtel.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.mumtel.IService.ISalesRepService;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.CustomerBillReport;
import com.mumtel.model.MonthlyTrafficReportVO;
import com.mumtel.model.SalesRep;
import com.mumtel.model.SalesRepCommisionReport;
import com.mumtel.utils.CommonUtility;
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
	@Autowired
	private ISalesRepService salesRepService;
	

	@RequestMapping(method = RequestMethod.GET, value = "country_list_report/pdf")
	public ModelAndView generateCountryListPdfReport(HttpServletResponse res, ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<Country> countriesList = countryService.getAll();
		if(countriesList==null || countriesList.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}

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
		if(countriesList==null || countriesList.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}

		
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
		if(report==null || report.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}

		
		logger.debug(Arrays.toString(report.toArray()));
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(report);
		parameterMap.put("month", CommonUtility.MONTHS.get(month)+" "+year);	
		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("monthlyTrafficPdfReport", parameterMap);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "monthly_traffic_report/xls")
	public ModelAndView generateMonthlyTrafficXlsReport(
			HttpServletResponse res, ModelAndView modelAndView,@RequestParam("month") int month,@RequestParam("year") int year) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<MonthlyTrafficReportVO> report = callDetailService.getMonthlyTrafficVO(month, year);
		if(report==null || report.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}
		
		logger.debug(Arrays.toString(report.toArray()));
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(report);

		parameterMap.put("month", CommonUtility.MONTHS.get(month)+" "+year);
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
		if(customerBill==null || customerBill.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}
		
		Customer customer = customerService.getCustomerbyPhone(phone);
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(customerBill);		
		
		float amountDue = 0;
		for(CustomerBillReport billRecord : customerBill)
		{
			amountDue += Float.parseFloat(billRecord.getCallCost());	
			int durationInMilliSeconds;
			try{
			   durationInMilliSeconds = (int)(Float.parseFloat(billRecord.getCallDuration())*60000);
			}catch(Exception ex){
				break;
			}
			String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(durationInMilliSeconds),
				    TimeUnit.MILLISECONDS.toMinutes(durationInMilliSeconds) % TimeUnit.HOURS.toMinutes(1),
				    TimeUnit.MILLISECONDS.toSeconds(durationInMilliSeconds) % TimeUnit.MINUTES.toSeconds(1));
			billRecord.setCallDuration(hms);
			
		}
		
		for(CustomerBillReport billRecord : customerBill){
			String[] parts = billRecord.getCallTime().split(":");			
			billRecord.setCallTime(String.format("%02d:%02d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
		}

		parameterMap.put("customerName", customer.getFirstName() + " " + customer.getLastName());
		parameterMap.put("customerPhone", customer.getTelephone());
		parameterMap.put("streetAddress", customer.getAddress().getStreetNo());
		parameterMap.put("city", customer.getAddress().getCity());
		parameterMap.put("state", customer.getAddress().getState());
		parameterMap.put("zip", customer.getAddress().getZip());
		parameterMap.put("totalAmount", amountDue);
		parameterMap.put("billingMonth", CommonUtility.MONTHS.get(Integer.parseInt(month))+" "+year);	
		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("customerBillsPdfReport", parameterMap);
		

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "customer_bills/xls")
	public ModelAndView generateCustomerBillsXlsReport(HttpServletResponse res,ModelAndView modelAndView,@RequestParam("phone") String phone,@RequestParam("month") String month,@RequestParam("year") String year) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		List<CustomerBillReport> customerBill = customerService.getBillDetailOfCustomer(phone, month, year);
		if(customerBill==null || customerBill.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}
		
		Customer customer = customerService.getCustomerbyPhone(phone);
		
		
		
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(customerBill);		
		
		float amountDue = 0;
		for(CustomerBillReport billRecord : customerBill)
		{
			amountDue += Float.parseFloat(billRecord.getCallCost());
			
			billRecord.setCallRate(new DecimalFormat("#0.00").format(Float.parseFloat(billRecord.getCallRate())));
			billRecord.setCallDuration(new DecimalFormat("#0.00").format(Float.parseFloat(billRecord.getCallDuration())));
			billRecord.setCallCost(new DecimalFormat("#0.00").format(Float.parseFloat(billRecord.getCallCost())));
		}

		parameterMap.put("customerName", customer.getFirstName() + " " + customer.getLastName());
		parameterMap.put("customerPhone", customer.getTelephone());
		parameterMap.put("streetAddress", customer.getAddress().getStreetNo());
		parameterMap.put("city", customer.getAddress().getCity());
		parameterMap.put("state", customer.getAddress().getState());
		parameterMap.put("zip", customer.getAddress().getZip());
		parameterMap.put("totalAmount", new DecimalFormat("#0.00").format(amountDue));
		parameterMap.put("billingMonth", CommonUtility.MONTHS.get(Integer.parseInt(month))+" "+year);	
		parameterMap.put("datasource", JRdataSource);		
		modelAndView = new ModelAndView("customerBillsXlsReport", parameterMap);
		
		res.setHeader("Content-disposition", "attachment; filename=Customer_Bill.xls");
		res.setContentType("application/vnd.ms-excel");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rate_sheet/pdf")
	public ModelAndView generateRateSheetPdfReport(HttpServletResponse res,ModelAndView modelAndView,@RequestParam("countryCode") int countryCode,@RequestParam("serviceCode") int serviceCode,@RequestParam("month") int month,@RequestParam("year") int year) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<CallRates> callRateList=callRateService.getAllcallRates(countryCode, serviceCode,month,year);
				
		if(callRateList==null || callRateList.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}
		
		if(logger.isDebugEnabled()){
			for(CallRates r:callRateList){
				logger.debug(r);
			}
		}
	
		
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(callRateList);
		
		parameterMap.put("datasource", JRdataSource);
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		parameterMap.put("startDate", formatter.format(callRateList.get(0).getDateFrom()));		
		parameterMap.put("service", callServicesService.get(serviceCode).getDescription());
		parameterMap.put("sourceCountry", countryService.get(countryCode).getCountryName());
		int peakPeriodStartTime = callRateList.get(0).getServiceCountry().getPeakTime().getPeakPeriodStart();
		parameterMap.put("peakPeriodStartTime", new DecimalFormat("00").format(peakPeriodStartTime/100)+":00");				
		int offPeakPeriodStartTime = callRateList.get(0).getServiceCountry().getPeakTime().getOffPeakPeriodStart();	
		parameterMap.put("offPeakPeriodStartTime", new DecimalFormat("00").format(offPeakPeriodStartTime/100)+":00");	
					
		modelAndView = new ModelAndView("RateSheetPdfReport", parameterMap);
		logger.debug("---->"+parameterMap.get("peakPeriodStartTime"));

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rate_sheet/xls")
	public ModelAndView generateRateSheetXlsReport(HttpServletResponse res, ModelAndView modelAndView,@RequestParam("countryCode") int countryCode,@RequestParam("serviceCode") int serviceCode,@RequestParam("month") int month,@RequestParam("year") int year) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<CallRates> callRateList=callRateService.getAllcallRates(countryCode, serviceCode,month,year);
		if(callRateList==null || callRateList.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}
		
		if(logger.isDebugEnabled()){
			for(CallRates r:callRateList){
				logger.debug(r);
			}
		}
	
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(callRateList);		
		
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("startDate", callRateList.get(0).getDateFrom());
		parameterMap.put("service", callServicesService.get(serviceCode).getDescription());
		parameterMap.put("sourceCountry", countryService.get(countryCode).getCountryName());
		int peakPeriodStartTime = callRateList.get(0).getServiceCountry().getPeakTime().getPeakPeriodStart();
		parameterMap.put("peakPeriodStartTime", new DecimalFormat("00").format(peakPeriodStartTime/100)+":00");				
		int offPeakPeriodStartTime = callRateList.get(0).getServiceCountry().getPeakTime().getOffPeakPeriodStart();	
		parameterMap.put("offPeakPeriodStartTime", new DecimalFormat("00").format(offPeakPeriodStartTime/100)+":00");	
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
			ModelAndView modelAndView,@RequestParam("id") String id, @RequestParam("month") String month,@RequestParam("year") String year) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		SalesRep salesRep=salesRepService.get(Long.parseLong(id));
		List<SalesRepCommisionReport> comissionReport = salesRepService.getCommissionDetail(id, month, year);
		if(comissionReport==null || comissionReport.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}

		float totalComission = 0;
		for(SalesRepCommisionReport commissionRecord : comissionReport){
			totalComission += commissionRecord.getCommission().floatValue();
		}

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(comissionReport);

		parameterMap.put("totalComission", totalComission);
		parameterMap.put("salesRepName", salesRep.getFirstName()+" "+salesRep.getLastName());
		parameterMap.put("startDate", CommonUtility.MONTHS.get(Integer.parseInt(month))+" "+year);
		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("monthlySalesRepComissionPdfReport",
				parameterMap);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "monthly_sales_rep_comission/xls")
	public ModelAndView generateMonthlySalesRepComissionXlsReport(
			ModelAndView modelAndView,@RequestParam("id") String id, @RequestParam("month") String month,@RequestParam("year") String year) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		SalesRep salesRep=salesRepService.get(Long.parseLong(id));
		List<SalesRepCommisionReport> comissionReport = salesRepService.getCommissionDetail(id, month, year);
		if(comissionReport==null || comissionReport.size()==0){
			parameterMap.put("errorMessage", "Invalid File or format please upload  Services and PeakTimes XLS File");
			modelAndView = new ModelAndView("errorPage", parameterMap);
			return modelAndView;
		}
		
		float totalComission = 0;
		for(SalesRepCommisionReport commissionRecord : comissionReport){
			totalComission += commissionRecord.getCommission().floatValue();
		}

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(comissionReport);

		parameterMap.put("totalComission", totalComission);
		parameterMap.put("salesRepName", salesRep.getFirstName()+" "+salesRep.getLastName());
		parameterMap.put("startDate", CommonUtility.MONTHS.get(Integer.parseInt(month))+" "+year);
		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("monthlySalesRepComissionXlsReport",
				parameterMap);

		return modelAndView;
	}

}
