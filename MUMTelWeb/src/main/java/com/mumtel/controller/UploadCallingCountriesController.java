package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.mumtel.IService.ICountryService;
import com.mumtel.model.Country;
import com.mumtel.util.ExcelUtil;
import com.mumtel.util.FileuploadForm;
import com.mumtel.utils.CommonUtility;
import com.mumtel.utils.MumTelAuthorities;
/**
 * 
 * @author ymukhtar
 *
 */
@Controller
public class UploadCallingCountriesController {
	private static Logger logger=Logger.getLogger(UploadCallingCountriesController.class);
	
	@Autowired
	private ICountryService countryService;
	
	@RequestMapping(value = "/showCountriesUploadForm", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("fileuploadForm",new FileuploadForm());
		return "uploadCallingCountryCodes";
	}
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value = "/searchCountries", method = RequestMethod.GET)
	public String searchCountries(Locale locale, Model model) {
		model.addAttribute("currentPage",1);
		return "countrieslistPage";
	}

	@RequestMapping(value="/uploadCallingCountries",method = RequestMethod.POST)
    public String upload(FileuploadForm fileuploadForm, BindingResult result) {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(fileuploadForm.getFileData().getBytes());
        Workbook workbook;
        try {
            if (fileuploadForm.getFileData().getOriginalFilename().endsWith("xls")) {
                workbook = new HSSFWorkbook(bis);
            } else if (fileuploadForm.getFileData().getOriginalFilename().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(bis);
            } else {
                throw new IllegalArgumentException("Received file does not have a standard excel extension.");
            }
           Sheet sheet= workbook.getSheetAt(0);
           List<Country> countriesList=new ArrayList<Country>();
            for (Row row : sheet) {
            	//skip the first two rows
               if (row.getRowNum() == 0 || row.getRowNum()==1) {
                 continue;
               }
              countriesList.add(new Country(ExcelUtil.getIntValueFromCell(row.getCell(1)), row.getCell(0).getStringCellValue()));
            }
            countryService.createAll(countriesList);
            if(logger.isDebugEnabled()){
            	logger.debug(Arrays.toString(countriesList.toArray()));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "countrieslistPage";
    }
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value="/countries",method=RequestMethod.GET)
	public String getJobSeekerHome(Model model,HttpServletRequest request,@RequestParam("currentPage") int currentPage,@RequestParam("searchString") String searchString){
		
		long count=countryService.getPagedCountryListCount(searchString);
		int totalPages=(int)Math.ceil(1.0*count/CommonUtility.FETCH_SIZE);
		model.addAttribute("searchString", searchString);
		if(count==0){
			model.addAttribute("message", "No Country found matching your criteria!");
		}else{
			model.addAttribute("count", count);
			int startIndex=(currentPage-1)*CommonUtility.FETCH_SIZE;
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("fetchSize", CommonUtility.FETCH_SIZE);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("message", "Total Countries found matching your criteria "+count);
			int fetchSize=(int)( (startIndex+CommonUtility.FETCH_SIZE)<count?CommonUtility.FETCH_SIZE:(count-startIndex));
			
			List<Country> countryList=countryService.getPagedCountryList(startIndex, fetchSize,searchString);
			model.addAttribute("countryList", countryList);
		}
		
		return "countrieslistPage";
	}
}
