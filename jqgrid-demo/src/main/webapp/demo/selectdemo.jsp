<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframe.common.util.VelocityUtil"%>
<%@page import="com.hframe.common.bean.Column"%>
<%@page import="com.hframe.common.bean.ShowType"%>
<%@page import="com.hframe.common.bean.Option"%>
<%@page import="com.hframe.common.bean.User"%>
<%@page import="com.hframe.common.ssh.service.CommonServ"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
    
  <body style="text-align: center;">

	<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys('','123456',this.value);" initial="true"></zqh:select>
	<zqh:select selectId="123456" visible="" view="table_ele" width="150px" onChange="refresh_Sys('','fdsfdsf',this.value);" initial="true"></zqh:select>  	
 	<zqh:select selectId="fdsfdsf" visible="" view="column_ele" width="150px" onChange="" initial="true"></zqh:select>  	
 	
  </body>
</html>
