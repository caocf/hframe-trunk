<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%@page import="java.io.File"%>
<%@ page import="com.hframe.common.util.FileUtils" %>

<%
	String path = request.getContextPath();
	String packagePath=request.getParameter("package")==null?"":request.getParameter("package");
	String chooseFile=request.getParameter("chooseFile")==null?"":request.getParameter("chooseFile");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<title>文件选择页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">

		var baseUrl="";

		//查看文件
		function showFile(fileName){
			var href=window.location.href;
			if(href.indexOf("package=") > -1) {
				href += "/" + fileName;
			}else {
				if(href.indexOf("?") > -1) {
					href += "&package=/" + fileName;
				}else {
					href += "?package=/" + fileName;
				}
			}
			window.location.href = href;
		}

		//回退
		function goBack(){
			var returnVal="";
			var href=window.location.href;
			if(href.indexOf("package=")!=-1){
				baseUrl=href.substring(0,href.indexOf("&package="));
				returnVal=href.substring(href.indexOf("&package="));
			}else{
				baseUrl=href;
				returnVal="&package=";
			}
			window.location.href=baseUrl+returnVal.substring(0,returnVal.lastIndexOf("/"));

		}

		function replaceFile(fileName){
			var packagePath="";
			var href=window.location.href;
			if(href.indexOf("package=")!=-1){
				packagePath=href.substring(href.indexOf("package=")+8);
			}else{
				packagePath="";
			}
			packagePath+="/"+fileName;

			if(parent!=null){
				parent.document.getElementById("description").innerHTML=packagePath;
			}
			//document.getElementById("description").innerHTML=packagePath;
		}
		function openFileNewWindow(fileName){

			var packagePath="";
			var href=window.location.href;
			if(href.indexOf("package=")!=-1){
				packagePath=href.substring(href.indexOf("package=")+8);
			}else{
				packagePath="";
			}
			packagePath+="/"+fileName;
            window.open("<%=path%>"+packagePath);

		}
		function openfile(fileName){

			var packagePath="";
			var href=window.location.href;
			if(href.indexOf("package=")!=-1){
				packagePath=href.substring(href.indexOf("package=")+8);
			}else{
				packagePath="";
			}
			packagePath+="/"+fileName;

            parent.loadfile(packagePath.substring(1));
		}

		function chooFile(fileName){

			var returnVal="";
			var href=window.location.href;


			if(href.indexOf("package=")!=-1){
				baseUrl=href.substring(0,href.indexOf("&package="));
				returnVal=href.substring(href.indexOf("&package="))+"/"+fileName;
			}else{
				baseUrl=href;
				returnVal="&package=/"+fileName;
			}
			//alert(returnVal.substring(returnVal.indexOf("&package=")+10));
			parent.returnFile(returnVal.substring(returnVal.indexOf("&package=")+10));
		}

	</script>


</head>

<body>
<div style="text-align: left;background-color: #eee;">
	<ul>
		<%
			if(!"".equals(packagePath)){
		%>
		<li><a href="#" onclick="goBack()">...</a></li>
		<%
			}
		%>
		<%//展示文件夹列表

			File[] fileList= FileUtils.getFileList(new File(request.getRealPath("/" + packagePath)));
			for(int i=0;i<fileList.length;i++){
				if(fileList[i].isDirectory()){

					if(("".equals(packagePath)/*&&FileUtils.showThisDir(fileList[i].getName())*/)||!"".equals(packagePath)){
		%>
		<li><a href="#" onclick="showFile('<%=fileList[i].getName() %>')"><%=fileList[i].getName() %></a></li>

		<%			}
		}
		}
		%>
		<%//展示文件列表

			for(int i=0;i<fileList.length;i++){


				if(fileList[i].isDirectory()!=true){
		%>
		<li><%=fileList[i].getName() %>

			<%if(!"1".equals(chooseFile)){ %>
            <a href="javascript:openfile('<%=fileList[i].getName() %>')" >打开</a>
            <a href="javascript:openFileNewWindow('<%=fileList[i].getName() %>');">新窗口打开</a>
            <a href="#" onclick="replaceFile('<%=fileList[i].getName() %>')">替换</a>
            <%}else{ %>
			<a href="#" onclick="chooFile('<%=fileList[i].getName() %>')">&nbsp;选择</a>

			<%} %>
		</li>
		<%
				}
			}
		%>

	</ul>

</div>  </body>
</html>
