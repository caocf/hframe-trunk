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

</head>

<body style="">

<zqh:myform formid="QF4hfpm_program_DS4Q" view="hfpm_program_DS4Q" colNum="6" title="项目查询" object="<%=null %>"
			editable="true" width="1800" defaultBtn="query,reset" isAjax="" objectId="">
</zqh:myform>

<zqh:mylist listId="RL4hfpm_program" view="hfpm_program" condition="1=1" objects="<%=null %>" title="项目列表" defaultBtn="">
	<zqh:button src="" param="" model="openmodel-create" buttonId="1111"  targetObj="M$CF4hfpm_program"
				icon="" buttonName="新建" title="新建"></zqh:button>&nbsp;&nbsp;&nbsp;&nbsp;
	<zqh:button
			src="test/createDialog.jsp?VIEW=core_db&TYPE=FORM&CONDITION=&TITLE=数据库" srcObj="" targetObj="UF4Hfpm_Program"
			param="width:400;height:300" model="openmodel-update" buttonId="1121"
			icon="" buttonName="修改" title="修改"></zqh:button>&nbsp;&nbsp;&nbsp;
	<zqh:button src="111" model="action-delete" buttonId="1311"
				icon="" buttonName="删除" title="删除"></zqh:button>
</zqh:mylist>

<zqh:model id="M$CF4hfpm_program">
	<zqh:myform formid="CF4hfpm_program" title="添加数据库" view="hfpm_program" colNum="4" editable="true"  defaultBtn="ajax">
	</zqh:myform>
</zqh:model>
<zqh:model id="M$UF4Hfpm_Program">
	<zqh:myform formid="UF4Hfpm_Program" title="修改数据库" view="hfpm_program" colNum="4" editable="true"  defaultBtn="ajax">
	</zqh:myform>
</zqh:model>

</body>
</html>
