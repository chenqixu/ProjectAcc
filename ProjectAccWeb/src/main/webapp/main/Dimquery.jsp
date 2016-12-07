<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="org.apache.commons.beanutils.BeanUtils"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.cqx.acc.service.client.DimClient" %>
<%@ page import="com.cqx.acc.service.bean.dim.Acc_my_card" %>
<%@ page import="com.cqx.acc.service.bean.dim.Acc_use_type" %>
<%
String action = request.getParameter("action");
String qryBean = request.getParameter("qryBean");
String beanStr = request.getParameter("beanStr");
JSONObject baseBeanJson = null;
Object requestBean = null;
if(!StringUtils.isEmpty(beanStr)){
	beanStr = java.net.URLDecoder.decode(beanStr, "UTF-8");
	baseBeanJson = JSONObject.fromObject(beanStr);
	if(qryBean.intern() == "mycard"){
		requestBean = new Acc_my_card();
		BeanUtils.copyProperties(requestBean, baseBeanJson);
	}else if(qryBean.intern() == "usetype"){
		requestBean = new Acc_use_type();
		BeanUtils.copyProperties(requestBean, baseBeanJson);
	}	
	System.out.println("------------------param--------------------");	
	System.out.println("beanStr:"+beanStr);
}
DimClient dimc = new DimClient();
if(StringUtils.isNotBlank(action)){
	if(action.intern() == "query"){//查询
		if(qryBean.intern() == "mycard"){
			dimc.qryMyCard((Acc_my_card)requestBean, response);
		}else if(qryBean.intern() == "usetype"){
			dimc.qryUsetype((Acc_use_type)requestBean, response);
		}
	}else if(action.intern() == "add"){//增加
		if(qryBean.intern() == "mycard"){
			dimc.insertMyCard((Acc_my_card)requestBean, response);
		}else if(qryBean.intern() == "usetype"){
			dimc.insertUsetype((Acc_use_type)requestBean, response);
		}
	}else if(action.intern() == "modify"){//修改
		if(qryBean.intern() == "mycard"){
			dimc.updateMyCard((Acc_my_card)requestBean, response);
		}else if(qryBean.intern() == "usetype"){
			dimc.updateUsetype((Acc_use_type)requestBean, response);
		}
	}else if(action.intern() == "del"){//删除
		if(qryBean.intern() == "mycard"){
			dimc.disableMyCard((Acc_my_card)requestBean, response);
		}else if(qryBean.intern() == "usetype"){
			dimc.disableUsetype((Acc_use_type)requestBean, response);
		}
	}
}
%>