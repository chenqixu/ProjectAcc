package com.cqx.acc.service.bean.intf;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccBody")
public class AccBody {
	private List<Object> body = null;
	public List<Object> getBody() {
		return body;
	}
	public void setBody(List<Object> body) {
		this.body = body;
	}
}
