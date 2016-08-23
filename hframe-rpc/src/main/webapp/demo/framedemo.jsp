<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'framedemo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>

	<script type="text/javascript">
	
	</script>
  </head>
  
  <body>
		<div style="width: 200px;float: left;">
		  	<ul>
		  		<li><a href="javascript:addTab_Sys('aaatab','表格','form.jsp');" >表格----form.jsp</a></li>
		  		<li><a href="javascript:addTab_Sys('aaatab','框架','frame.jsp');" >框架----frame.jsp</a></li>
		  		<li><a href="javascript:addTab_Sys('aaatab','右键菜单','contextmenu.jsp');" >右键菜单----contextmenu.jsp</a></li>
		  		<li><a href="javascript:addTab_Sys('aaatab','级联菜单','menubar.jsp');" >级联菜单----menubar.jsp</a></li>
		  		<li><a href="javascript:addTab_Sys('aaatab','标签页','tab2.jsp');" >标签页----tab2.jsp</a></li>
		  		<li><a href="javascript:addTab_Sys('aaatab','动态输入框','tipInput.jsp');" >动态输入框----tipInput.jsp</a></li>
		  		<li><a href="javascript:addTab_Sys('aaatab','树','tree.jsp');">树----tree.jsp</a></li>
		  		<li><a href="javascript:addTab_Sys('aaatab','树','design/pagedesigner/pagedesigner.jsp');">/design/pagedesigner/pagedesigner.jsp</a></li>
		  		
		  		
		  	</ul>
		</div>
		<zqh:frame height="500" style="float:left;clear:right;" width="800" frameId="aaatab" isTab="true" src="demo/framedemo_left.jsp"></zqh:frame>
		
	<br/>
	
	<zqh:frame height="400" style="float:left;" width="300" frameId="leftFrame" src="demo/framedemo_left.jsp"></zqh:frame>
	<zqh:frame height="600" style="float:left;" width="800" frameId="RightFrame" src="demo/framedemo_right.jsp"></zqh:frame>、、、
	

  </body>
</html>
