<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframework.common.util.VelocityUtil"%>
<%@page import="com.hframework.common.bean.Column"%>
<%@page import="com.hframework.common.bean.ShowType"%>
<%@page import="com.hframework.common.bean.Option"%>
<%@page import="com.hframework.common.bean.User"%>
<%@page import="com.hframework.common.ssh.service.CommonServ"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml-strict.dtd">
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>

<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
    
  <body style="text-align: center;">
  <div style="height: 400px;background-color: green;" id="test2">
</div>
<div style="height: 400px;background-color: blue;" id="test">
</div>
<script type="text/javascript">

 var aaa=createContextMenu_Sys("#test2");
</script>
  </body>
</html>
