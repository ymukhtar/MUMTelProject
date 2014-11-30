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
}
