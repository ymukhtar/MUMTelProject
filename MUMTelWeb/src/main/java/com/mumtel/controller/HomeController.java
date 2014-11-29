package com.mumtel.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICallServicesService;
import com.mumtel.IService.ICountryService;
import com.mumtel.utils.MumTelAuthorities;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private ICallDetailsService callDetailService;
	
	@Autowired
	ICallServicesService callServicesService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.print("redirecting to home");
		model.addAttribute("totalcodes",countryService.getPagedCountryListCount(""));
		model.addAttribute("totalcallDetails",callDetailService.getPagedCallDetailListCount(""));
		model.addAttribute("totalServices",callServicesService.getPagedServiceListCount(""));
		return "adminDashboard";
	}
	
}
