package com.cqx.acc.util;

public class AccStringUtils {
	public static boolean IsNotEmpty(String str){
		boolean flag = false;
		if(str!=null && str.trim().length()>0)flag = true;
		return flag;
	}
}
