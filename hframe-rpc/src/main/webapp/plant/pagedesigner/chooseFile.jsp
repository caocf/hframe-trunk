<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="zqh" uri="/WEB-INF/zqhframe.tld" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'chooseFile.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function returnFile(fileName){
			window.returnValue=fileName;
			window.close();
		}
		
	</script>
  </head>
  
   <body>
   
	<hr>
	
	<div>
    <zqh:frame height="410" style="" width="390" frameId="myframe2" src="showFileSystem.jsp?chooseFile=1"></zqh:frame>
	</div>
	
	
	<hr>

  </body>
</html>
