package com.lauerbach.opendata.efa;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParameterHelper {
	
	// itdDateDay=27&
	// itdDateMonth=09&
	// itdDateYear=2016&
	// itdTimeHour=08&
	// itdTimeMinute=53&
	public static String getParameters( Date date) {
		SimpleDateFormat f= new SimpleDateFormat("'&itdDateDay='dd'&itdDateMonth='MM'&itdDateYear='yyyy'&itdTimeHour='HH'&itdTimeMinute='mm");
		if (date!=null) {
			return f.format(date);
		} else {
			return "";
		}
	}
	
	public static String getParameter( String name, String value) {
		try {
			return value!=null ? "&"+name+"="+URLEncoder.encode(value, "UTF-8") : "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static String getParameters(String name, boolean useRealtime) {
		return "&"+name+"="+ (useRealtime ? "1" : "0");
	}
	
	public static String getParameters(String name, Integer value) {
		try {
			return value!=null ? "&"+name+"="+URLEncoder.encode(value.toString(), "UTF-8") : "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
}
