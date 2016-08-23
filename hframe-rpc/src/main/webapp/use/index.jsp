<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
	
	<style type="text/css">
	
	
	</style>
	
	<script type="text/javascript">
	
	
	function refreshLeft(id,url){
	
		leftFrame.refresh_Sys(null,'munuTreed','tag_Condition=menu_id='+id);
	}
	
	
		
	
	</script>
	
  </head>
  
    
  <body style="text-align:center">
  	
	  	<div style="width: 1000px;text-align: center;">
	
	<zqh:menu condition="par_id='-1'" menuId="sysmenu111" objects="<%=null %>" view="sec_menu_set" onClick="refreshLeft" width="400px"></zqh:menu> 
	<br/>
	<zqh:frame height="520" style="float:left;margin:2px;border:#dbdbdb solid 1px;" width="300" frameId="leftFrame" src="demo/framedemo_left.jsp"></zqh:frame>
	<zqh:frame height="500" style="float:left;margin:2px;" width="680" frameId="RightFrame" src="demo/framedemo_right.jsp" isTab="true"></zqh:frame>
	</div>

  </body>
</html>
