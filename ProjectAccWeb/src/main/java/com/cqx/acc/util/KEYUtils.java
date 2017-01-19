package com.cqx.acc.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cqx.acc.service.client.SyncTimeClient;

public class KEYUtils {
	private static Map<String, String> keylist = new HashMap<String, String>();
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
	static {
		keylist.put("cqx", "61832587718812523095");
		keylist.put("cry", "81239571609091726356");
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
	        e.printStackTrace();
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
	
	public static String getKEYByNameAndId(String name){
		String result = "";
		String sed = "";
		// 是否本地模式
		if(Constants.isLocal){
			sed = sdf1.format(new Date());
		}else{
			sed = SyncTimeClient.syncTime();
		}
		String id = keylist.get(name);
		result = stringToMD5(sed.substring(0, sed.length()-1)+name+id);		
		return result;
	}

}
