<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframework.common.ssh.service.CommonServ"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'form.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


		<!-- 引入bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
<div>				
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home1" aria-controls="home1" role="tab" data-toggle="tab">数据库</a></li>
    <li role="presentation"><a href="#profile2" aria-controls="profile2" role="tab" data-toggle="tab">数据表</a></li>
    <li role="presentation"><a href="#messages3" aria-controls="messages3" role="tab" data-toggle="tab">数据列</a></li>
    <li role="presentation"><a href="#settings4" aria-controls="settings4" role="tab" data-toggle="tab">展示方式</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane fade in  active" id="home1">
    		<iframe id="null" src="design/backstage/coredblist.jsp?_FRAMEID=null"
				frameborder="0" style="height: 550px; width: 800px;" height="143"
				width="160"></iframe></div>
    <div role="tabpanel" class="tab-pane fade" id="profile2">
    	<iframe id="null"
				src="design/backstage/coretablelist.jsp?_FRAMEID=null"
				frameborder="0" style="height: 550px; width: 800px;"></iframe>
    </div>
    <div role="tabpanel" class="tab-pane fade" id="messages3"><iframe id="null"
				src="design/backstage/coretablecolumnlist.jsp?_FRAMEID=null"
				frameborder="0" style="height: 550px; width: 800px;"></iframe></div>
    <div role="tabpanel" class="tab-pane fade" id="settings4"><iframe id="null"
				src="design/backstage/coreshowtypelist.jsp?_FRAMEID=null"
				frameborder="0" style="height: 550px; width: 800px;"></iframe></div>
  </div>
</div>
	</body>
</html>
