<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 't.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
	
		function ok(){
			
			;
		}
	
	</script>
  </head>
  
  <body>
		
		<form id="testForm" name="testForm" action="" ajaxSubmit="alert(1);"  test='' onsubmit="alert(1)">
			<table>
				<tr>
					<td>id:</td>
					<td><input name="id"></td>
				</tr>
				<tr>
					<td>name:</td>
					<td><input name="name"></td>
				</tr>
				<tr>
					<td>age:</td>
					<td><input name="age"></td>
				</tr>

			</table>
		</form>

	
		<button id="subBtn" onclick=document.forms('testForm').submit()>提交</button>
		<button id="subBtn" onclick=document.forms('testForm').ajaxSubmit()>ajax提交</button>
		
		
  </body>
</html>
