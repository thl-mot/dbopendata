package com.lauerbach.opendata.efa;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class TestParameterClasses {

	@Test
	public void test() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2016, 7, 25, 13, 45);
		String d = ParameterHelper.getParameters( cal.getTime());
		if (!"&itdDateDay=25&itdDateMonth=08&itdDateYear=2016&itdTimeHour=13&itdTimeMinute=45".equals(d)) {
			fail();
		}
	    System.out.println( d);
	}

}
