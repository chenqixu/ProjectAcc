package com.cqx.acc.service.client;

import com.cqx.acc.service.LoginService;
import com.cqx.acc.service.bean.login.LoginRequestObject;
import com.cqx.acc.service.bean.login.LoginResponseObject;
import com.cqx.acc.util.Constants;

/**
 * 同步系统时间
 * */
public class SyncTimeClient{
	private static LoginService ls = (LoginService)Constants.ctx.getBean("LoginService");
	public static String syncTime(){
		LoginRequestObject request = new LoginRequestObject();
		LoginResponseObject response = ls.syncTime(request);
		if(response.getHeader().getStatus()!=0)return "";
		return (String)response.getBody();
	}
}
