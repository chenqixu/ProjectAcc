package com.cqx.acc.service.bean.intf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccResponseIntf")
public interface AccResponseIntf {
	public Object getBody();
	public void setBody(Object body);
	public AccResponseHeader getHeader();
	public void setHeader(AccResponseHeader header);
}
