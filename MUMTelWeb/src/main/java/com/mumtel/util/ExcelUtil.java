package com.mumtel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"MM/dd/yyyy");

	public static int getIntValueFromCell(Cell cell) {
		int value;
		if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
			value = Integer.parseInt(String.valueOf((int) cell
					.getNumericCellValue()));
		} else {
			value = Integer.parseInt(cell.getStringCellValue().trim());
		}
		return value;
	}

	public static double getDoubleValueFromCell(Cell cell) {
		double value;
		if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
			value = cell.getNumericCellValue();
		} else {
			value = Double.parseDouble(cell.getStringCellValue().trim());
		}
		return value;
	}

	public static Date getDateValueFromCell(Cell cell) {
		try {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				return HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			} else {
				return sdf.parse(cell.getStringCellValue().trim());
			}
		} catch (Exception e) {
			System.out.println("Unable to get Date Value" + cell);
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				return HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			} else {
				try {
					return sdf.parse(cell.getStringCellValue().trim());
				} catch (ParseException pe) {
					return null;
				}
			}

		}
	}
}
