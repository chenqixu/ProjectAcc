package com.cqx.acc.service.bean.intf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccAllBean")
public class AccAllBean {
	protected String startnum = "0"; // 默认从0开始查询
	protected String pagenum = "-1"; // 默认-1查询全部
	public String getStartnum() {
		return startnum;
	}
	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}
	public String getPagenum() {
		return pagenum;
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
}
