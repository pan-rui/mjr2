/**
 * 
 */
package com.p2p.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.thoughtworks.xstream.io.json.JsonWriter.Format;

/**
 * 时间工具类
 * @author Administrator
 *
 */
public class DateUtil {
		
	public final static DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	
	public final static DateFormat YYYYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public final static DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	/**
	 * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return YYYY_MM_DD_MM_HH_SS.format(date);
	}
	
	/**
	 * 字符串转换为时间
	 * @param dateString 字符串格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date strToDate(String dateString){
		Date date = null;
		try {
			date = YYYY_MM_DD_MM_HH_SS.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转换为时间
	 * @param dateString 字符串格式yyyy-MM-dd
	 * @return
	 */
	public static Date strYMDToDate(String dateString){
		Date date = null;
		try {
			date = YYYY_MM_DD.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 计算两个时间之间相差的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long diffDays(Date startDate,Date endDate){
		long days = 0;
		long start = startDate.getTime();
		long end = endDate.getTime();
		//一天的毫秒数1000 * 60 * 60 * 24=86400000
		days = (end - start) / 86400000;
		return days;
	}
	
	/**
	 * 日期加上月数的时间
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateAddMonth(Date date,int month){
		return add(date,Calendar.MONTH,month);
	}
	
	
	/**
	 * 日期加上天数的时间
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateAddDay(Date date,int day){
		return add(date,Calendar.DAY_OF_YEAR,day);
	}
	
	/**
	 * 日期加上年数的时间
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date dateAddYear(Date date,int year){
		return add(date,Calendar.YEAR,year);
	}
	
	/**
	 * 日期加上分的时间
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date dateAddMinute(Date date,int minute){
		return add(date,Calendar.MINUTE,minute);
	}
	
	 /** 
     * 计算剩余时间 (多少天多少时多少分)
     * @param startDateStr 
     * @param endDateStr 
     * @return 
     */  
    public static String remainDateToString(Date startDate, Date endDate){  
    	StringBuilder result = new StringBuilder();
    	long times = endDate.getTime() - startDate.getTime();
    	if(times < -1){
    		result.append("过期");
    	}else{
    		long temp = 1000 * 60 * 60 *24;
    		//天数
    		long d = times / temp;

    		//小时数
    		times %= temp;
    		temp  /= 24;
    		long m = times /temp;
    		//分钟数
    		times %= temp;
    		temp  /= 60;
    		long s = times /temp;
    		
    		result.append(d);
    		result.append("天");
    		result.append(m);
    		result.append("小时");
    		result.append(s);
    		result.append("分");
    	}
    	return result.toString();
    }  
    
	private static Date add(Date date,int type,int value){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, value);
		return calendar.getTime();
	}
	 
	/**
	 * 得到当前时间是星期几
	 * @param args
	 * @return
	 */
	public static int getday() {
        Calendar c = Calendar.getInstance();
 
        java.text.Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
 
        return c.get(Calendar.DAY_OF_WEEK);
    }
}
