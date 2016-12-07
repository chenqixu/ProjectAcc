<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// 删除会话
session.setAttribute("user", null);
// 销毁用户的session
request.getSession().invalidate();
// 重新跳转到主页
request.getRequestDispatcher("/Login.jsp").forward(request, response);
%>
<!DOCTYPE HTML>
<html>
  <head>    
    <title>登出</title>
  </head>  
  <body>
  </body>
</html>
