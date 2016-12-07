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
boolean islogin = lc.checkLogin(bean);
if(islogin){
	session.setAttribute("user", username);
}
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'LoginPost.jsp' starting page</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>  
  <body>
  </body>
  <script>  
	function documentReady(){
		if(document.readyState =="complete"){
			<%if(islogin){%>
				parent.location.href="<%=path%>/main/Index.jsp";
			<%}else{%>
				alert("用户/密码错误！");
			<%}%>
		}
	}
	document.onreadystatechange= documentReady;
  </script>
</html>