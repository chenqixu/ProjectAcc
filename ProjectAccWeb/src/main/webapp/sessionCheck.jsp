<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
Object obj = session.getAttribute("user");
if(obj==null){
	response.sendRedirect(request.getContextPath()+"/Login.jsp");
}else{
	
}
%>