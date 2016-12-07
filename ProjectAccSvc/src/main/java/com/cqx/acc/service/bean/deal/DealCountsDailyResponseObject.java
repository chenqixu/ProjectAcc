package com.cqx.acc.service.bean.deal;

import java.util.List;

import com.cqx.acc.service.bean.intf.AccResponseHeader;
import com.cqx.acc.service.bean.intf.AccResponseIntf;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "DealCountsDailyResponseObject")
public class DealCountsDailyResponseObject implements AccResponseIntf {
	private AccResponseHeader header = null;
	private List<AccCountsDailyBean> response = null;
	@Override
	public Object getBody() {
		return response;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void setBody(Object body) {
		response = (List<AccCountsDailyBean>)body;
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
