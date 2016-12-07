package com.cqx.acc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.cxf.common.util.StringUtils;

public class DataSourceUtils {
	private static String dbDriver;
	private static String dbUserName;
	private static String dbPassword;
	private static String dbURL;
	
	private static DataSource dataSource;
	
	static {
		initConfigure();
	}	

	/**
	 * ��ʼ������Դ
	 * */
	public static void initConfigure() {
		//���ó�ʼ��
		dbDriver = Constants.getCommonPropertiesValueById("driver");
		dbUserName = Constants.getCommonPropertiesValueById("username");
		dbPassword = Constants.getCommonPropertiesValueById("password");
		dbURL = Constants.getCommonPropertiesValueById("url");
//		dbDriver = "com.mysql.jdbc.Driver";
//		dbUserName = "acc";
//		dbPassword = "123456";
//		dbURL = "jdbc:mysql://127.0.0.1:3306/accacount?useUnicode=true&characterEncoding=gbk";
		CommonUtils.info("##Initialize the configuration file DataSourceUtils.dbDriver:"+dbDriver);
		CommonUtils.info("##Initialize the configuration file DataSourceUtils.dbUserName:"+dbUserName);
		CommonUtils.info("##Initialize the configuration file DataSourceUtils.dbPassword:"+dbPassword);
		CommonUtils.info("##Initialize the configuration file DataSourceUtils.dbURL:"+dbURL);
//		//�������Դ
//		dataSource = DataSourceUtils.setupDataSource(dbDriver,
//		dbUserName, dbPassword, dbURL);
	}
	
	/**
	 * �������Դ
	 * */
	public static DataSource getConfSource(){
		if(dataSource==null){
			CommonUtils.info("##DataSourceUtils Not initialized configuration, data source!##");
		}
		return dataSource;
	}
	
	/**
	 * ��ȡ����
	 * */
	public static Connection getConnection(DataSource dataSource)
			throws SQLException {
		return dataSource.getConnection();
	}
	
	/**
	 * ��ȡ����
	 * */
	public static Connection getConnection(){
		try {
			Class.forName(dbDriver);
		    return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
		} catch (Exception e) {
			CommonUtils.error(e.getMessage());
		}
		return null;
	}

	/**
	 * �ر�����
	 * */
	public static void release(Connection connection, Statement statement,
			ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				CommonUtils.error(e.getMessage());
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				CommonUtils.error(e.getMessage());
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				CommonUtils.error(e.getMessage());
			}
		}
	}

	/**
	 * �������ӳ�
	 * 
	 * @param connectURI
	 * @return
	 */
	public static DataSource setupDataSource(String driver, String username,
			String password, String url) {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setUrl(url);
		// �������
		ds.setMaxActive(5);
		// ����������
		ds.setMinIdle(5);
		// ��С��������
		ds.setMaxIdle(2);
		return ds;
	}

	public static void printDataSourceStats(DataSource ds) {
		BasicDataSource bds = (BasicDataSource) ds;
		CommonUtils.info("NumActive: " + bds.getNumActive());
		CommonUtils.info("NumIdle: " + bds.getNumIdle());
	}

	public static void shutdownDataSource(DataSource ds) throws SQLException {
		BasicDataSource bds = (BasicDataSource) ds;
		bds.close();
	}
	
	/**
	 * �ַ��������ֽ�β,����ǰ����ַ���
	 * */
	private static String getStringCutNum(String str){
		String result_str = str;
		boolean flag = true;
		while(flag){
			if(result_str.endsWith("0")|| result_str.endsWith("1")|| result_str.endsWith("2")
					||result_str.endsWith("3")|| result_str.endsWith("4")|| result_str.endsWith("5")
					||result_str.endsWith("6")|| result_str.endsWith("7")|| result_str.endsWith("8")
					|| result_str.endsWith("9")){
				result_str = result_str.substring(0, result_str.length()-1);
			}else{
				flag = false;
			}
		}
		return result_str;
	}
	
	/**
	 * ��������bean,��˳�򷵻�PreparedStatement
	 * sql�п��������ֶκͱ���һ�µ���˳�򲻶ԣ�
	 * ����Ҫ��Ӧ����������޳�insert into .. (ǰ���޳�update .. setǰ���޳�select ... whereǰ
	 * */
	public static List<AccKeyValue> getNameValueByObj(Object obj, String _sql){
		String sql = _sql;
		if(!StringUtils.isEmpty( sql)){
			if(sql.indexOf("insert into")>=0){
				sql = sql.substring(sql.indexOf("(")+"(".length());
			}else if(sql.indexOf("update ")>=0){
				sql = sql.substring(sql.indexOf(" set ")+" set ".length());
			}else if(sql.indexOf("select")>=0){
				sql = sql.substring(sql.indexOf("where")+"where".length());
			}
		}
		List<AccKeyValue> result_list = new ArrayList<AccKeyValue>();
		try{
			Field fields[] = obj.getClass().getDeclaredFields();
			//��ʹ��java������ƻ�ȡ JavaBean ������ֵʱ����������Ա�����Ϊprivate ��
			//��Ҫ��setAccessible����Ϊtrue. Ĭ�ϵ�ֵΪfalse.
			Field.setAccessible(fields, true);
			for(int i=0;i<fields.length;i++){
				AccKeyValue tmpAccKV = new AccKeyValue();
				tmpAccKV.setKey(fields[i].getName());
				// �ж��Ƿ��ֵ
				if(fields[i].get(obj)==null){
					continue;
				}
				// �����ֵ���ж��Ƿ���String���ͣ��жϳ����Ƿ�Ϊ0
				if(fields[i].get(obj).getClass()==String.class && ((String)fields[i].get(obj)).length()==0){
					continue;
				}
				tmpAccKV.setValue(fields[i].get(obj));
				tmpAccKV.setValue_class(fields[i].get(obj).getClass());
				//�Ҳ�����Ӧ�ַ���,����ȥ��ĩβ���ֺ�����ҵ���Ӧ�ַ�����
				if(sql.indexOf(fields[i].getName())<0 && sql.indexOf(getStringCutNum(fields[i].getName()))>=0){
					//ȥ��ĩβ���ֺ��λ�ü���ĩβ����
					tmpAccKV.setIndex(sql.indexOf(getStringCutNum(fields[i].getName()))
							+Integer.valueOf(fields[i].getName().substring(fields[i].getName().length()-1,fields[i].getName().length())));
				}else{
					tmpAccKV.setIndex(sql.indexOf(fields[i].getName()));
				}
				if(tmpAccKV.getIndex()>=0)
					result_list.add(tmpAccKV);
			}
			Collections.sort(result_list);
		}catch(Exception e){
			CommonUtils.error(e.getMessage());
		}
		return result_list;
	}
	
	/**
	 * �����ֶ�,����Map<�ֶ�����,�ֶ�����>
	 * */
	private static Map<String, Class<?>> getMapByFields(Field fields[]){
		Map<String, Class<?>> result_map = new HashMap<String, Class<?>>();
		for(Field f:fields){
			result_map.put(f.getName(), f.getType());
		}		
		return result_map;
	}	
	
	/**
	 * ��ѯ,ͨ����ѯsql���,��ѯ����bean���ؽ��bean
	 * */
	public static AccResultSet query(String sql, Object obj, Class<?> c){
		AccResultSet ars = new AccResultSet();
		List<Object> result_list = new ArrayList<Object>();
		PreparedStatement pst = null;
		PreparedStatement totalpst = null;
		ResultSet rs = null;
		ResultSet totalrs = null;
		Connection conn = null;
		try{
//			conn = getConnection(getConfSource());
			conn = getConnection();
			if(conn!=null){
				// ���ж�sql���Ƿ���limit�������limit���Ȳ�ѯ�ܼ�¼��
				if(sql.indexOf("limit")>0){
					String v_totalcount_sql = sql.substring(0, sql.indexOf("limit"));
					v_totalcount_sql = "select count(1) "+v_totalcount_sql.substring(v_totalcount_sql.indexOf("from"), v_totalcount_sql.length());
					totalpst = conn.prepareStatement(v_totalcount_sql);
					if(obj!=null){
						List<AccKeyValue> param_list = getNameValueByObj(obj, v_totalcount_sql);
						for(int i=0;i<param_list.size();i++){
							if(param_list.get(i).getValue_class() == String.class){
								totalpst.setString(i+1, (String)param_list.get(i).getValue());
							}else if(param_list.get(i).getValue_class() == int.class){
								totalpst.setInt(i+1, (Integer)param_list.get(i).getValue());						
							}else if(param_list.get(i).getValue_class() == Date.class){
								totalpst.setDate(i+1, (Date)param_list.get(i).getValue());
							}
						}
					}
					totalrs = totalpst.executeQuery();
					while (totalrs.next() != false) {						
						ars.setTotalcount(totalrs.getInt(1));
						break;
					}
				}
				// ��ѯ���
				pst = conn.prepareStatement(sql);
				if(obj!=null){
					List<AccKeyValue> param_list = getNameValueByObj(obj, sql);
					for(int i=0;i<param_list.size();i++){
						if(param_list.get(i).getValue_class() == String.class){
							pst.setString(i+1, (String)param_list.get(i).getValue());
						}else if(param_list.get(i).getValue_class() == int.class){
							pst.setInt(i+1, (Integer)param_list.get(i).getValue());						
						}else if(param_list.get(i).getValue_class() == Date.class){
							pst.setDate(i+1, (Date)param_list.get(i).getValue());
						}
					}
				}
				rs = pst.executeQuery();				
				ResultSetMetaData rsmd = rs.getMetaData();
				int cols = rsmd.getColumnCount();  
				while (rs.next() != false) {
					Object tmpc = c.newInstance();
					Field fields[] = c.getDeclaredFields();
					Field.setAccessible(fields, true);
					Map<String, Class<?>> fieldmap = getMapByFields(fields);
					for (int i = 1; i <= cols; ++i) {
						Method methodset = c.getDeclaredMethod("set"+CommonUtils.captureName(rsmd.getColumnName(i)),
								fieldmap.get(rsmd.getColumnName(i)));
						if(fieldmap.get(rsmd.getColumnName(i)) == String.class){
							methodset.invoke(tmpc, rs.getString(i));
						}else if(fieldmap.get(rsmd.getColumnName(i)) == int.class){
							methodset.invoke(tmpc, rs.getInt(i));
						}else if(fieldmap.get(rsmd.getColumnName(i)) == Date.class){
							methodset.invoke(tmpc, rs.getDate(i));
						}
					}
					result_list.add(tmpc);
				}				
			}			
		}catch(Exception e){
			CommonUtils.error(e.toString());
		}finally{
			release(conn, pst, rs);
			if(sql.indexOf("limit")>0){
				release(null, totalpst, totalrs);
			}
		}
		if(ars.getTotalcount()==0){
			ars.setTotalcount(result_list.size());
		}
		ars.setResult(result_list);
		return ars;
	}
	
	/**
	 * insert��update��delete
	 * */
	public static int update(String sql, Object obj){
		int result_code = -1;
		PreparedStatement pst = null;
		Connection conn = null;
		try{
//			conn = getConnection(getConfSource());
			conn = getConnection();
			if(conn!=null){
				pst = conn.prepareStatement(sql);
				List<AccKeyValue> param_list = getNameValueByObj(obj, sql);
				for(int i=0;i<param_list.size();i++){
					if(param_list.get(i).getValue_class() == String.class){
						pst.setString(i+1, (String)param_list.get(i).getValue());
					}else if(param_list.get(i).getValue_class() == int.class){
						pst.setInt(i+1, (Integer)param_list.get(i).getValue());						
					}else if(param_list.get(i).getValue_class() == Date.class){
						pst.setDate(i+1, (Date)param_list.get(i).getValue());
					}
				}
				result_code = pst.executeUpdate();
			}			
		}catch(Exception e){
			CommonUtils.error(e.toString());
		}finally{
			release(conn, pst, null);
		}		
		return result_code;
	}
}
