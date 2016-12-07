package com.cqx.acc.util;

import com.cqx.acc.service.bean.intf.AccRequestHeader;
import com.cqx.acc.service.bean.intf.AccResponseHeader;
import com.cqx.acc.service.bean.intf.AccResponseIntf;

public class MessageHelper {
	/**
	 * ����Ĭ��header�Ϳ�ʼʱ��
	 * */
	public static void autoSetRespHeader(AccResponseIntf ari){
		AccResponseHeader responseHeader = new AccResponseHeader();
		responseHeader.setDealtime(String.valueOf(System.currentTimeMillis()));		
		ari.setHeader(responseHeader);
	}
	
	/**
	 * �������ʱ��
	 * */
	public static void autoSetDealTime(AccResponseIntf ari){
		long strattime = Long.valueOf(ari.getHeader().getDealtime());
		long dealtime = System.currentTimeMillis() - strattime;
		ari.getHeader().setDealtime(String.valueOf(dealtime));
		ari.getHeader().setStatus(0);
	}
	
	/**
	 * ���ü�Ȩʧ�ܽ�������������ʱ��
	 * */
	public static void autoSetAuthResult(AccResponseIntf ari){
		long strattime = Long.valueOf(ari.getHeader().getDealtime());
		long dealtime = System.currentTimeMillis() - strattime;
		ari.getHeader().setDealtime(String.valueOf(dealtime));
		ari.getHeader().setDesc("��Ȩʧ�ܣ������ԡ�");
		ari.getHeader().setStatus(1);
	}
	
	/**
	 * ��Ȩ
	 * */
	public static boolean authority(AccRequestHeader header){
		if(!Constants.IS_PERMIT){ // �����м�Ȩ
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
