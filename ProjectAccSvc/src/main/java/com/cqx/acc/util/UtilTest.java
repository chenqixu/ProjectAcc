package com.cqx.acc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

import com.cqx.acc.service.JsonService;
import com.cqx.acc.service.bean.AccTable;
import com.cqx.acc.service.bean.dim.Acc_my_card;
import com.cqx.acc.service.bean.json.JsonRequestObject;
import com.cqx.acc.util.xml.CallWebService;
import com.cqx.acc.util.xml.ResultXML;
import com.cqx.acc.util.xml.XMLData;

public class UtilTest {
	public static void main(String[] args) throws Exception{
//		AccTable at = new AccTable();
//		at.setPackagestr("com.cqx.acc.service.bean.dim");
//		at.setTablename("acc_my_card");
//		at.setTablecolum("acc_my_card,acc_card_name,acc_card_desc");
//		System.out.println(JSONObject.fromObject(at).toString());
		
//		Constants.init();

//		LoginRequestBean qb = new LoginRequestBean();
//		String sql = "select count(1) as success from acc_user where username=? and password=? ";
//		qb.setUsername("admin");
//		qb.setPassword("123456");
//		System.out.println(sql);
//		List<Object> result_list = DataSourceUtils.query(sql, qb, QrySuccessBean.class);
//		for(int i=0;i<result_list.size();i++){
//			System.out.println("[result"+i+"]"+((QrySuccessBean)result_list.get(i)).getSuccess());
//		}

//		DealCountsDailyRequestBean bean = new DealCountsDailyRequestBean();
//		String sql = "select seq_id,acc_time,acc_type,acc_value,acc_sts,acc_desc,t1.acc_use_type,acc_use_time,"
//				+"user_name "
//				+",t2.acc_card_name,t3.acc_use_name "
//				+",case when acc_type=0 then '平衡' when acc_type=1 then '收入' when acc_type=2 then '支出' when acc_type=3 then '透支' end as acc_type_desc "
//				+" from acc_counts_daily t1 "
//				+" left join acc_my_card t2 on t1.acc_card=t2.acc_my_card "
//				+" left join acc_use_type t3 on t1.acc_use_type=t3.acc_use_type "				
//				+" where acc_sts=1 and acc_use_time >= ? and acc_use_time <= ?  order by seq_id limit 0,1";
//		bean.setAcc_use_time1("2016-07-03");
//		bean.setAcc_use_time2("2016-07-31");
//		bean.setAcc_desc("");
//		List<Object> result_list = DataSourceUtils.query(sql, bean, AccCountsDailyBean.class);
//		System.out.println(sql);
//		for(int i=0;i<result_list.size();i++){
//			System.out.print("[result"+i+"]");
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getSeq_id());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_time());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_type());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_value());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_sts());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_desc());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_use_type());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_use_time());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getUser_name());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_card_name());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_use_name());
//			System.out.print("|"+((AccCountsDailyBean)result_list.get(i)).getAcc_type_desc());
//			System.out.println();
//		}
//		result_list = DataSourceUtils.query(sql, bean, AccCountsDailyBean.class);
//		System.out.println(result_list.size());
		
//		DealCountsDailyRequestBean bean = new DealCountsDailyRequestBean();
//		StringBuffer sql = new StringBuffer();
//		bean.setAcc_type("1");
//		bean.setAcc_value1("2");
//		bean.setAcc_sts("1");
//		bean.setAcc_desc("圣诞测试");
//		bean.setAcc_use_type("1");
//		bean.setAcc_use_time1("2015-12-03");
//		bean.setUser_name("admin");
//		sql.append("insert into acc_counts_daily(seq_id,acc_time,acc_type,acc_value,acc_sts,acc_desc,"+
//				"acc_use_type,acc_use_time,user_name) values(NEXTVAL('CountsDailySeq'),now(),?,?,?,?,?,?,?) ");
//		int result_code = DataSourceUtils.update(sql.toString(), bean);
//		System.out.println(String.valueOf(result_code));
		
//		DealCountsDailyRequestBean bean = new DealCountsDailyRequestBean();
//		StringBuffer sql = new StringBuffer();
//		bean.setAcc_value1("2");
//		bean.setSeq_id("4");
//		sql.append("update acc_counts_daily set acc_value = ? where seq_id = ? ");
//		int result_code = DataSourceUtils.update(sql.toString(), bean);
//		System.out.println(result_code);
		

//		int totalcount = 31;
//		int pagecount = 15;
//		int startcount = 0;
//		int page = totalcount/pagecount;
//		if(totalcount%pagecount>0){
//			page++;
//		}
//		System.out.println(page);
		
//		// 今天
//		Date now = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sdf.format(now));
//		// 一个月前
//		Calendar cale = Calendar.getInstance();
//		cale.setTime(now);
//		cale.add(Calendar.MONTH, -1);
//		System.out.println(sdf.format(cale.getTime()));
		
//		String sql = "select 1 from a limit 12";
//		String v_totalcount_sql = sql.substring(0,sql.indexOf("limit"));
//		v_totalcount_sql = "select count(1) "+v_totalcount_sql.substring(v_totalcount_sql.indexOf("from"), v_totalcount_sql.length());
//		System.out.println(v_totalcount_sql);
		
//		Acc_my_card requestBean = new Acc_my_card();
//		AccTable at = new AccTable();
//		at.setPackagestr("com.cqx.acc.service.bean.dim");
//		at.setTablename("acc_my_card");
//		at.setTablecolum("acc_my_card,acc_card_name,acc_card_desc");
//		at.setRequestObjbean(requestBean);
//		System.out.println(JSONObject.fromObject(at).toString());
		
//		System.out.println(KEYUtils.stringToMD5("cqx"));
//		System.out.println(KEYUtils.stringToMD5("cry"));
//		System.out.println(KEYUtils.stringToMD5("123456"));
		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		    System.out.println(DriverManager.getConnection(
//		    		"jdbc:mysql://sqld.duapp.com:4050/waeSrvunFllmFRkiGfgY",
//		    		"e52daa1c782340d29564a7d587f33e63", "3ad0a3f419b9459d8f5c1a07a0ab032c"));
//		} catch (Exception e) {
//			CommonUtils.error(e.getMessage());
//		}
		
		loginTest("cqx", "cqx", "61832587718812523095");
	}
	
	public static void TestClass(Object obj) throws Exception{
		System.out.println(obj.getClass().getName());
		Field fields[] = obj.getClass().getDeclaredFields();
		//在使用java反射机制获取 JavaBean 的属性值时，如果该属性被声明为private 的
		//需要将setAccessible设置为true. 默认的值为false.
		Field.setAccessible(fields, true);
		for(int i=0;i<fields.length;i++){
			if(fields[i].get(obj)!=null){
				System.out.println(fields[i].getName()+" "+fields[i].get(obj)+" " +fields[i].get(obj).getClass());
				if(fields[i].get(obj) instanceof String){
					if(AccStringUtils.IsNotEmpty((String)fields[i].get(obj))){
						Object tmpc = obj.getClass().newInstance();
						Method methodset = obj.getClass().getDeclaredMethod("set"+CommonUtils.captureName(fields[i].getName()),String.class);
						methodset.invoke(tmpc, null);
					}
					System.out.println("is string");
				}
			}
		}
	}
	
	public static void TestClass(Class c) throws Exception {
		Object obj = c.newInstance();
		Field fields[] = c.getDeclaredFields();
		Field.setAccessible(fields, true);
		for(int i=0;i<fields.length;i++){
			Method methodset = c.getDeclaredMethod("set"+fields[i].getName().toUpperCase(),
					fields[i].getType());
			methodset.invoke(obj, "a");
			Method methodget = c.getDeclaredMethod("get"+fields[i].getName().toUpperCase(), null);
			System.out.println(methodget.invoke(obj, null));
		}		
	}
	
	/**
	 * 登陆测试
	 * */
	public static void loginTest(String username, String password, String id) throws Exception {
		String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.acc.cqx.com/\"><soapenv:Header/><soapenv:Body><ser:LoginCheck><message><header>"
    			+"<requestname>"+username+"</requestname>"
    			+"<key>"+KEYUtils.getKEYByNameAndId(username, id)+"</key>"
    			+"<requesttime></requesttime><requestip></requestip></header><request>"
    			+"<username>"+username+"</username>"
    			// 对密码进行md5加密
    			+"<password>"+KEYUtils.stringToMD5(password)+"</password>"
    			+"</request></message></ser:LoginCheck></soapenv:Body></soapenv:Envelope>";
		byte[] data = soap.getBytes();
    	String sUrl = "http://localhost:8080/ProjectAccSvc/LoginService";
    	CallWebService cws = new CallWebService();
    	String resultxml = cws.doAction(sUrl, data);
        if (resultxml.length()>0) {
            // 解析返回信息
        	ResultXML rx = new ResultXML();
    		StringBuffer xml = new StringBuffer();
    		xml.append("<?xml version=\"1.0\"  encoding='UTF-8'?>");
    		xml.append(resultxml);
    		XMLData xd = new XMLData(xml.toString());
    		rx.rtFlag = true;
    		rx.bXmldata = true;
    		rx.xmldata = xd;
    		rx.setbFlag(false);
    		// 先看状态，再看返回值
    		rx.resetParent().node("Body").node("LoginCheckResponse").node("message").node("header").setParentPointer();
    		rx.setRowFlagInfo("status");
    		rx.First();
    		 if (rx.isEof()) { // 没有结果
             	System.out.println("status没有结果");
    		 } else {
             	String status = rx.getRowValue();
             	System.out.println("[status]"+status);
    		 }
    		rx.resetParent().node("Body").node("LoginCheckResponse").node("message").setParentPointer();
    		rx.setRowFlagInfo("response");
    		rx.First();
            if (rx.isEof()) { // 没有结果
            	System.out.println("response没有结果");
            } else { // 有结果
            	String logincode = rx.getRowValue();
            	System.out.println("[logincode]"+logincode);
            }
        }
	}
}
