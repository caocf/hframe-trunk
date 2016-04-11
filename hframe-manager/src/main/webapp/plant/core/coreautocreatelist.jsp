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

		<link rel="stylesheet" type="text/css"
			href="<%=path%>/design/css/index.css">

		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

		<script type="text/javascript">
		$(function(){
			
			/*
			 $("#currentDb").change(function(){
			 
			 	var dbid=$("select[name=currentDb]").find("option:selected").val();
			 	window.location.href="<%=path%>/core/core_table_list?coreTable.dbId="+dbid;
			 	
			 });
			 */
			 
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
			 $("#refreshCacheBtn").click(function(){
			 	$.ajax({
			 		type:"post",
			 		url:"<%=path%>/core/core_autocreate_refreshCache",
			 		success:function(msg){
			 			if(msg=="T"){
			 				alert("刷新成功！");
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
		<div id="left" style="float: left; width: 240px; height: 400px;">
			<ul class="left_menu">
				<li><a href="<%=path %>/core/core_autocreate_list">全部生成</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list">生成sql</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list" >生成po对象</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list" >生成hbm文件</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list" >生成DAO</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list" >生成DAOImpl</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list" >生成Server</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list" >生成Action</a></li>
				<li><a href="<%=path %>/core/core_autocreate_list" >生成jsp</a></li>
			</ul>
		</div>
		<div id="right" style="width: 700px; height: 400px;">
			
			<div style="text-align: left;">
				当前数据库：<select id="currentDb" name="currentDb" >
					<c:forEach var="db" items="${dbList}">
						<c:if test="${defaultDb.id eq db.id }">
							<option value="${db.id }" selected="selected">${db.showName }</option>	
						</c:if>
						<c:if test="${defaultDb.id ne db.id }">
							<option value="${db.id }">${db.showName }</option>	
						</c:if>
					</c:forEach>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;<button id="autoCreateBtn">生成代码</button>
				&nbsp;&nbsp;&nbsp;&nbsp;<button id="refreshCacheBtn">刷新缓存</button>
			</div>
			
		</div>
	</body>
</html>
