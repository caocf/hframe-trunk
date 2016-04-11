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
	 
  	function showFile(fileName){

  		var returnVal="";
  		var href=window.location.href;

  		if(href.indexOf("package=")!=-1){
  			baseUrl=href.substring(0,href.indexOf("&package="));
  			returnVal=href.substring(href.indexOf("&package="))+"/"+fileName;
  		}else{
  			baseUrl=href;
  			returnVal="&package=/"+fileName;
  		}
  		window.location.href=baseUrl+returnVal;
  	}
	



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
  function readFile(fileName){
	
		var packagePath="";
		
		
		var href=window.location.href;
  		
  		if(href.indexOf("package=")!=-1){
  		
  			packagePath=href.substring(href.indexOf("package=")+8);
  		}else{
  			packagePath="";
  		}
  		
  		packagePath+="/"+fileName;
  		
  	//	alert("<%=path%>"+packagePath);
  		
  		window.open("<%=path%>"+packagePath);
  		
	}
	function loadFile(fileName){
	
		var packagePath="";
		
		
		var href=window.location.href;
  		
  		if(href.indexOf("package=")!=-1){
  		
  			packagePath=href.substring(href.indexOf("package=")+8);
  		}else{
  			packagePath="";
  		}
  		
  		packagePath+="/"+fileName;
  		
// 		alert("<%=path%>"+packagePath);
  		parent.loadFile(packagePath.substring(1));
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
		<h2>列表</h2>
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
							<a href="#" onclick="replaceFile('<%=fileList[i].getName() %>')">&nbsp;替换</a>
							<a href="javascript:readFile('<%=fileList[i].getName() %>');">&nbsp;查看</a>
							<a href="javascript:void(0)" onclick="loadFile('<%=fileList[i].getName() %>')">&nbsp;加载</a> 
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
