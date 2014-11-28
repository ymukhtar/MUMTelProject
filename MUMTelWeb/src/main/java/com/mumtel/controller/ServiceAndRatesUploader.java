package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import com.mumtel.IService.ICallRatesService;
import com.mumtel.IService.ICallServicesService;
import com.mumtel.IService.ICountryService;
import com.mumtel.IService.IServiceCountryService;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.ServiceCountry;
import com.mumtel.util.ExcelUtil;
import com.mumtel.util.FileuploadForm;
import com.mumtel.utils.CommonUtility;
import com.mumtel.utils.MumTelAuthorities;

@Controller
public class ServiceAndRatesUploader {
	private static Logger logger = Logger.getLogger(UploadCallDetails.class);

	@Autowired
	ICallServicesService callServicesService;
	@Autowired
	ICallRatesService callRatesService;
	@Autowired
	ICountryService countryService;
	@Autowired
	IServiceCountryService serviceCountryService;

	@RequestMapping(value = "/showServiceAndRateUploader", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("fileuploadForm", new FileuploadForm());
		return "uploadServicesAndRates";
	}

	@RequestMapping(value = "/uploadServicesAndRates", method = RequestMethod.POST)
	public String upload(FileuploadForm fileuploadForm, BindingResult result,
			Model model) {

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
			Calendar calendar = Calendar.getInstance();
			String dateOfFile = fileName.split("_")[1];
			logger.info("Dates " + dateOfFile);
			int year = Integer.parseInt(dateOfFile.substring(0, 4));
			int month = Integer.parseInt(dateOfFile.substring(4, 6));
			int day = Integer.parseInt(dateOfFile.substring(6, 8));
			if (logger.isDebugEnabled()) {
				logger.debug("Year" + year + " month " + month + " day " + day);
			}
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			Date fromDate = calendar.getTime();
			int totalServices = workbook.getNumberOfSheets();
			
			//Create only those Services which dont Exist
			List<Service> allServicesList=callServicesService.getAllServices();
			
			Set<Service> allServices = new HashSet<Service>();
			for (int i = 0; i < totalServices; i++) {
				Service s=new Service(
						workbook.getSheetName(i).split("_")[0]);
				if(!allServicesList.contains(s))
					allServices.add(s);
			}
			// save in database all services if it doesnt exist in db
			if(allServices.size()>0){
				callServicesService.createAll(allServices);
				allServicesList=callServicesService.getAllServices();
			}
			else{
				logger.debug("All Services already exist");
			}
			
			
			
			// create Service Country Object
			Map<ServiceCountry, List<CallRates>> serviceCallRates = new HashMap<ServiceCountry, List<CallRates>>(
					totalServices);
			for (int i = 0; i < totalServices; i++) {
				Sheet sheet = workbook.getSheetAt(0);
				String[] country_service = workbook.getSheetName(i).split("_");
				if("USA".equals(country_service[1])){
					country_service[1]="United States of America";
				}				
				ServiceCountry serviceCountry = new ServiceCountry(
						countryService.getCountry(country_service[1]),
						getServiceFromSet(allServicesList, country_service[0]),
						new Date());
				List<CallRates> callRateList = new ArrayList<CallRates>();
				for (Row row : sheet) {
					// skip the first two rows
					if (row.getRowNum() == 0) {
						continue;
					}
					callRateList.add(new CallRates(countryService
							.getCountry(ExcelUtil.getIntValueFromCell(row
									.getCell(0))), (float) ExcelUtil
							.getDoubleValueFromCell(row.getCell(1)),
							(float) ExcelUtil.getDoubleValueFromCell(row
									.getCell(2)), fromDate, null));
				}
				serviceCallRates.put(serviceCountry, callRateList);
			}
			serviceCountryService.createAllServiceCountryAndCallRates(serviceCallRates);

		} catch (IOException e) {
			e.printStackTrace();
		}

		model.addAttribute("currentPage", 1);
		model.addAttribute("searchString", "");
		return "redirect:/callDetails";
	}

	private Service getServiceFromSet(List<Service> all, String desc) {
		for (Service s : all) {
			if (desc.equals(s.getDescription()))
				return s;
		}
		return null;
	}

	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value = "/serviceAndRatesDetails", method = RequestMethod.GET)
	public String getserviceAndRatesDetails(Model model,
			HttpServletRequest request,
			@RequestParam("currentPage") int currentPage,
			@RequestParam("searchString") String searchString) {
		Country countryUSA = countryService
				.getCountry("United States of America");
		Set<ServiceCountry> countryServiceList = countryUSA
				.getServicesCountryList();
		long count = countryServiceList.size();
		int totalPages = (int) Math
				.ceil(1.0 * count / CommonUtility.FETCH_SIZE);
		model.addAttribute("searchString", searchString);
		if (count == 0) {
			model.addAttribute("message",
					"No Calling Services found matching your criteria!");
		} else {
			model.addAttribute("count", count);
			int startIndex = (currentPage - 1) * CommonUtility.FETCH_SIZE;
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("fetchSize", CommonUtility.FETCH_SIZE);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("message",
					"Total Call Details found matching your criteria " + count);
			int fetchSize = (int) ((startIndex + CommonUtility.FETCH_SIZE) < count ? CommonUtility.FETCH_SIZE
					: (count - startIndex));
			model.addAttribute("countryServiceList", countryServiceList);
		}

		return "servicesandRateslistPage";
	}
}
