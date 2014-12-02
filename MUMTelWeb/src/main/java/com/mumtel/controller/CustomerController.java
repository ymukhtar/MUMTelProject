package com.mumtel.controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mumtel.IService.ICallDetailsService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.ICustomerService;
import com.mumtel.IService.ISalesRepCustomerRefService;
import com.mumtel.IService.ISalesRepService;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.SalesRep;
import com.mumtel.model.SalesRepCustomerRef;
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
	
	@Autowired
	private ISalesRepService salesRepService;
	
	@Autowired
	private ISalesRepCustomerRefService isalesRepCustomerRefService;
	

	
	@RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("customer",new Customer());
		model.addAttribute("allSalesRep",salesRepService.getAll());
		return "registerCustomerPage";
	}
	
	@InitBinder
	public void initBindet(WebDataBinder binder){
		binder.registerCustomEditor(SalesRepCustomerRef.class, new PropertyEditorSupport(){
			@Override
			public void setAsText(String val){
				SalesRep salesRep= salesRepService.get(Long.parseLong(val));
				SalesRepCustomerRef ref=new SalesRepCustomerRef(salesRep, null, new Date(), 0.10f);
				setValue(ref);
			}
		});
	}


	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public String save(@Valid Customer customer, BindingResult result,Model model) {
		if(result.hasFieldErrors()){
			model.addAttribute("allSalesRep",salesRepService.getAll());
			return "registerCustomerPage";
		}else{
			customer.getSalesRepAssigned().setCustomer(customer);
			customerService.create(customer);
		}
		model.addAttribute("message","Customer registered successfully!");
		return "successPage";
	}
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value="/customerDetails",method=RequestMethod.GET)
	public String getCustomers(Model model,HttpServletRequest request,@RequestParam("currentPage") int currentPage,@RequestParam("searchString") String searchString){
		
		long count=customerService.getPagedCustomerListCount(searchString);
		int totalPages=(int)Math.ceil(1.0*count/CommonUtility.FETCH_SIZE);
		model.addAttribute("searchString", searchString);
		if(count==0){
			model.addAttribute("message", "No Customer found matching your criteria!");
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
			
			List<Customer> customerList=customerService.getPagedCustomerList(startIndex, fetchSize,searchString);
			model.addAttribute("customerList", customerList);
		}
		
		return "CustomerDetailslistPage";
	}
	
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value="/viewBills",method=RequestMethod.GET)
	public String viewBills(Model model,HttpServletRequest request,@RequestParam("personeId") Long personeId,@RequestParam("month") int month,@RequestParam("year") String year){
		
		Customer customer=customerService.get(personeId);
		model.addAttribute("name",customer.getFirstName()+" "+customer.getLastName());
		model.addAttribute("address",customer.getAddress().getStreetNo()+", "+customer.getAddress().getCity()+", "+customer.getAddress().getState()+", "+customer.getAddress().getZip());
		model.addAttribute("phone",customer.getTelephone());
		model.addAttribute("service",customer.getServiceCountry().getService().getDescription());
		model.addAttribute("b",CommonUtility.MONTHS.get(month)+" "+year);
		
		return "customerBillReportPage";
	}
}
