package com.cqx.acc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {	
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
	
	/**
	 * 判断字符串不为空
	 * */
	public static boolean IsNotEmpty(String str){
		boolean flag = false;
		if(str!=null && str.trim().length()>0)flag = true;
		return flag;
	}
}
