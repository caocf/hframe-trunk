<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>智能系统-设计首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=path %>/design/css/index.css">
  </head>
  
  <body  background="./images/bg_12.jpg"  bgcolor="white" style="margin: 5px;">
  	<div id="left" style="float: left;width: 240px;height: 400px;">
  	</div>
  	<div id="right" style="width: 700px;height: 400px;">
  	
  	</div>
  </body>
</html>
