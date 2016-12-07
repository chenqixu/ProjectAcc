package com.cqx.acc.util;

import java.io.File;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Constants {
//	public static Logger log = Logger.getLogger(Constants.class);	
	
	public static String spltstr = File.separator;//�ļ�·���ָ���(����windows��linux)
	public static final boolean isLocal = false;// �Ƿ񱾵�ģʽ

	public static String configPath = getConfigPath();
	
	public static ApplicationContext ctx = null;
		
	public static String getConfigPath(){
		return Constants.class.getClassLoader().getResource("").getPath();
	};
	
	public static void init(){
//		System.out.println("[Constants.class.getResource]:"+Constants.class.getResource(""));
		//��־��ʼ��
//		PropertyConfigurator.configure(configPath+spltstr+"resources"
//				+spltstr+"config"+spltstr+"log4j.properties");
		//�����ʼ��
		ctx = new ClassPathXmlApplicationContext("ClientBeans.xml");
	}
}
