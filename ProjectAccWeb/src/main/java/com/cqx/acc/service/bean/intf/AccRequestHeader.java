package com.cqx.acc.service.bean.intf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccRequestHeader")
public class AccRequestHeader {
	private String requestname = "";
	private String key = "";
	private String requesttime = "";
	private String requestip = "";
	public String getRequestname() {
		return requestname;
	}
	public void setRequestname(String requestname) {
		this.requestname = requestname;
	}
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	public String getRequestip() {
		return requestip;
	}
	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
