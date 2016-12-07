package com.cqx.acc.service.bean.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.cqx.acc.service.bean.intf.AccRequestHeader;
import com.cqx.acc.service.bean.intf.AccRequestIntf;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "JsonRequestObject")
public class JsonRequestObject implements AccRequestIntf {
	private AccRequestHeader header = null;
	private String request = null;
	@Override
	public Object getBody() {
		return request;
	}
	@Override
	public void setBody(Object body) {
		request = (String)body;
	}
	@Override
	public AccRequestHeader getHeader() {
		return header;
	}
	@Override
	public void setHeader(AccRequestHeader _header) {
		header = _header;
	}
}
