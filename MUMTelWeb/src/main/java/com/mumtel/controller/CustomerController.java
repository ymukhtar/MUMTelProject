package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.ICustomerService;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.util.ExcelUtil;
import com.mumtel.util.FileuploadForm;
import com.mumtel.utils.CommonUtility;
import com.mumtel.utils.MumTelAuthorities;

@Controller
@Secured(MumTelAuthorities.ROLE_ADMIN)
public class CustomerController {
	
	private static Logger logger=Logger.getLogger(CustomerController.class);
	
	@Autowired
	private ICallDetailsService callDetailService;
	
	@Autowired
	private ICustomerService customerService;

	
	@RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("customer",new Customer());
		return "registerCustomerPage";
	}
	
	@RequestMapping(value = "/saveCustomer", method = RequestMethod.GET)
	public String save(Customer customer, BindingResult result,Model model) {
		customerService.create(customer);
		return "registerCustomerPage";
	}
	
	@RequestMapping(value="/customerDetails",method=RequestMethod.GET)
	public String getCallDetails(Model model,HttpServletRequest request,@RequestParam("currentPage") int currentPage,@RequestParam("searchString") String searchString){
		
		long count=callDetailService.getPagedCallDetailListCount(searchString);
		int totalPages=(int)Math.ceil(1.0*count/CommonUtility.FETCH_SIZE);
		model.addAttribute("searchString", searchString);
		if(count==0){
			model.addAttribute("message", "No Call Details found matching your criteria!");
		}else{
			model.addAttribute("count", count);
			int startIndex=(currentPage-1)*CommonUtility.FETCH_SIZE;
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("fetchSize", CommonUtility.FETCH_SIZE);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("message", "Total Call Details found matching your criteria "+count);
			int fetchSize=(int)( (startIndex+CommonUtility.FETCH_SIZE)<count?CommonUtility.FETCH_SIZE:(count-startIndex));
			
			List<CallDetail> callDetailList=callDetailService.getPagedCallDetailList(startIndex, fetchSize,searchString);
			model.addAttribute("callDetailsList", callDetailList);
		}
		model.addAttribute("fileuploadForm",new FileuploadForm());
		return "callDetailslistPage";
	}
}
