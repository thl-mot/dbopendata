package com.lauerbach.opendata.efa.helper;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.lauerbach.opendata.efa.xml.ItdDateTimeType;
import com.lauerbach.opendata.efa.xml.ItdDateType;
import com.lauerbach.opendata.efa.xml.ItdTimeType;

public class DateElement extends ItdDateTimeType {
	
	static DateTimeFormatter df= DateTimeFormat.forPattern("dd.MM. HH:mm");
	
	public Date getDate() {
		Calendar cal= Calendar.getInstance();
		cal.clear();
		ItdDateType d = getItdDate();
		ItdTimeType t = getItdTime();
		cal.set( d.getYear(), d.getMonth()+1, d.getDay(), t.getHour(), t.getMinute());
		return cal.getTime();
	}
	
	public String toString() {
		return df.print( new DateTime(getDate()));
	}
	
}
