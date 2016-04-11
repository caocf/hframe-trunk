<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml-strict.dtd">

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

	</script>
  </head>
  
    
  <body style="text-align: center;">
  	  <h3>一、选择</h3>
  	  	 <zqh:dtree treeId="munuTreedf" view="hfpm_page" objects="<%=null %>" condition="1=1" title="页面管理" checkbox="true" defaultBtn="select">
  	  	 </zqh:dtree>
  	    	  	<button onclick="refresh_Sys(null,'munuTreedf','tag_Condition=hfpmPageId=101')">刷新</button>
  	  
  	   <h3>二、添加</h3>	
  	  	<table>
  	  		<tr>
  	  			<td>
  	  	 				<zqh:dtree treeId="munuTreed" view="hfpm_page" objects="<%=null %>" condition="1=1" title="组织结构" checkbox="false" defaultBtn="" onClick="TreeClick_Sys4Other"></zqh:dtree>
  	  			</td>
  	  			<td style="width:1000px;">
					 <zqh:myform colNum="4" defaultBtn="save,reset" formid="sysOrgForm" view="hfpm_page" title="组织" height="800"></zqh:myform>
  	  			
  	  			</td>
  	  			
  	  		</tr>
  	  	</table>
  	   <h3>三、导航</h3>

	  <zqh:frame height="400" style="" width="1200" frameId="tefdas" src="demo/framedemo.jsp"></zqh:frame>
 </body>
</html>
