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
  	
  	<h3 style="text-align: left;">1�������½�</h3>
	 	<zqh:mygrid gridId="userGrid32" view="full_form_test" width="" url="Hframe/fullformtest/_fullformtest_batchCreate"  objects="<%=null %>" title="�����½�" editable="true" defaultBtn="save,reset,back,ajax"></zqh:mygrid>

	<h3 style="text-align: left;">1�������½�</h3>
	<zqh:mygrid gridId="diarygro1up1" view="hfpm_program" width=""  objects="<%=null %>" title="�����½�" editable="true" condition="" defaultBtn="save,reset,back,ajax"></zqh:mygrid>


	<h3 style="text-align: left;">2�����Ը���</h3>
 	<zqh:mygrid gridId="diarygroup1" view="hfpm_program" width=""  objects="<%=null %>" title="���Ը���" editable="true" condition="1=1" defaultBtn="save,reset,back,ajax"></zqh:mygrid>
	<button onclick="refresh_Sys(null,'diarygroup1','tag_Condition=hfpmProgramId=1510313751370')">ˢ��</button>


	<h3 style="text-align: left;">3������չʾ(������listȡ��)</h3>
 	<zqh:mygrid gridId="userGrid2" view="hfpm_program" width=""  objects="<%=null %>" condition="1=1" title="����չʾ" editable="text" defaultBtn="back"></zqh:mygrid>
	<button onclick="refresh_Sys(null,'userGrid2','tag_Condition=hfpmProgramId=1510313175370')">ˢ��</button>

  </body>
</html>
