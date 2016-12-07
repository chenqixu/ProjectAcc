package com.cqx.acc.service.bean.login;

import com.cqx.acc.service.bean.intf.AccResponseHeader;
import com.cqx.acc.service.bean.intf.AccResponseIntf;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "LoginResponseObject")
public class LoginResponseObject implements AccResponseIntf {
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
