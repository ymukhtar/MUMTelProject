package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mumtel.IService.ICountryService;
import com.mumtel.model.CallDetail;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.ServiceCountry;
import com.mumtel.util.ExcelUtil;
import com.mumtel.util.FileuploadForm;
import com.mumtel.utils.PrettyPrintingMap;

@Controller
public class ServiceAndRatesUploader {
	private static Logger logger = Logger.getLogger(UploadCallDetails.class);

	@Autowired
	ICountryService countryService;

	@RequestMapping(value = "/showServiceAndRateUploader", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("fileuploadForm", new FileuploadForm());
		return "uploadCallDetails";
	}

	@RequestMapping(value = "/uploadServicesAndRates", method = RequestMethod.POST)
	public String upload(FileuploadForm fileuploadForm, BindingResult result) {

		ByteArrayInputStream bis = new ByteArrayInputStream(fileuploadForm
				.getFileData().getBytes());
		Workbook workbook;
		String fileName=fileuploadForm.getFileData().getOriginalFilename();
		try {
			if (fileName.endsWith("xls")) {
				workbook = new HSSFWorkbook(bis);
			} else if (fileName.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(bis);
			} else {
				throw new IllegalArgumentException(
						"Received file does not have a standard excel extension.");
			}

			Calendar calendar=Calendar.getInstance();
			String dateOfFile=fileName.split("_")[1];
			logger.info("Dates "+dateOfFile);
			int year=Integer.parseInt(dateOfFile.substring(0, 4));
			int month=Integer.parseInt(dateOfFile.substring(4, 6));
			int day=Integer.parseInt(dateOfFile.substring(6, 8));
			if(logger.isDebugEnabled()){
				logger.debug("Year"+year+" month "+month+" day "+day);
			}
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			Date fromDate=calendar.getTime();
			
			int totalServices = workbook.getNumberOfSheets();
			
			Set<String> allServices=new HashSet<String>();
			
			for(int i=0;i<totalServices;i++ ){
				allServices.add(workbook.getSheetName(i).split("_")[1]);
			}
			
			//create all services first
			if(logger.isDebugEnabled()){
				logger.debug(Arrays.toString(allServices.toArray()));
			}
			
			//create Service Country Object
			
			Map<ServiceCountry,List<CallRates>> serviceCallRates=new HashMap<ServiceCountry, List<CallRates>>(totalServices);

			for (int i = 0; i < totalServices; i++) {

				Sheet sheet = workbook.getSheetAt(0);

				String[] country_service=workbook.getSheetName(i).split("_");
				
				Service service=null;//fetch from service 
				ServiceCountry serviceCountry=new ServiceCountry(countryService.getCountry(country_service[0]), service, new Date());
				
				List<CallRates> callRateList = new ArrayList<CallRates>();
				for (Row row : sheet) {
					// skip the first two rows
					if (row.getRowNum() == 0 ) {
						continue;
					}
					callRateList.add(
							new CallRates(
									countryService.getCountry(ExcelUtil.getIntValueFromCell(row.getCell(0))),
									(float)ExcelUtil.getDoubleValueFromCell(row.getCell(1)), 
									(float)ExcelUtil.getDoubleValueFromCell(row.getCell(2)),fromDate,null));
					
				}
				
				serviceCallRates.put(serviceCountry, callRateList);
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug(new PrettyPrintingMap<ServiceCountry, List<CallRates>>(serviceCallRates).toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "countrieslistPage";
	}
}
