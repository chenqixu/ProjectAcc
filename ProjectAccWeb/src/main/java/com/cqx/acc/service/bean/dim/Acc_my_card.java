package com.cqx.acc.service.bean.dim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.cqx.acc.service.bean.intf.AccAllBean;

import net.sf.json.JSONObject;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "Acc_my_card")
public class Acc_my_card extends AccAllBean{
	private String acc_my_card;
	private String acc_card_name;
	private String acc_card_desc;
	private String user_name;	
	private String acc_sts;
	public String getAcc_sts() {
		return acc_sts;
	}
	public void setAcc_sts(String acc_sts) {
		this.acc_sts = acc_sts;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAcc_my_card() {
		return acc_my_card;
	}
	public void setAcc_my_card(String acc_my_card) {
		this.acc_my_card = acc_my_card;
	}
	public String getAcc_card_name() {
		return acc_card_name;
	}
	public void setAcc_card_name(String acc_card_name) {
		this.acc_card_name = acc_card_name;
	}
	public String getAcc_card_desc() {
		return acc_card_desc;
	}
	public void setAcc_card_desc(String acc_card_desc) {
		this.acc_card_desc = acc_card_desc;
	}
	public Acc_my_card jsonToBean(String jsonStr){
		JSONObject jsobj = JSONObject.fromObject(jsonStr);
		return (Acc_my_card)JSONObject.toBean(jsobj, Acc_my_card.class);
	}
}
