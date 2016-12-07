package com.cqx.acc.service.bean.intf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccRequestIntf")
public interface AccRequestIntf {
	public Object getBody();
	public void setBody(Object body);
	public AccRequestHeader getHeader();
	public void setHeader(AccRequestHeader header);
}
