<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  </head>
  
  <body background="./images/bg_11.jpg"   bgcolor="white" style="margin: 5px;">
  	<div id="left" style="float: left;width: 240px;height: 400px;">
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
