package com.ycb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author chenghui
 *
 */
public class DateUtils {
	
	/**
	 * 获取当前时间的字符串
	 * @param format
	 * @return
	 */
	public static 	String  getCurrDate(String  format){
			Date  date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			return sdf.format(date);
	}
	/**
	 * 格式化时间 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static  Date formatString(String date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 相互转化
	 * @param oldformat
	 * @param format
	 * @param value
	 * @return
	 */
	public static String format(String oldformat,String format,String value){
		  Date date = null;
	        if(value != null && !"".equals(value)) {
	            try {
	                if("date".equals(oldformat)) {
	                    date = new Date(value);
	                } else {
	                    date = getDateFormate(oldformat).parse(value);
	                }

	                return getDateFormate(format).format(date);
	            } catch (ParseException var5) {
	                var5.printStackTrace();
	                return "";
	            }
	        } else {
	            return "";
	        }
		
	}
	
	public static SimpleDateFormat getDateFormate(String format) {
        return StringUtils.isEmpty(format)?null:(format.equals("yyyyMMdd")?new SimpleDateFormat("yyyyMMdd"):(format.equals("yyyy")?new SimpleDateFormat("yyyy"):(format.equals("yyyyMMdd_")?new SimpleDateFormat("yyyy-MM-dd"):(format.equals("yyyyMMdd_IU")?new SimpleDateFormat("yyyy/MM/dd"):(format.equals("yyyyMMddC")?new SimpleDateFormat("yyyy年MM月dd日"):(format.equals("yyyyMM")?new SimpleDateFormat("yyyyMM"):(format.equals("yyyyMM_")?new SimpleDateFormat("yyyy-MM"):(format.equals("yyyy")?new SimpleDateFormat("yyyy"):(format.equals("HHmmss")?new SimpleDateFormat("HHmmss"):(format.equals("HHmmss_")?new SimpleDateFormat("HH:mm:ss"):(format.equals("HHmmssC")?new SimpleDateFormat("HH点mm分ss秒"):(format.equals("yyyyMMddHHmmss")?new SimpleDateFormat("yyyyMMddHHmmss"):(format.equals("yyyyMMddHHmmssC")?new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒"):(format.equals("yyyyMMddHHmmssSSS")?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"):(format.equals("yyyyMMddHHmmss_")?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"):(format.equals("yyyyMMddHHmmss_UI")?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"):(format.equals("yyyyMMddHHmmss_IU")?new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"):new SimpleDateFormat(format))))))))))))))))));
    }

	
	public static Date paresDate(String format,String date) {
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
    }
	
	 public static String getFormatedDateString(float timeZoneOffset){
	        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
	            timeZoneOffset = 0;
	        }
	        
	        int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
	        TimeZone timeZone;
	        String[] ids = TimeZone.getAvailableIDs(newTime);
	        if (ids.length == 0) {
	            timeZone = TimeZone.getDefault();
	        } else {
	            timeZone = new SimpleTimeZone(newTime, ids[0]);
	        }
	    
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        sdf.setTimeZone(timeZone);
	        return sdf.format(new Date());
	    }
	 
	 public static String getbjTime(){
		 String betime=getFormatedDateString(8);
		 return format("yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", betime);
	 }
	 
	 public static Date formatDate(String date,String format){
		 	SimpleDateFormat  sdf=new SimpleDateFormat(format);
		 	try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	 }
}
