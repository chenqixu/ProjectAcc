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
		// ���������bean
		LoginRequestBean bean = (LoginRequestBean)requestBean;
		// �����������
		LoginRequestObject request = new LoginRequestObject();
		// ���������md5����
		bean.setPassword(KEYUtils.stringToMD5(bean.getPassword()));
		request.setBody(bean);
		// �Զ����ñ���ͷ
		MessageHelper.autoSetRequestHeader(request, bean.getUsername());
		// �������
		return ls.LoginCheck(request);
	}

	@Override
	public int exec(Object requestBean) {
		// ���ر�־
		int flag=-1; // -1����0������1��Ȩʧ��
		LoginResponseObject response = (LoginResponseObject) callService(requestBean);
		// ��Ȩʧ��
		if(response.getHeader().getStatus()==1){
			 flag=2;
		}
		// ���缰����ԭ����Ҫ����3��(�������)
		else if(response.getHeader().getStatus()==-1){
			if(exec_cnt<=2){
				exec_cnt++;
				exec(requestBean);
			}
		}
		// ��������ɹ�
		else{
			// ��ѯ���0�û����벻�ԣ���ѯ���1�ɹ�
			if(Integer.valueOf((String)response.getBody())==1)flag=1;
			else flag=0;
		}
		// -1������������;0�û����벻��;1�ɹ�;2��Ȩʧ��
		return flag;
	}
}
