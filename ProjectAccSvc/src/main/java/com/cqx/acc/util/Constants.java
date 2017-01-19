package com.cqx.acc.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.cqx.acc.properites.bean.Db;

public class Constants {
	public static final boolean ISLOGGER = false;
	public static final boolean ISINFO = false;
	public static final boolean ISDEBUG = false;
	public static final boolean ISERR = true;
	public static final boolean IS_PERMIT = true; // 是否鉴权，开发时候用false
	public static final String DECODE = "UTF-8";
	
	public static String spltstr = File.separator;//文件路径分隔符(区分windows和linux)

	public static String configPath = getConfigPath();
	
	private static Resource dbResource = new ClassPathResource(spltstr+
			"resources"+spltstr+"config"+spltstr+"db.properties");
	private static Properties props = null;
		
	public static String getConfigPath(){
		return Constants.class.getClassLoader().getResource("").getPath();
	};
	
	@Autowired
	private Db db;	
	public static String dbDriver = "";	
	public static String dbUserName = "";	
	public static String dbPassword = "";	
	public static String dbURL = "";

	public Db getDb() {
		return db;
	}
	
	/**
	 * 通过id获得配置文件common.properties的值
	 * */
	public static String getCommonPropertiesValueById(String id){
		String value = "";
		try{
			value = props.getProperty(id);
		}catch(Exception e){
			CommonUtils.error(e.getMessage());
		}
		return value;
	}
	
	public static void init(){
		//日志初始化
		PropertyConfigurator.configure(configPath+spltstr+"resources"
				+spltstr+"config"+spltstr+"log4j.properties");
		try {
			//配置初始化
			props  = PropertiesLoaderUtils.loadProperties(dbResource);
		} catch (IOException e) {
			CommonUtils.error(e.toString());
		}
	}
}
