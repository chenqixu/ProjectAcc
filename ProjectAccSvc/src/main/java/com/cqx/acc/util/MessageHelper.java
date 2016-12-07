package com.cqx.acc.util;

import com.cqx.acc.service.bean.intf.AccRequestHeader;
import com.cqx.acc.service.bean.intf.AccResponseHeader;
import com.cqx.acc.service.bean.intf.AccResponseIntf;

public class MessageHelper {
	/**
	 * 设置默认header和开始时间
	 * */
	public static void autoSetRespHeader(AccResponseIntf ari){
		AccResponseHeader responseHeader = new AccResponseHeader();
		responseHeader.setDealtime(String.valueOf(System.currentTimeMillis()));		
		ari.setHeader(responseHeader);
	}
	
	/**
	 * 计算结束时间
	 * */
	public static void autoSetDealTime(AccResponseIntf ari){
		long strattime = Long.valueOf(ari.getHeader().getDealtime());
		long dealtime = System.currentTimeMillis() - strattime;
		ari.getHeader().setDealtime(String.valueOf(dealtime));
		ari.getHeader().setStatus(0);
	}
	
	/**
	 * 设置鉴权失败结果，及计算结束时间
	 * */
	public static void autoSetAuthResult(AccResponseIntf ari){
		long strattime = Long.valueOf(ari.getHeader().getDealtime());
		long dealtime = System.currentTimeMillis() - strattime;
		ari.getHeader().setDealtime(String.valueOf(dealtime));
		ari.getHeader().setDesc("鉴权失败，请重试。");
		ari.getHeader().setStatus(1);
	}
	
	/**
	 * 鉴权
	 * */
	public static boolean authority(AccRequestHeader header){
		if(!Constants.IS_PERMIT){ // 不进行鉴权
			return true;
		}else{
			String name = header.getRequestname();
			String remote_id = header.getKey();
			String local_id = KEYUtils.getKEYByNameAndId(name, UserListCache.keylist.get(name));
			CommonUtils.debug("[remote_id]"+remote_id+"[local_id]"+local_id);
			return remote_id.equals(local_id);
		}
	}
}
