package com.cqx.acc.service.client.intf;

import com.cqx.acc.service.bean.intf.AccResponseIntf;

public abstract class AbstractClient {
	// ִ�м�����
	public int exec_cnt = 0;
	/**
	 * �������÷���
	 * */
	protected abstract AccResponseIntf callService(Object requestBean);
	
	/**
	 * ����callService������ҵ���߼�������ʧ���ص�
	 * */
	public abstract int exec(Object requestBean);
}
