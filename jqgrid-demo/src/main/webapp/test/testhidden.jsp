<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testHidden.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	
		function writeIframe(){
			document.frames("hiddenFrame").document.write("<div id='HIDDENCONTENT'>2222</div>");
			document.frames("hiddenFrame").document.close();
		}
	
		function getIframeCont(){
			document.getElementById("treeDiv").innerHTML=document.frames("hiddenFrame").document.getElementById("HIDDENCONTENT").innerHTML;
		}
	</script>
  </head>
  
  <body>
    This is my JSP psdage. <br>
        <iframe src="hidden.jsp" width="500" height="400" frameborder="1" id="hiddenFrame"></iframe>
    <button onclick="writeIframe()">writeIframe()</button>
    <button onclick="getIframeCont()">"getIframeCont"()</button>
    
    <div id="treeDiv">mei shu ju £¡</div>
    
  </body>
</html>
