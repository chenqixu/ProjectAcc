package com.cqx.acc.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cqx.acc.service.bean.Acc_user;

/**
 * 用户缓存列表
 * */
public class UserListCache {
	public static Map<String, String> keylist = new HashMap<String, String>();
	/**
	 * 初始化用户缓存列表
	 * */
	public static void init(){
		String sql = "select username,password,keyid from acc_user ";
		CommonUtils.info("[sql]"+sql);
		List<Object> result_list = DataSourceUtils.query(sql, null, Acc_user.class).getResult();
		Iterator<Object> it = result_list.iterator();
		while(it.hasNext()){
			Acc_user result = (Acc_user)it.next();
			keylist.put(result.getUsername(), result.getKeyid());
			CommonUtils.debug("[UserListCache]"+result.getUsername()+"|"+result.getKeyid());
		}
	}
}
