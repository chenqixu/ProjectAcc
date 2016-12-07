package com.cqx.acc.service.bean.intf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccResponseHeader")
public class AccResponseHeader {
	private String dealtime = "";
	private int status = 0;
	private String desc = "";
	private int totalcount = 0;
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public String getDealtime() {
		return dealtime;
	}
	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
