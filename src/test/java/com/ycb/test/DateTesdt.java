package com.ycb.test;

import java.util.Calendar;

import com.ycb.util.DateUtils;

public class DateTesdt {
	
	private static final String old = "yyyy年MM月dd日hh时mm分ss秒";
	private static final String format = "yyyyMMddHHmmss";
	public static void main(String[] args) {
		String startTime=DateUtils.getbjTime();
		System.out.println(startTime);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(DateUtils.paresDate(format, startTime));
		calendar.add(Calendar.MINUTE, 5);
		
		String endTime=DateUtils.formatDate(calendar.getTime(), format);
		System.out.println(endTime);
		
	}
}	
