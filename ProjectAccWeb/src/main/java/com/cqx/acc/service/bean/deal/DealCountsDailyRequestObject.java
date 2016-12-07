package com.cqx.acc.service.bean.deal;

import com.cqx.acc.service.bean.intf.AccRequestHeader;
import com.cqx.acc.service.bean.intf.AccRequestIntf;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "DealCountsDailyRequestObject")
public class DealCountsDailyRequestObject implements AccRequestIntf {
	private AccRequestHeader header = null;
	private DealCountsDailyRequestBean request = null;
	@Override
	public Object getBody() {
		return request;
	}
	@Override
	public void setBody(Object body) {
		request = (DealCountsDailyRequestBean)body;
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
