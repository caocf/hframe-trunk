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

	</head>

	<body >
			
		<div style="text-align: left;font-size: 21px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys(null,'222','tag_Condition=dbId=\\''+this.value+'\\'')" initial="true"></zqh:select>
	 	</div>
		<zqh:mylist listId="222" view="SYS_TABLE_LIST" condition="1=1" objects="<%=null %>" title="数据表列表" defaultBtn="">
			<zqh:button src="" param="" model="openmodel-create" buttonId="1111" icon="" buttonName="新建" title="新建" targetObj= "fdsfdsafdsa"></zqh:button>&nbsp;&nbsp;&nbsp;
			<zqh:button src="" param="" model="openmodel-update" buttonId="1211" icon="" buttonName="修改" title="修改" srcObj=""   targetObj= "aaaaf1dsf1dsa"></zqh:button>&nbsp;&nbsp;&nbsp;
			<!-- 
			<zqh:button src="test/createDialog.jsp?VIEW=core_table&TYPE=FORM&CONDITION=&TITLE=数据表" param="width:400;height:300" model="openwin-create" buttonId="1111" icon="" buttonName="新建" title="新建" ></zqh:button>&nbsp;&nbsp;&nbsp;
			<zqh:button src="test/createDialog.jsp?VIEW=core_table&TYPE=FORM&CONDITION=&TITLE=数据表" param="width:400;height:300" model="openwin-update" buttonId="1211" icon="" buttonName="修改" title="修改" ></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	 -->
	  	  	<zqh:button src="111" model="action-delete" buttonId="1311" icon="" buttonName="删除" title="删除"></zqh:button>
  	  	</zqh:mylist>
		<hr>
		
		<zqh:model id="fdsfdsafdsa">
			<zqh:myform formid="aaaafdsfdsa" title="添加数据库" view="core_table" colNum="4"  defaultBtn="ajax">
			</zqh:myform>
		</zqh:model>
		<zqh:model id="fdsfdsafd1sa">
			<zqh:myform formid="aaaaf1dsf1dsa" title="修改数据库" view="core_table" colNum="4"  defaultBtn="ajax">
			</zqh:myform>
		</zqh:model>
	</body>
</html>
