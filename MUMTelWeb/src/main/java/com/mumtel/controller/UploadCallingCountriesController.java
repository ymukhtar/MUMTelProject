package com.mumtel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mumtel.model.Country;
import com.mumtel.util.FileuploadForm;
/**
 * 
 * @author ymukhtar
 *
 */
@Controller
public class UploadCallingCountriesController {
	private static Logger logger=Logger.getLogger(UploadCallingCountriesController.class);
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_form";
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
              countriesList.add(new Country(Integer.parseInt(row.getCell(1).getStringCellValue()), row.getCell(0).getStringCellValue()));
            }
            if(logger.isDebugEnabled()){
            	logger.debug(Arrays.toString(countriesList.toArray()));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "import/importDone";
    }
}
