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
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.util.ExcelUtil;
import com.mumtel.util.FileuploadForm;
import com.mumtel.utils.CommonUtility;
import com.mumtel.utils.MumTelAuthorities;

@Controller
public class UploadCallDetails {
	
	private static Logger logger=Logger.getLogger(UploadCallDetails.class);
	
	
	@Autowired
	private ICallDetailsService callDetailService;
	@Autowired
	private ICountryService countryService;
	
	@RequestMapping(value = "/showUploadCallDetails", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("fileuploadForm",new FileuploadForm());
		return "uploadCallDetails";
	}
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value="/uploadCallDetails",method = RequestMethod.POST)
    public String upload(FileuploadForm fileuploadForm, BindingResult result,Model model) {
		
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
           
           
           List<CallDetail> callDetailList=new ArrayList<CallDetail>();
            for (Row row : sheet) {
            	//skip the first row
               if (row.getRowNum() == 0) {
                 continue;
               }
               
               CallDetail detail=new CallDetail(String.valueOf(ExcelUtil.getIntValueFromCell(row.getCell(2))),
							String.valueOf(ExcelUtil.getIntValueFromCell(row.getCell(3))), 
   							
							countryService.getCountry(ExcelUtil.getIntValueFromCell(row.getCell(0))),
							countryService.getCountry(ExcelUtil.getIntValueFromCell(row.getCell(1))),
							ExcelUtil.getIntValueFromCell(row.getCell(4)),String.valueOf(ExcelUtil.getIntValueFromCell(row.getCell(6))),
							ExcelUtil.getDateValueFromCell(row.getCell(5)));
               callDetailList.add(detail);
               
            }
           // countryService.createAll(countriesList);
            if(logger.isDebugEnabled()){
//            	logger.debug(Arrays.toString(callDetailList.toArray()))/;
            }
            callDetailService.createAll(callDetailList);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

		model.addAttribute("currentPage",1);
		model.addAttribute("searchString","");
		return "redirect:/callDetails";
    }
	
	@Secured(MumTelAuthorities.ROLE_ADMIN)
	@RequestMapping(value="/callDetails",method=RequestMethod.GET)
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
		
		return "callDetailslistPage";
	}
}
