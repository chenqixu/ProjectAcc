package com.cqx.acc.util;

import com.cqx.acc.service.bean.intf.AccRequestHeader;
import com.cqx.acc.service.bean.intf.AccRequestIntf;

public class MessageHelper {

	/**
	 * 自动设置报文头部
	 * */
	public static void autoSetRequestHeader(AccRequestIntf ari, String user_name){
		AccRequestHeader header = new AccRequestHeader();
		header.setRequestname(user_name);
		header.setKey(KEYUtils.getKEYByNameAndId(user_name));
		ari.setHeader(header);
	}
}
