<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

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
<script type="text/javascript">

</script>
	</head>

	<body >
				<div style="text-align: left;font-size: 21px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys('','123456',this.value);" initial="true"></zqh:select>
		<zqh:select selectId="123456" visible="" view="table_ele" width="150px" onChange="refresh_Sys(null,'1231232','tag_Param='+this.value+'&&tag_Condition= column_id_from IN (SELECT id FROM core_table_column WHERE table_id = \\''+this.value+'\\')');" initial="true"></zqh:select>  	
  		</div>
  		<zqh:mygrid gridId="1231232" defaultBtn="save" editable="true" condition=" column_id_from IN (SELECT id FROM core_table_column WHERE table_id = '319873202375279')"  view="core_table_fk"  title="外键列表" param="319873202375279"></zqh:mygrid>
	</body>
</html>
