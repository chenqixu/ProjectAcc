<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqx.acc.service.bean.login.LoginRequestBean" %>
<%@ page import="com.cqx.acc.service.client.LoginClient" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// 读取请求设置bean
String username = request.getParameter("username");
String password = request.getParameter("password");
//System.out.println("username:"+username+" password:"+password);
LoginRequestBean bean = new LoginRequestBean();
bean.setUsername(username);
bean.setPassword(password);
// 验证用户密码
LoginClient lc = new LoginClient();
// -1网络或服务问题;0用户密码不对;1成功;2鉴权失败
int islogin = lc.exec(bean);
if(islogin==1){
	session.setAttribute("user", username);
}
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="GBK">
    <title>LoginPost</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="ACC LoginPost">
  </head>  
  <body>
  </body>
  <script type="text/javascript">
	function documentReady(){
		if(document.readyState =="complete"){
			<%if(islogin==1){%>
				parent.location.href="<%=path%>/main/Index.jsp";
			<%}else if(islogin==0){%>
				alert("用户/密码错误！");
			<%}else if(islogin==-1){%>
				alert("网络或服务问题，请稍后重试！");
			<%}else if(islogin==2){%>
				alert("鉴权失败，请联系管理员！");
			<%}%>
		}
	}
	document.onreadystatechange= documentReady;
  </script>
</html>