package com.mumtel.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ymukhtar
 *
 */
public class CommonUtility {
	public static int FETCH_SIZE=10;
	public static Map<Integer, String> MONTHS=new HashMap<Integer, String>();
	public static List<String> YEARS=new ArrayList<String>();
	static{
		MONTHS.put(1, "January");
		MONTHS.put(2, "Feburary");
		MONTHS.put(3, "March");
		MONTHS.put(4, "April");
		MONTHS.put(5, "May");
		MONTHS.put(6, "June");
		MONTHS.put(7, "July");
		MONTHS.put(8, "August");
		MONTHS.put(9, "September");
		MONTHS.put(10, "October");
		MONTHS.put(11, "November");
		MONTHS.put(12, "December");
		
		YEARS.add("2010");
		YEARS.add("2011");
		YEARS.add("2012");
		YEARS.add("2013");
		YEARS.add("2014");
	}
}
