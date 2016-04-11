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
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		

		<zqh:mylist listId="222" view="SYS_SET_LIST" condition="1=1" objects="<%=null %>" title="数据集" defaultBtn="">
			<zqh:button src="test/createCombDialog.jsp?VIEW=core_set|core_field&TYPE=FORM|GRID&CONDITION=&TITLE=数据集|数据集域列表&FK=core_set_id" param="width:1400;height:500" model="openwin-create" buttonId="1111" icon="" buttonName="新建" title="新建" ></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="test/createCombDialog.jsp?VIEW=core_set|core_field&TYPE=FORM|GRID&CONDITION=&TITLE=数据集|数据集域列表&FK=core_set_id" param="width:1400;height:500" model="openwin-update" buttonId="1121" icon="" buttonName="修改" title="修改"></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="111" model="action-delete" buttonId="1311" icon="" buttonName="删除" title="删除"></zqh:button>
  	  	</zqh:mylist>


	</body>
</html>
