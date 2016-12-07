package com.cqx.acc.service.bean.deal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "AccCountsDailyBean")
public class AccCountsDailyBean {
	private int seq_id;
	private String acc_time;
	private int acc_type;
	private String acc_value;
	private int acc_sts;
	private String acc_desc;
	private int acc_use_type;
	private int acc_card;
	private String acc_use_time;
	private String user_name;	
	private String acc_card_name;
	private String acc_use_name;
	private String acc_type_desc;
	public String getAcc_card_name() {
		return acc_card_name;
	}
	public void setAcc_card_name(String acc_card_name) {
		this.acc_card_name = acc_card_name;
	}
	public String getAcc_use_name() {
		return acc_use_name;
	}
	public void setAcc_use_name(String acc_use_name) {
		this.acc_use_name = acc_use_name;
	}
	public String getAcc_type_desc() {
		return acc_type_desc;
	}
	public void setAcc_type_desc(String acc_type_desc) {
		this.acc_type_desc = acc_type_desc;
	}
	public int getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(int seq_id) {
		this.seq_id = seq_id;
	}
	public String getAcc_time() {
		return acc_time;
	}
	public void setAcc_time(String acc_time) {
		this.acc_time = acc_time;
	}
	public int getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(int acc_type) {
		this.acc_type = acc_type;
	}
	public String getAcc_value() {
		return acc_value;
	}
	public void setAcc_value(String acc_value) {
		this.acc_value = acc_value;
	}
	public int getAcc_sts() {
		return acc_sts;
	}
	public void setAcc_sts(int acc_sts) {
		this.acc_sts = acc_sts;
	}
	public String getAcc_desc() {
		return acc_desc;
	}
	public void setAcc_desc(String acc_desc) {
		this.acc_desc = acc_desc;
	}
	public int getAcc_use_type() {
		return acc_use_type;
	}
	public void setAcc_use_type(int acc_use_type) {
		this.acc_use_type = acc_use_type;
	}
	public int getAcc_card() {
		return acc_card;
	}
	public void setAcc_card(int acc_card) {
		this.acc_card = acc_card;
	}
	public String getAcc_use_time() {
		return acc_use_time;
	}
	public void setAcc_use_time(String acc_use_time) {
		this.acc_use_time = acc_use_time;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
