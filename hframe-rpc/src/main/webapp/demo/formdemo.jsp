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
  	
	<hr/>
	<h3 style="text-align: left;">1、测试新建</h3>
	<zqh:myform formid="full1_form_test111" view="full_form_test" colNum="4" title="测试" object="<%=null %>" editable="true" width="1800" defaultBtn="save,reset,back,ajax"  isAjax="">
		<!-- <center>  -->
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="保存"></zqh:button>
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="重置"></zqh:button>
		<!-- </center>  -->
	</zqh:myform>
	<h3 style="text-align: left;">1、测试新建</h3>
	<zqh:myform formid="hfpm_progr11am11" view="hfpm_program" colNum="4" title="test" object="<%=null %>"  editable="true" width="1800" defaultBtn="save,reset,back" isAjax="" objectId="">
	</zqh:myform>
	<h3 style="text-align: left;">2、测试更新</h3>
	<zqh:myform formid="hfpm_program11" view="hfpm_program" colNum="4" title="test" object="<%=null %>"  editable="true" width="1800" defaultBtn="save,reset,back" isAjax="" objectId="151031375370">
	</zqh:myform>
	<button onclick="refresh_Sys(null,'hfpm_program11','tag_ObjectId=660813252612227')">刷新</button>
	<h3 style="text-align: left;">3、测试展示</h3>
	<zqh:myform formid="hfpm_program22" view="hfpm_program" colNum="4" title="测试" object="<%=null %>"  editable="text" width="1800" defaultBtn="back" isAjax="" objectId="151031375370"></zqh:myform>

 	
 	
 	<h3 style="text-align: left;">4、form与grid组合提交</h3>
 	<div style="text-align: left;">
	 	<zqh:myform formid="diarygroup" view="diary_group" colNum="8" title="创建日志分组" object="<%=null %>" editable="true" url="" defaultBtn=""></zqh:myform>
		<zqh:mygrid gridId="diary" defaultBtn="ajax(diarygroup|diary)" view="diary" editable="true" title="明细" ></zqh:mygrid>
	</div>
	
  </body>
</html>
