package com.cqx.acc.service.bean.deal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   
@XmlType(name = "DealCountsDailyRequestBean")
public class DealCountsDailyRequestBean {
	private String seq_id = null;//��ˮ
	private String acc_use_time1 = null;//��Ŀ����ʱ��
	private String acc_use_time2 = null;//��Ŀ����ʱ��
	private String acc_type;//��Ŀ����(0:ƽ��1:����,2:֧��;3:͸֧)
	private String acc_value1;//��Ŀֵ
	private String acc_value2;//��Ŀֵ
	private String acc_sts;//��Ŀ�Ƿ���Ч(1:��Ч,0:��Ч)
	private String acc_use_type;//����
	private String acc_card;//�ҵĿ�
	private String acc_desc;//��Ŀ����˵��
	private String user_name;//�û���
	private String startnum = "0"; // Ĭ�ϴ�0��ʼ��ѯ
	private String pagenum = "-1"; // Ĭ��-1��ѯȫ��
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
	public String getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAcc_use_time1() {
		return acc_use_time1;
	}
	public void setAcc_use_time1(String acc_use_time1) {
		this.acc_use_time1 = acc_use_time1;
	}
	public String getAcc_use_time2() {
		return acc_use_time2;
	}
	public void setAcc_use_time2(String acc_use_time2) {
		this.acc_use_time2 = acc_use_time2;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getAcc_value1() {
		return acc_value1;
	}
	public void setAcc_value1(String acc_value1) {
		this.acc_value1 = acc_value1;
	}
	public String getAcc_value2() {
		return acc_value2;
	}
	public void setAcc_value2(String acc_value2) {
		this.acc_value2 = acc_value2;
	}
	public String getAcc_sts() {
		return acc_sts;
	}
	public void setAcc_sts(String acc_sts) {
		this.acc_sts = acc_sts;
	}
	public String getAcc_use_type() {
		return acc_use_type;
	}
	public void setAcc_use_type(String acc_use_type) {
		this.acc_use_type = acc_use_type;
	}
	public String getAcc_card() {
		return acc_card;
	}
	public void setAcc_card(String acc_card) {
		this.acc_card = acc_card;
	}
	public String getAcc_desc() {
		return acc_desc;
	}
	public void setAcc_desc(String acc_desc) {
		this.acc_desc = acc_desc;
	}
}
