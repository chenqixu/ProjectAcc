package com.cqx.acc.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.cqx.acc.service.bean.login.LoginRequestObject;
import com.cqx.acc.service.bean.login.LoginResponseObject;

@WebService(name = "LoginService", targetNamespace = "http://service.acc.cqx.com/")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface LoginService {
	public @WebResult(name = "message")LoginResponseObject LoginCheck(@WebParam(name = "message")
	LoginRequestObject requestObj);
	
	public @WebResult(name = "message")LoginResponseObject syncTime(@WebParam(name = "message")
	LoginRequestObject requestObj);
}
