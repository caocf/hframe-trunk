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
  
  
  	  <zqh:mylist listId="222" view="hfpm_data_field" condition="1=1" objects="<%=null %>" title="数据表列表" defaultBtn="">
			<zqh:button src="test/createDialog.jsp?VIEW=core_table&TYPE=FORM&CONDITION=&TITLE=数据表" param="width:400;height:300" model="openwin-create" buttonId="1111" icon="" buttonName="新建" title="新建" ></zqh:button>&nbsp;&nbsp;&nbsp;
			<zqh:button src="test/createDialog.jsp?VIEW=core_table&TYPE=FORM&CONDITION=&TITLE=数据表" param="width:400;height:300" model="openwin-update" buttonId="1211" icon="" buttonName="修改" title="修改" ></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="111" model="action-delete" buttonId="1311" icon="" buttonName="删除" title="删除"></zqh:button>
  	  	</zqh:mylist>
  	    <input type="checkbox" onclick="allSelect_Sys(this)" >
  	  	<button onclick="refresh_Sys(null,'222','tag_Condition=1=1')">刷新</button>
  	  	<button onclick="refreshList(222,2,10)">下一个</button>
  	  
  </body>
</html>
