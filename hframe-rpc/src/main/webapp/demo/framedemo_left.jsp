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
		
		function refreshLeft(id){
		var text=TreeClickGetText_Sys(id);
		var url=TreeClickGetUrl_Sys(id);
		
		parent.addTab_Sys("RightFrame",text,url);
	}
	
	</script>

  </head>
  
  <body>
  <zqh:dtree treeId="munuTr1eed" view="hfpm_page" objects="<%=null %>" condition="1=1" title="组织结构" checkbox="false" defaultBtn="" onClick="refreshLeft"></zqh:dtree>
  </body>
</html>
