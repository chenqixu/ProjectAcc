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
	 * 初始化数据源
	 * */
	public static void initConfigure() {
		//配置初始化
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
//		//获得数据源
//		dataSource = DataSourceUtils.setupDataSource(dbDriver,
//		dbUserName, dbPassword, dbURL);
	}
	
	/**
	 * 获得数据源
	 * */
	public static DataSource getConfSource(){
		if(dataSource==null){
			CommonUtils.info("##DataSourceUtils Not initialized configuration, data source!##");
		}
		return dataSource;
	}
	
	/**
	 * 获取连接
	 * */
	public static Connection getConnection(DataSource dataSource)
			throws SQLException {
		return dataSource.getConnection();
	}
	
	/**
	 * 获取连接
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
	 * 关闭连接
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
	 * 创建连接池
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
		// 最大活动连接
		ds.setMaxActive(5);
		// 最大空闲连接
		ds.setMinIdle(5);
		// 最小空闲连接
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
	 * 字符串以数字结尾,返回前面的字符串
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
	 * 传入条件bean,按顺序返回PreparedStatement
	 * sql有可能遇上字段和表名一致导致顺序不对，
	 * 所以要适应这种情况，剔除insert into .. (前，剔除update .. set前，剔除select ... where前
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
			//在使用java反射机制获取 JavaBean 的属性值时，如果该属性被声明为private 的
			//需要将setAccessible设置为true. 默认的值为false.
			Field.setAccessible(fields, true);
			for(int i=0;i<fields.length;i++){
				AccKeyValue tmpAccKV = new AccKeyValue();
				tmpAccKV.setKey(fields[i].getName());
				// 判断是否空值
				if(fields[i].get(obj)==null){
					continue;
				}
				// 如果有值，判断是否是String类型，判断长度是否为0
				if(fields[i].get(obj).getClass()==String.class && ((String)fields[i].get(obj)).length()==0){
					continue;
				}
				tmpAccKV.setValue(fields[i].get(obj));
				tmpAccKV.setValue_class(fields[i].get(obj).getClass());
				//找不到对应字符串,但是去除末尾数字后可以找到对应字符串的
				if(sql.indexOf(fields[i].getName())<0 && sql.indexOf(getStringCutNum(fields[i].getName()))>=0){
					//去除末尾数字后的位置加上末尾数字
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
	 * 传入字段,返回Map<字段名称,字段类型>
	 * */
	private static Map<String, Class<?>> getMapByFields(Field fields[]){
		Map<String, Class<?>> result_map = new HashMap<String, Class<?>>();
		for(Field f:fields){
			result_map.put(f.getName(), f.getType());
		}		
		return result_map;
	}	
	
	/**
	 * 查询,通过查询sql语句,查询条件bean返回结果bean
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
				// 先判断sql中是否有limit，如果有limit就先查询总记录数
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
				// 查询结果
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
	 * insert，update，delete
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
