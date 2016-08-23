<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pagedesignerframe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
	#toolsDiv{
		position: absolute;
		border: 1px solid ddd;
		background-color: eee;
		left: 10px;
		top:40px;
		
	}
	</style>
	
	<script type="text/javascript">
		function addTab() {
			addTab_Sys("aaatab","未命名","design/pagedesigner/pagedesigner.jsp",1260,580);
		}
		
		function toolDivToggle(){
			$("#toolsDiv").toggle();
		}
		
		$(function(){
			$(document).keydown(function(e){
				if(e.ctrlKey&&e.shiftKey&&e.keyCode==84){//Ctrl+Shift+T 打开文件系统
					toolDivToggle();
				}
			});
			
		});
		
		function loadFile(src){
			var fileName = src.substring(src.lastIndexOf("/")+1);
			src = "design/pagedesigner/pagedesigner.jsp?src="+src;
			addTab_Sys("aaatab",fileName,src,1260,580);
		}
	
	</script>
	
  </head>
  
  <body>
  
  <div id="toolsDiv" style="width: 290px;text-align: left;z-index: 1000">
  	<zqh:frame height="510" style="" width="390" frameId="myframe2" src="design/pagedesigner/showFileSystem.jsp"></zqh:frame>
  </div>
  
		<zqh:frame height="580" style="float:left;clear:right;" width="1260" frameId="aaatab" isTab="true" src="design/pagedesigner/pagedesigner.jsp"></zqh:frame>
	</body>
</html>
