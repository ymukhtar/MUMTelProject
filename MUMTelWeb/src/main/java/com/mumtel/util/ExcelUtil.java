package com.mumtel.util;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {


	
	public static int getIntValueFromCell(Cell cell){
		int value;
		 if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()){
			 value=Integer.parseInt(String.valueOf((int)cell.getNumericCellValue()));
         }
		 else{
        	 value=Integer.parseInt(cell.getStringCellValue());
         }
		 return value;
	}
	
	public static double getDoubleValueFromCell(Cell cell){
		double value;
		 if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()){
			 value=cell.getNumericCellValue();
         }
		 else{
        	 value=Double.parseDouble(cell.getStringCellValue());
         }
		 return value;
	}
	
	public static Date getDateValueFromCell(Cell cell){
		if(HSSFDateUtil.isCellDateFormatted(cell)){
			return cell.getDateCellValue();
		}
		else if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()){
			return HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
        }
		 else{
			return HSSFDateUtil.getJavaDate( Double.parseDouble(cell.getStringCellValue()));
        }
	}
}
