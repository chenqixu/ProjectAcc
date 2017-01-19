package com.cqx.acc.service.client;

import com.cqx.acc.service.LoginService;
import com.cqx.acc.service.bean.intf.AccResponseIntf;
import com.cqx.acc.service.bean.login.LoginRequestBean;
import com.cqx.acc.service.bean.login.LoginRequestObject;
import com.cqx.acc.service.bean.login.LoginResponseObject;
import com.cqx.acc.service.client.intf.AbstractClient;
import com.cqx.acc.util.Constants;
import com.cqx.acc.util.KEYUtils;
import com.cqx.acc.util.MessageHelper;

public class LoginClient extends AbstractClient{
	private LoginService ls = null;
	public LoginClient(){
		ls = (LoginService)Constants.ctx.getBean("LoginService");
	}

	@Override
	protected AccResponseIntf callService(Object requestBean) {
		// 传入的请求bean
		LoginRequestBean bean = (LoginRequestBean)requestBean;
		// 创建请求对象
		LoginRequestObject request = new LoginRequestObject();
		// 对密码进行md5加密
		bean.setPassword(KEYUtils.stringToMD5(bean.getPassword()));
		request.setBody(bean);
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(request, bean.getUsername());
		// 请求服务
		return ls.LoginCheck(request);
	}

	@Override
	public int exec(Object requestBean) {
		// 返回标志
		int flag=-1; // -1错误；0正常；1鉴权失败
		LoginResponseObject response = (LoginResponseObject) callService(requestBean);
		// 鉴权失败
		if(response.getHeader().getStatus()==1){
			 flag=2;
		}
		// 网络及其他原因需要重试3次(请求服务)
		else if(response.getHeader().getStatus()==-1){
			if(exec_cnt<=2){
				exec_cnt++;
				exec(requestBean);
			}
		}
		// 服务请求成功
		else{
			// 查询结果0用户密码不对；查询结果1成功
			if(Integer.valueOf((String)response.getBody())==1)flag=1;
			else flag=0;
		}
		// -1网络或服务问题;0用户密码不对;1成功;2鉴权失败
		return flag;
	}
}
