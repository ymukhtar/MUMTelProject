package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
import com.mumtel.model.Country;
import com.mumtel.util.ExcelUtil;
import com.mumtel.util.FileuploadForm;

@Controller
public class UploadCallDetails {
	
	private static Logger logger=Logger.getLogger(UploadCallDetails.class);
	
	@Autowired
	ICountryService countryService;
	@RequestMapping(value = "/showUploadCallDetails", method = RequestMethod.GET)
	public String displayForm(Model model) {
		model.addAttribute("fileuploadForm",new FileuploadForm());
		return "uploadCallDetails";
	}
	
	@RequestMapping(value="/uploadCallDetails",method = RequestMethod.POST)
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
           
           
           List<CallDetail> callDetailList=new ArrayList<CallDetail>();
            for (Row row : sheet) {
            	//skip the first two rows
               if (row.getRowNum() == 0 || row.getRowNum()==1) {
                 continue;
               }
               
               callDetailList.add(new CallDetail(String.valueOf(ExcelUtil.getIntValueFromCell(row.getCell(0))),
            		   							String.valueOf(ExcelUtil.getIntValueFromCell(row.getCell(1))), 
            		   							
            		   							countryService.getCountry(ExcelUtil.getIntValueFromCell(row.getCell(2))),
            		   							countryService.getCountry(ExcelUtil.getIntValueFromCell(row.getCell(3))),
            		   							ExcelUtil.getIntValueFromCell(row.getCell(4)), 
            		   							ExcelUtil.getDateValueFromCell(row.getCell(5))));
               
            }
           // countryService.createAll(countriesList);
            if(logger.isDebugEnabled()){
            	logger.debug(Arrays.toString(callDetailList.toArray()));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "countrieslistPage";
    }
}
