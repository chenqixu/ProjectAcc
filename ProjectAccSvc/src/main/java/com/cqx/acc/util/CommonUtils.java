package com.cqx.acc.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class CommonUtils {
	private static final Logger logger = Logger.getLogger(CommonUtils.class);
	/**
	 * 首字母大写
	 * */
	public static String captureName(String name){
		if(name!=null && name.length()>0){
			char[] cs = name.toCharArray();
			cs[0] -= 32;
			return String.valueOf(cs);
		}else{
			return "";
		}
	}
	
	/**
	 * 获得今天yyyy-MM-dd
	 * */
	public static String getNowDate(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now);
	}
	
	/**
	 * 获得上个月的今天yyyy-MM-dd
	 * */
	public static String getLastNowDate(){
		Calendar cale = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		cale.setTime(now);
		cale.add(Calendar.MONTH, -1);
		return sdf.format(cale.getTime());
	}
	
	public static String decode(String str){
		try {
			return java.net.URLDecoder.decode(str, Constants.DECODE);
		} catch (UnsupportedEncodingException e) {
			error(e.getMessage());
		}
		return str;
	}
	
	public static void info(String str){
		if(Constants.ISINFO){
			if(Constants.ISLOGGER){
				logger.info(str);
			}else{
				System.out.println(str);
			}
		}
	}

	public static void debug(String str){
		if(Constants.ISDEBUG){
			if(Constants.ISLOGGER){
				logger.debug(str);
			}else{
				System.out.println(str);
			}
		}
	}

	public static void error(String str){
		if(Constants.ISERR){
			if(Constants.ISLOGGER){
				logger.error(str);
			}else{
				System.out.println(str);
			}
		}
	}
}
