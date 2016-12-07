package com.cqx.acc.service.bean.json;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.cqx.acc.service.bean.intf.AccResponseHeader;
import com.cqx.acc.service.bean.intf.AccResponseIntf;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "JsonResponseObject")
public class JsonResponseObject implements AccResponseIntf {
	private AccResponseHeader header = null;
	private List<String> response = null;
	@Override
	public Object getBody() {
		return response;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void setBody(Object body) {
		response = (List<String>)body;
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
