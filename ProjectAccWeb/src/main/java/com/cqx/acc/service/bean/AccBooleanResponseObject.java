package com.cqx.acc.service.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.cqx.acc.service.bean.intf.AccResponseHeader;
import com.cqx.acc.service.bean.intf.AccResponseIntf;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccBooleanResponseObject")
public class AccBooleanResponseObject implements AccResponseIntf {
	private AccResponseHeader header = null;
	private String response = null;
	@Override
	public Object getBody() {
		return response;
	}
	@Override
	public void setBody(Object body) {
		response = (String)body;
	}
	@Override
	public AccResponseHeader getHeader() {
		return header;
	}
	@Override
	public void setHeader(AccResponseHeader _header) {
		header = _header;
	}
}
