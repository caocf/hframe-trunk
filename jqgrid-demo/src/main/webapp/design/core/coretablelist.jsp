<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>

		<link rel="stylesheet" type="text/css"
			href="<%=path%>/design/css/index.css">

		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

		<script type="text/javascript">
		
	
	</script>

	</head>

	<body background="<%=path%>/design/images/bg_13.jpg" bgcolor="white"
		style="margin: 5px;">
		<div id="left" style="float: left; width: 240px; height: 400px;">
			<ul class="left_menu">
			<li><a href="<%=path %>/core/core_db_list">数据库管理</a></li>
			<li><a href="<%=path %>/core/core_table_list" >数据表管理</a></li>
			<li><a href="<%=path %>/core/core_tablecolumn_list" >数据列管理</a></li>
			<li><a href="<%=path %>/core/core_tablefk_list" >外键管理</a></li>


			</ul>
		</div>
		<div id="right" style="width: 700px; height: 400px;">
			
		<div style="text-align: left;font-size: 21px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys(null,'222','tag_Condition=dbId=\\''+this.value+'\\'')" initial="true"></zqh:select>
	 	</div>
		<zqh:mylist listId="222" view="SYS_TABLE_LIST" condition="1=1" objects="<%=null %>" title="数据表列表" defaultBtn="">
			<zqh:button src="test/createDialog.jsp?VIEW=core_table&TYPE=FORM&CONDITION=&TITLE=数据表" param="width:400;height:300" model="openwin-create" buttonId="1111" icon="" buttonName="新建" title="新建" ></zqh:button>&nbsp;&nbsp;&nbsp;
			<zqh:button src="test/createDialog.jsp?VIEW=core_table&TYPE=FORM&CONDITION=&TITLE=数据表" param="width:400;height:300" model="openwin-update" buttonId="1211" icon="" buttonName="修改" title="修改" ></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="111" model="action-delete" buttonId="1311" icon="" buttonName="删除" title="删除"></zqh:button>
  	  	</zqh:mylist>
		</div>
		
		<hr>
	</body>
</html>
