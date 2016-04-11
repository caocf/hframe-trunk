<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

	<link rel="stylesheet" type="text/css" href="<%=path %>/design/css/index.css">
	
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#db").click(function(){
				$("#dbcontent").css("display","");
			});
		});	
	
	
	</script>
	
  </head>
  
  <body background=""  bgcolor="white" style="margin: 5px;">
  	<div id="left" style="">
		<table class="left_menu_table">
			<tr>
				<td><a href="<%=path %>/core/core_db_list">数据库管理</a></td>
			</tr>
			<tr>
				<td><a href="<%=path %>/core/core_table_list" >数据表管理</a></td>
			</tr>
			<tr>
				<td><a href="<%=path %>/core/core_tablecolumn_list" >数据列管理</a></td>
			</tr>
			<tr>
				<td><a href="<%=path %>/core/core_tablefk_list" >外键管理</a></td>
			</tr>
		</table>
		
  	</div>
  	<div id="right" style="width: 700px;height: 400px;">
  		<div id="dbcontent" >
  			<h1>数据库列表</h1>
  			<table>
  				<c:forEach var="vo" items="${list }">
  					<tr>
  						<td>${vo.id }</td>
  						<td>${vo.dbName }</td>
  						<td>${vo.showName }</td>
  						<td>${vo.createTime }</td>
  						<td>${vo.descprition }</td>
  					</tr>
  				
  				</c:forEach>
  			
  			</table>
  		</div>
  		
  	</div>
  </body>
</html>
