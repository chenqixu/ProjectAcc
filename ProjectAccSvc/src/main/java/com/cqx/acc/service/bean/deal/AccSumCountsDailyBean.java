package com.cqx.acc.service.bean.deal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccSumCountsDailyBean")
public class AccSumCountsDailyBean {
	private String acc_value;
	public String getAcc_value() {
		return acc_value;
	}
	public void setAcc_value(String acc_value) {
		this.acc_value = acc_value;
	}
}
