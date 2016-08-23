<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>设计会员登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body >
   	<div id=" container" align="center" style="background: url('./images/bg_5.jpg') no-repeat center top ;height: 600px;">
   		<div id="content" >
   			<div style="height: 400px;">&nbsp;</div>
   			<div style="margin-left: 600px;">
   		<form action="loginAction.jsp" method="post">
   			<table>
   				<thead>
   					<tr>
   						<th colspan="2">设计用户登录</th>
   					</tr>
   				</thead>
   				<tr>
   					<td>用户名：</td>
   					<td><input name="username" ></td>
   				</tr>
   				<tr>
   					<td>密码：</td>
   					<td><input type="password" name="password"></td>
   				</tr>
   				<tr>
   					<td colspan="2" align="center"><input type="submit"  value="登陆"></td>
   				</tr>
   				<tr>
   					<td colspan="2" align="center"><font color="red" size="2"> ${msg}</font></td>
   				</tr>
   			
   			
   			</table>
   		</form>
   		</div>
   		</div>
   		
   		</div>
   
  </body>
</html>
