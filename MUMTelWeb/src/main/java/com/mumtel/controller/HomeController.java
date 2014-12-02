package com.mumtel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICallServicesService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.ICustomerService;
import com.mumtel.Service.CustomerService;
import com.mumtel.model.MonthlyTrafficReportVO;
import com.mumtel.utils.CommonUtility;
import com.mumtel.utils.MumTelAuthorities;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = org.apache.log4j.Logger.getLogger(HomeController.class);
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private ICallDetailsService callDetailService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	ICallServicesService callServicesService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.");
		System.out.print("redirecting to home");
		model.addAttribute("totalcodes",countryService.getPagedCountryListCount(""));
		model.addAttribute("totalcallDetails",callDetailService.getPagedCallDetailListCount(""));
		model.addAttribute("totalServices",callServicesService.getPagedServiceListCount(""));
		model.addAttribute("totalCustomers",customerService.getPagedCustomerListCount(""));
		model.addAttribute("months", CommonUtility.MONTHS);
		model.addAttribute("years", CommonUtility.YEARS);
		return "adminDashboard";
	}
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value = "/getMonthlySalesReport", method = RequestMethod.GET)
	public String getMonthlySalesReport(Model model,@RequestParam("month") int month,@RequestParam("year") int year) {
		
		logger.debug("Month"+month);
		logger.debug("Year"+year);
		List<MonthlyTrafficReportVO> report=callDetailService.getMonthlyTrafficVO(month, year);
		
		logger.debug(Arrays.toString(report.toArray()));
		
		return "adminDashboard";
	}
	
	
}
