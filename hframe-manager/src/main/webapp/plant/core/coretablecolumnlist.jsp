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

		<link rel="stylesheet" type="text/css"
			href="<%=path%>/design/css/index.css">

		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/table.js"></script>

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
		<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys('','123456',this.value);" initial="true"></zqh:select>
		<zqh:select selectId="123456" visible="" view="table_ele" width="150px" onChange="refresh_Sys(null,'1231232','tag_Condition=table_id=\\''+this.value+'\\'');" initial="true"></zqh:select>  	
  		</div>
  		<zqh:mygrid gridId="1231232" defaultBtn="save" editable="true" condition="table_id='183454398475491'"  view="core_table_column"  title="数据列列表"></zqh:mygrid>
				
			
		</div>
	</body>
</html>
