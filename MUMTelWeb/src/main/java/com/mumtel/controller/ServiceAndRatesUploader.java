package com.mumtel.controller;

import java.io.ByteArrayInputStream;
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
	private ICallServicesService callServicesService;
	@Autowired
	private ICallRatesService callRatesService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private IServiceCountryService serviceCountryService;

	@RequestMapping(value = "/showServiceAndRateUploader", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("fileuploadForm", new FileuploadForm());
		return "uploadServicesAndRates";
	}

	@RequestMapping(value = "/uploadServicesAndRates", method = RequestMethod.POST)
	public String upload(FileuploadForm fileuploadForm, BindingResult result,Model model) {
		ByteArrayInputStream bis = new ByteArrayInputStream(fileuploadForm.getFileData().getBytes());
		Workbook workbook;
		String fileName = fileuploadForm.getFileData().getOriginalFilename();
		List<Country> allCountries = countryService.getAll();
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
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			Date fromDate = calendar.getTime();
			int totalServices = workbook.getNumberOfSheets();
			//Get All Services in db
			List<Service> allServicesList = callServicesService.getAll();
			Service service=null;
			Set<Service> allServices = new HashSet<Service>();
			for (int i = 0; i < totalServices; i++) {
				service = new Service(workbook.getSheetName(i).split("_")[0]);
				if (!allServicesList.contains(service)) {
					allServices.add(service);
				}
			}
			// save in database all services if it doesnt exist in db
			if (allServices.size() > 0) {
				callServicesService.createAll(allServices);
				allServicesList = callServicesService.getAll();
			}
			
			// create Service Country Object
			Map<ServiceCountry, List<CallRates>> serviceCallRates = new HashMap<ServiceCountry, List<CallRates>>(totalServices);
			for (int i = 0; i < totalServices; i++) {
				Sheet sheet = workbook.getSheetAt(0);
				String[] country_service = workbook.getSheetName(i).split("_");
				if ("USA".equals(country_service[1])) {
					country_service[1] = "United States of America";
				}
				ServiceCountry serviceCountry = new ServiceCountry(
						getCountryFromDesc(country_service[1], allCountries),
						getServiceFromSet(allServicesList, country_service[0]),
						new Date());
				List<CallRates> callRateList = new ArrayList<CallRates>();
				for (Row row : sheet) {
					// skip the first row
					if (row.getRowNum() == 0) {
						continue;
					}
					callRateList.add(new CallRates(getCountryFromCode(
							ExcelUtil.getIntValueFromCell(row.getCell(0)),
							allCountries), (float) ExcelUtil
							.getDoubleValueFromCell(row.getCell(1)),
							(float) ExcelUtil.getDoubleValueFromCell(row
									.getCell(2)), fromDate, null));
				}
				serviceCallRates.put(serviceCountry, callRateList);
			}
			// save servicecountry if it doesnt exist save all rates
			serviceCountryService.createAllServiceCountryAndCallRates(serviceCallRates);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("errorMessage", "Invalid File or format please upload Rate List XLS File");
			return "errorPage";
		}
		model.addAttribute("currentPage", 1);
		model.addAttribute("searchString", "");
		model.addAttribute("allCountries",allCountries);
		return "redirect:/serviceAndRatesDetails";
	}

	private Country getCountryFromDesc(String desc, List<Country> allCountry) {
		for (Country country : allCountry) {
			if (desc.equalsIgnoreCase(country.getCountryName())) {
				return country;
			}
		}
		return null;
	}

	private Country getCountryFromCode(int code, List<Country> allCountry) {
		for (Country country : allCountry) {
			if (code == country.getCallingCode()) {
				return country;
			}
		}
		return null;
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
		
		List<Country> allCountries=countryService.getAll();
		if(searchString==null||searchString.isEmpty()){
			searchString="1";
		}
		Country firstCountry = getCountryFromCode(Integer.parseInt(searchString), allCountries);
		Set<ServiceCountry> countryServiceList = firstCountry.getServicesCountryList();
		long count = countryServiceList.size();
		model.addAttribute("searchString", searchString);
		if (count == 0) {
			model.addAttribute("message",
					"No Calling Services found matching your criteria!");
		} 

		model.addAttribute("fileuploadForm", new FileuploadForm());
		model.addAttribute("allCountries", allCountries);
		model.addAttribute("selectedCountryCode",firstCountry.getCallingCode());
		return "servicesandRateslistPage";
	}
}
