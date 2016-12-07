package com.cqx.acc.service.impl;

import java.util.Iterator;
import java.util.List;

import com.cqx.acc.service.LoginService;
import com.cqx.acc.service.bean.login.LoginRequestBean;
import com.cqx.acc.service.bean.login.LoginRequestObject;
import com.cqx.acc.service.bean.login.LoginResponseObject;
import com.cqx.acc.service.bean.login.QrySuccessBean;
import com.cqx.acc.util.CommonUtils;
import com.cqx.acc.util.DataSourceUtils;
import com.cqx.acc.util.KEYUtils;
import com.cqx.acc.util.MessageHelper;

import javax.jws.WebService;

@WebService(endpointInterface = "com.cqx.acc.service.LoginService",serviceName="LoginService",targetNamespace="http://service.acc.cqx.com/")
public class LoginServiceImpl implements LoginService {
	
	@Override
	public LoginResponseObject LoginCheck(LoginRequestObject requestObj) {
		LoginResponseObject responseObj = new LoginResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		int resultcode = 0;
		LoginRequestBean requestbean = (LoginRequestBean)requestObj.getBody();
		String sql = "select count(1) as success from acc_user where username=? and password=? ";
		CommonUtils.info("[sql]"+sql);
		List<Object> result_list = DataSourceUtils.query(sql, requestbean, QrySuccessBean.class).getResult();
		Iterator<Object> it = result_list.iterator();
		while(it.hasNext()){
			QrySuccessBean result = (QrySuccessBean)it.next();
			resultcode = result.getSuccess();
		}
		responseObj.setBody(String.valueOf(resultcode));
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}	

	@Override
	public LoginResponseObject syncTime(LoginRequestObject requestObj) {
		LoginResponseObject responseObj = new LoginResponseObject();
		MessageHelper.autoSetRespHeader(responseObj);
		responseObj.setBody(KEYUtils.getNowTime());
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}
}
