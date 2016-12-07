package com.cqx.acc.util;

import java.io.File;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Constants {
//	public static Logger log = Logger.getLogger(Constants.class);	
	
	public static String spltstr = File.separator;//文件路径分隔符(区分windows和linux)
	public static final boolean isLocal = false;// 是否本地模式

	public static String configPath = getConfigPath();
	
	public static ApplicationContext ctx = null;
		
	public static String getConfigPath(){
		return Constants.class.getClassLoader().getResource("").getPath();
	};
	
	public static void init(){
//		System.out.println("[Constants.class.getResource]:"+Constants.class.getResource(""));
		//日志初始化
//		PropertyConfigurator.configure(configPath+spltstr+"resources"
//				+spltstr+"config"+spltstr+"log4j.properties");
		//服务初始化
		ctx = new ClassPathXmlApplicationContext("ClientBeans.xml");
	}
}
