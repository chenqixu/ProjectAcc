package com.cqx.acc.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.cqx.acc.service.bean.AccBooleanResponseObject;
import com.cqx.acc.service.bean.deal.DealCountsDailyRequestObject;
import com.cqx.acc.service.bean.deal.DealCountsDailyResponseObject;

@WebService(name = "DealCountsDailyService", targetNamespace = "http://service.acc.cqx.com/")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface DealCountsDailyService {
	public @WebResult(name = "message")DealCountsDailyResponseObject qryCountsDaily(@WebParam(name = "message")
	DealCountsDailyRequestObject requestObj);
	
	public @WebResult(name = "message")AccBooleanResponseObject insertCountsDaily(@WebParam(name = "message")
	DealCountsDailyRequestObject requestObj);

	public @WebResult(name = "message")AccBooleanResponseObject updateCountsDaily(@WebParam(name = "message")
	DealCountsDailyRequestObject requestObj);

	public @WebResult(name = "message")AccBooleanResponseObject delCountsDaily(@WebParam(name = "message")
	DealCountsDailyRequestObject requestObj);

	public @WebResult(name = "message")AccBooleanResponseObject disableCountsDaily(@WebParam(name = "message")
	DealCountsDailyRequestObject requestObj);
}
