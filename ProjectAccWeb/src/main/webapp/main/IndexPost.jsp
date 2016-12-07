<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="org.apache.commons.beanutils.BeanUtils"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.cqx.acc.service.bean.deal.DealCountsDailyRequestBean" %>
<%@ page import="com.cqx.acc.service.client.DealCountsDailyClient" %>
<%
String action = request.getParameter("action");
String beanStr = request.getParameter("beanStr");
DealCountsDailyRequestBean dcdrbean = new DealCountsDailyRequestBean();
DealCountsDailyClient client = new DealCountsDailyClient();
if(!StringUtils.isEmpty(beanStr)){
	beanStr = java.net.URLDecoder.decode(beanStr, "UTF-8");
	JSONObject baseBeanJson = JSONObject.fromObject(beanStr);
	BeanUtils.copyProperties(dcdrbean, baseBeanJson);
	System.out.println("------------------param--------------------");	
	System.out.println("beanStr:"+beanStr);
}
if(StringUtils.isNotBlank(action)){
	if(action.intern() == "query"){//增加
		client.qryCountsDaily(dcdrbean, response);
	}else if(action.intern() == "add"){//增加
		client.insertCountsDaily(dcdrbean, response);
	}else if(action.intern() == "modify"){//修改
		client.updateCountsDaily(dcdrbean, response);
	}else if(action.intern() == "del"){//删除，只是置失效
		client.disableCountsDaily(dcdrbean, response);
	}
}
%>