package com.mumtel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.mumtel.IService.ICallRatesService;
import com.mumtel.IService.ICallServicesService;
import com.mumtel.IService.ICountryService;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;

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
	

	@RequestMapping(method = RequestMethod.GET, value = "country_list_report/pdf")
	public ModelAndView generateCountryListPdfReport(ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<Country> countriesList = countryService.getAllCountry();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				countriesList);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("countryListPdfReport", parameterMap);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "country_list_report/xls")
	public ModelAndView generateCountryListXlsReport(ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<Country> countriesList = countryService.getAllCountry();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				countriesList);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("countryListXlsReport", parameterMap);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "monthly_traffic_report/pdf")
	public ModelAndView generateMonthlyTrafficPdfReport(
			ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		ArrayList<String> usersList = new ArrayList();
		usersList.add("test1");

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(usersList);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("monthlyTrafficPdfReport", parameterMap);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "customer_bills/pdf")
	public ModelAndView generateCustomerBillsPdfReport(ModelAndView modelAndView) {
		System.out.println("Printing Report");

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		ArrayList<String> usersList = new ArrayList();
		usersList.add("test1");

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(usersList);

		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("customerBillsPdfReport", parameterMap);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rate_sheet/pdf")
	public ModelAndView generateRateSheetPdfReport(ModelAndView modelAndView,@RequestParam("countryCode") int countryCode,@RequestParam("serviceCode") int serviceCode) {
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
		parameterMap.put("service", callServicesService.getService(serviceCode).getDescription());
		parameterMap.put("sourceCountry", countryService.getCountry(countryCode).getCountryName());
		modelAndView = new ModelAndView("RateSheetPdfReport", parameterMap);

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
