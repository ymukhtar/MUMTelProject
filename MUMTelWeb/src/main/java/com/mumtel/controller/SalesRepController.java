package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.mumtel.IService.ISalesRepService;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.SalesRep;
import com.mumtel.util.ExcelUtil;
import com.mumtel.util.FileuploadForm;
import com.mumtel.utils.CommonUtility;
import com.mumtel.utils.MumTelAuthorities;

@Controller
@Secured(MumTelAuthorities.ROLE_ADMIN)
public class SalesRepController {
	
	private static Logger logger=Logger.getLogger(SalesRepController.class);
	
	@Autowired
	private ISalesRepService salesRepService;

	
	@RequestMapping(value = "/registerSalesRep", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("salesRep",new SalesRep());
		return "registerSalesRep";
	}
	
	@RequestMapping(value = "/saveSalesRep", method = RequestMethod.POST)
	public String save(@Valid SalesRep salesRep, BindingResult result,Model model) {
		if(result.hasFieldErrors()){
			return "registerSalesRep";
		}else{
			salesRepService.create(salesRep);
		}
		model.addAttribute("message","SalesRep registered successfully!");
		return "successPage";
	}
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value="/salesRepDetails",method=RequestMethod.GET)
	public String getSalesRep(Model model,HttpServletRequest request,@RequestParam("currentPage") int currentPage,@RequestParam("searchString") String searchString){
		
		long count=salesRepService.getPagedSalesRepListCount(searchString);
		int totalPages=(int)Math.ceil(1.0*count/CommonUtility.FETCH_SIZE);
		model.addAttribute("searchString", searchString);
		if(count==0){
			model.addAttribute("message", "No Sales Rep found matching your criteria!");
		}else{
			model.addAttribute("count", count);
			int startIndex=(currentPage-1)*CommonUtility.FETCH_SIZE;
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("fetchSize", CommonUtility.FETCH_SIZE);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("months", CommonUtility.MONTHS);
			model.addAttribute("years", CommonUtility.YEARS);
			model.addAttribute("message", "Total Customer found matching your criteria "+count);
			int fetchSize=(int)( (startIndex+CommonUtility.FETCH_SIZE)<count?CommonUtility.FETCH_SIZE:(count-startIndex));
			
			List<SalesRep> salesRepList=salesRepService.getPagedSalesRepList(startIndex, fetchSize, searchString);
			model.addAttribute("salesRepList", salesRepList);
		}
		
		return "SalesRepDetailslistPage";
	}
}
