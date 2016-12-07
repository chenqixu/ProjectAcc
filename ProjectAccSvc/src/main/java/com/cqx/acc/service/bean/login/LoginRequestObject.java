package com.cqx.acc.service.bean.login;

import com.cqx.acc.service.bean.intf.AccRequestHeader;
import com.cqx.acc.service.bean.intf.AccRequestIntf;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "LoginRequestObject")
public class LoginRequestObject implements AccRequestIntf {
	private AccRequestHeader header = null;
	private LoginRequestBean request = null;
	@Override
	public Object getBody() {
		return request;
	}
	@Override
	public void setBody(Object body) {
		request = (LoginRequestBean)body;
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
