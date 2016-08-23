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
		
		<script type="text/javascript">
		$(function(){
			 
			 $("#autoCreateBtn").click(function(){
			 	var dbid=$("select[name=currentDb]").find("option:selected").val();
			 	$.ajax({
			 		type:"post",
			 		url:"<%=path%>/core/core_autocreate_create?dbid="+dbid,
			 		success:function(msg){
			 			if(msg=="T"){
			 				alert("创建成功！");
			 			}else{
			 				alert(msg);
			 			}
			 		}
			 	});
			 	//window.location.reload();
			 });
		});	
	
	
	</script>
	</head>

	<body background="<%=path%>/design/images/bg_13.jpg" bgcolor="white"
		style="margin: 5px;">
		
		<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="" initial="true"></zqh:select>

		&nbsp;&nbsp;&nbsp;&nbsp;<button id="autoCreateBtn">生成代码</button>


	</body>
</html>
