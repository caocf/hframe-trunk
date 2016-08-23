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
	#userGridDiv{
		overflow: scroll;
		width: 1000px;
	}
	
	
	.icon-sub{
		padding:10px;
		width: 100px;
		height: 40px;
		
	}
	
	
	</style>
	
	<script type="text/javascript">
	
	
	function refreshLeft(id,url){
	
		alert(id);
	}
	
	
		
	
	</script>
	<script type="text/javascript">document.execCommand("BackgroundImageCache", false, true);</script><!-- IE6±³¾°Í¼Æ¬ÉÁË¸ÎÊÌâ  -->
	
  </head>
  
    
  <body style="text-align:center">
  	
	<hr/>
	  	<zqh:myform colNum="4" defaultBtn="save" formid="sssssssssss" view="sec_menu"></zqh:myform>
	  
	  
	  	<zqh:menu condition="" menuId="menusID" objects="<%=new CommonServ().getMenuList()%>" view="menu"></zqh:menu> 
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  	<zqh:menu condition="par_id='-1'" menuId="sysmenu111" objects="<%=null %>" view="sec_menu_set" onClick="refreshLeft" ></zqh:menu> 
	  	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  <br/>
	  	  	<zqh:menu condition="1=1" menuId="sysmenu3" objects="<%=null %>" view="sec_menu_set" onClick="refreshLeft" ></zqh:menu> 
	  	

	<zqh:frame height="400" style="float:left;" width="300" frameId="leftFrame" src="demo/framedemo_left.jsp"></zqh:frame>
	<zqh:frame height="400" style="float:left;" width="800" frameId="RightFrame" src="demo/framedemo_right.jsp"></zqh:frame>

  </body>
</html>
