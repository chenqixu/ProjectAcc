package com.cqx.acc.service.client.intf;

import com.cqx.acc.service.bean.intf.AccResponseIntf;

public abstract class AbstractClient {
	// 执行计数器
	public int exec_cnt = 0;
	/**
	 * 真正调用服务
	 * */
	protected abstract AccResponseIntf callService(Object requestBean);
	
	/**
	 * 调用callService，处理业务逻辑，包括失败重调
	 * */
	public abstract int exec(Object requestBean);
}
