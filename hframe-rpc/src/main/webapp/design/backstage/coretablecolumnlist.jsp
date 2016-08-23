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
		<base href="<%=basePath%>">
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
		<div style="text-align: left;font-size: 21px;"    >
		<form class="form-inline">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys('','123456',this.value);" initial="true"></zqh:select>
		<zqh:select selectId="123456" visible="" view="table_ele" width="150px" onChange="refresh_Sys(null,'1231232','tag_Condition=table_id=\\''+this.value+'\\'');" initial="true"></zqh:select>  	
  		</form></div>
  		<zqh:mygrid gridId="1231232" defaultBtn="save" editable="true" condition="table_id='183454398475491'"  view="core_table_column"  title="数据列列表"></zqh:mygrid>
	</body>
</html>
