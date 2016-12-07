package com.cqx.acc.service.client;

import com.cqx.acc.service.LoginService;
import com.cqx.acc.service.bean.login.LoginRequestBean;
import com.cqx.acc.service.bean.login.LoginRequestObject;
import com.cqx.acc.service.bean.login.LoginResponseObject;
import com.cqx.acc.util.Constants;
import com.cqx.acc.util.KEYUtils;
import com.cqx.acc.util.MessageHelper;

public class LoginClient {
	public boolean checkLogin(LoginRequestBean bean){
		boolean flag = false;
		LoginService ls = (LoginService)Constants.ctx.getBean("LoginService");
		LoginRequestObject request = new LoginRequestObject();
		// ���������md5����
		bean.setPassword(KEYUtils.stringToMD5(bean.getPassword()));
		request.setBody(bean);
		// �Զ����ñ���ͷ
		MessageHelper.autoSetRequestHeader(request, bean.getUsername());
		LoginResponseObject response = ls.LoginCheck(request);
		// ��Ȩʧ��
		if(response.getHeader().getStatus()!=0)return false;
		int result_code = Integer.valueOf((String)response.getBody());
		if(result_code>0)flag = true;
		return flag;
	}
	
	public String syncTime(){
		LoginService ls = (LoginService)Constants.ctx.getBean("LoginService");
		LoginRequestObject request = new LoginRequestObject();
		LoginResponseObject response = ls.syncTime(request);
		if(response.getHeader().getStatus()!=0)return "";
		return (String)response.getBody();
	}
}
