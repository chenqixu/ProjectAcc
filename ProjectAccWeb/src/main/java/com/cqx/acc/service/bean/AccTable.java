package com.cqx.acc.service.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import net.sf.json.JSONObject;

import com.cqx.acc.service.bean.intf.AccAllBean;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccTable")
public class AccTable {
	private String packagestr;
	private String tablename;
	private String tablecolum;
	private AccAllBean requestObjbean;
	public AccAllBean getRequestObjbean() {
		return requestObjbean;
	}
	public void setRequestObjbean(AccAllBean requestObjbean) {
		this.requestObjbean = requestObjbean;
	}
	public String getPackagestr() {
		return packagestr;
	}
	public void setPackagestr(String packagestr) {
		this.packagestr = packagestr;
	}
	public String getTablecolum() {
		return tablecolum;
	}
	public void setTablecolum(String tablecolum) {
		this.tablecolum = tablecolum;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public AccTable jsonToBean(String jsonStr){
		JSONObject jsobj = JSONObject.fromObject(jsonStr);
		return (AccTable)JSONObject.toBean(jsobj, AccTable.class);
	}
}
