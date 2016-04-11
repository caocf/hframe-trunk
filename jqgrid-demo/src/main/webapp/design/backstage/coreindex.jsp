<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframe.common.ssh.service.CommonServ"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ include file="/webframe/commonhead.jsp" %>
    <%@ include file="/webframe/mycommonhead.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tab.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	
  </head>
  
  <body style="">
		&nbsp;&nbsp;&nbsp;<zqh:tab view="" tabId="111" objects="<%=null %>" tabGroup="<%=new CommonServ().getTabGroup()%>"></zqh:tab>
  </body>
</html>
