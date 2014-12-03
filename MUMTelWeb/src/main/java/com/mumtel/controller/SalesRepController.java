package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import com.mumtel.IService.ICustomerService;
import com.mumtel.IService.ISalesRepService;
import com.mumtel.model.Address;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.SalesRep;
import com.mumtel.model.SalesRepCommisionReport;
import com.mumtel.model.SalesRepCustomerRef;
import com.mumtel.util.FileuploadForm;
import com.mumtel.utils.CommonUtility;
import com.mumtel.utils.MumTelAuthorities;

@Controller
@Secured(MumTelAuthorities.ROLE_ADMIN)
public class SalesRepController {
	
	private static Logger logger=Logger.getLogger(SalesRepController.class);
	
	@Autowired
	private ISalesRepService salesRepService;
	
	@Autowired
	private ICustomerService customerService;

	@RequestMapping(value = "/uploadSalesRep", method = RequestMethod.POST)
	public String uploadServicesAndPeakTimes(FileuploadForm fileuploadForm, BindingResult result,Model model) {
		ByteArrayInputStream bis = new ByteArrayInputStream(fileuploadForm.getFileData().getBytes());
		Workbook workbook;
		String fileName = fileuploadForm.getFileData().getOriginalFilename();
		try {
			if (fileName.endsWith("xls")) {
				workbook = new HSSFWorkbook(bis);
			} else if (fileName.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(bis);
			} else {
				throw new IllegalArgumentException(
						"Received file does not have a standard excel extension.");
			}
			Sheet sheet = workbook.getSheetAt(0);
			List<SalesRep> allSalesRep=salesRepService.getAll();
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			boolean firstRow=true;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if(firstRow){
					firstRow=false;
					continue;
				}
				SalesRep salesRep = new SalesRep();
				Customer customer=customerService.getCustomerbyPhone(String.valueOf((long)row.getCell(0).getNumericCellValue()));
				int salesRepID=(int)row.getCell(1).getNumericCellValue();
				salesRep.setFirstName("Sales");
				salesRep.setLastName("REP");
				salesRep.setAddress(new Address("2000", "Fairfield", "IA", "52557"));
				salesRep.setBusinesssAddress(salesRep.getAddress());
				salesRep.setBusinesssPhone(String.valueOf(salesRepID));
				salesRep.setEmailAddress("abc@mum.edu");
				if(allSalesRep.contains(salesRep))
					continue;
				else{
					salesRepService.create(salesRep);
					allSalesRep=salesRepService.getAll();
				}
				SalesRepCustomerRef srs=new SalesRepCustomerRef(salesRep, customer, new Date(),(int)row.getCell(2).getNumericCellValue());
				customer.setSalesRepAssigned(srs);
				customerService.update(customer);
				
			}
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("errorMessage", "Invalid File or format please Choose SalesRep XLS");
			return "errorPage";
		}
		return "redirect://home";
	}
	@RequestMapping(value = "/registerSalesRep", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("salesRep",new SalesRep());
		model.addAttribute("fileuploadForm", new FileuploadForm());
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
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value="/viewCommission",method=RequestMethod.GET)
	public String viewBills(Model model,HttpServletRequest request,@RequestParam("personeId") Long personeId,@RequestParam("month") int month,@RequestParam("year") String year){
		
		SalesRep salesRep=salesRepService.get(personeId);
		
		List<SalesRepCommisionReport> commList=salesRepService.getCommissionDetail(String.valueOf(personeId), String.valueOf(month), year);
		model.addAttribute("name",salesRep.getFirstName()+" "+salesRep.getLastName());
		model.addAttribute("address",salesRep.getBusinesssAddress().getStreetNo()+", "+salesRep.getBusinesssAddress().getCity()+", "+salesRep.getBusinesssAddress().getState()+", "+salesRep.getBusinesssAddress().getZip());
		model.addAttribute("phone",salesRep.getBusinesssPhone());
		model.addAttribute("id",salesRep.getPersonID());
		model.addAttribute("month",month);
		model.addAttribute("year",year);
		model.addAttribute("billingMonth",CommonUtility.MONTHS.get(month)+" "+year);
		model.addAttribute("commList",commList);
		
		return "SakesRepCommissionReportPage";
	}
}
