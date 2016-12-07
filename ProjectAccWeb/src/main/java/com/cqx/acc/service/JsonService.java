package com.cqx.acc.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.cqx.acc.service.bean.json.JsonRequestObject;
import com.cqx.acc.service.bean.json.JsonResponseObject;

@WebService(name = "JsonService", targetNamespace = "http://service.acc.cqx.com/")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface JsonService {
	public @WebResult(name = "message")JsonResponseObject qry(@WebParam(name = "message")
	JsonRequestObject requestObj);
	
	public @WebResult(name = "message")JsonResponseObject insert(@WebParam(name = "message")
	JsonRequestObject requestObj);
	
	public @WebResult(name = "message")JsonResponseObject update(@WebParam(name = "message")
	JsonRequestObject requestObj);
}
