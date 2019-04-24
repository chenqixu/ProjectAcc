package com.cqx.acc.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KEYUtils {
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String getKEYByNameAndId(String name, String id){
		String result = "";
		String sed = getNowTime();
//		result = DigestUtils.md5Hex(sed.substring(0, sed.length()-1)+name+id);
		result = stringToMD5(sed.substring(0, sed.length()-1)+name+id);
		CommonUtils.debug(sed);
		CommonUtils.debug(sed.substring(0, sed.length()-1));
		CommonUtils.debug(sed.substring(0, sed.length()-1)+name+id);
		CommonUtils.debug(result);
		return result;
	}
	public static String getNowTime(){
		return sdf1.format(new Date());
	}
	
	/**
	 * 将字符串转成MD5值
	 * 
	 * @param string
	 * @return
	 */
	public static String stringToMD5(String string) {
	    byte[] hash;
	    try {
	        hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	    } catch (Exception e) {
	    	CommonUtils.error(e.getMessage());
	        return null;
	    }
	    StringBuilder hex = new StringBuilder(hash.length * 2);
	    for (byte b : hash) {
	        if ((b & 0xFF) < 0x10)
	            hex.append("0");
	        hex.append(Integer.toHexString(b & 0xFF));
	    }
	    return hex.toString();
	}
//	public static void main(String[] args) {
//		System.out.println(new KEYUtils().getKEYByNameAndId("cqx", "61832587718812523095"));
//	}
}
