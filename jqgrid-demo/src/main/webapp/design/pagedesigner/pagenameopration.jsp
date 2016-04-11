<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>

<%@taglib prefix="zqh" uri="/WEB-INF/zqhframe.tld" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			
String packagePath=request.getParameter("package")==null?"":request.getParameter("package");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'pagenameopration.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

  </head>
  
  
  
  <script type="text/javascript">
  
    var dynObjs = window.dialogArguments;
    
   
    var dynObjStr="";
    if(dynObjs!=null){
   		for(var i=0;i<dynObjs.length;i++){
	    	dynObjStr+="  "+dynObjs[i].getView()+":"+dynObjs[i].getMethod();
	    }
    
    }
    
	
	$(function(){
			
			var content="";
			
			$.ajax({
				type:"post",
				url:"<%=path%>/core/core_page_ajaxData?type=dbList",
				success:function(msg){
					var rows=msg.split(";");
					for(var i=0;i<rows.length;i++){
						
						if(rows[i]!=""){
							var cols=rows[i].split(":");
							content+="<option value='"+cols[0]+"'>"+cols[1]+"("+cols[2]+")</option>";
						}
					}
					$("#currentDb").html(content);
					getTables();
				}
			});
		
		
		 
	});
	
	var bgContent="";
	
	
	
	function getTables(){
		
		var dbId=$("select[name=currentDb]").find("option:selected").val();
		
		var content="";
			
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_ajaxData?type=tableList",
			data:"dbId="+dbId,
			success:function(msg){
				var rows=msg.split(";");
				for(var i=0;i<rows.length;i++){
					if(rows[i]!=""){
						var cols=rows[i].split(":");
						
						if(dynObjs!=null&&dynObjs.length>0){
							if(dynObjs[0].getView()==cols[1]){
								content+="<option value='"+cols[1]+"' selected='selected'>"+cols[1]+"("+cols[2]+")(推荐)</option>";
								continue;
							}
						}
						content+="<option value='"+cols[1]+"'>"+cols[1]+"("+cols[2]+")</option>";
					}
				}
				$("#currentTable").html(content);
			}
		});

	}
	
	function getTableContent(){
		
		
		var dbName=$("select[name=currentDb]").find("option:selected").html();
		
		dbName=dbName.substring(0,dbName.indexOf("("));
		
		var tableName=$("select[name=currentTable]").find("option:selected").val();
		
		
		var methodName=$("input[name=methodSelect][checked]").val();
		
		if("other"==methodName){
			methodName=$("#inputMethod").val();
		}
		
		
		
		var pathName=tableName.replace(new RegExp("_","g"),"")+""+methodName.replace(new RegExp("_","g"),"");
		
		
		pathName="/"+dbName.replace(new RegExp("_","g"),"").toLowerCase()+"/"+tableName.replace(new RegExp("_","g"),"")+"/"+pathName+".jsp";
		if("model"==methodName){
			pathName=$("#inputMethod2").val();
			if(methodName==""){
				pathName="model"+new Date();
			}
			
			pathName="model/"+pathName+".jsp";

		}
		
		
		document.getElementById("description").innerHTML=pathName;
		
		
	}
	
	function showInput(){
	
		document.getElementById("inputMethod").style.display="";
	
	}
	function showInput2(){
	
		document.getElementById("inputMethod2").style.display="";
	
	}
  
  	function ok(){
  	
  		if(document.getElementById("description").innerHTML!=""){
  		
  			window.parent.returnValue=document.getElementById("description").innerHTML;
  			window.close();
  		}else{
  			alert("请输入或者选择文件！");
  		}
  	}
 
  
  
  </script>
  
  
  <body>
    <div id="bgTabsContent" style="text-align: center; "  >
	
	<table style="">
		<tr>
			<th align="right">DB:</th>
			<td><select id="currentDb" name="currentDb"  onchange='getTables()'></select></td>
		</tr>
		<tr>
			<th  align="right">TABLE:</th>
			<td><select id="currentTable" name="currentTable" onchange='getTableContent()'></select>
				
			</td>
		</tr>
		<tr>
			<th  align="right">方法:</th>
			<td><input type="radio" name="methodSelect" value="list" onclick="getTableContent()" >list
				<input type="radio" name="methodSelect" value="save" onclick="getTableContent()" >save
				<input type="radio" name="methodSelect" value="edit" onclick="getTableContent()" >edit
				<input type="radio" name="methodSelect" value="other" onclick="showInput(),getTableContent()" >other
				<input id="inputMethod" style="display: none;"  onkeyup="getTableContent()">
			</td>
		</tr>
		
		<tr>
			<th  align="right">模板:</th>
			<td>
				<input type="radio" name="methodSelect" value="model" onclick="showInput2(),getTableContent()" >
				<input id="inputMethod2" style="display: none;" onkeyup="getTableContent()">
			</td>
		</tr>
		

		<tr>
			<th align="right">描述:</th>
			<td><label id="description"></label> </td>
		</tr>
		
		<tr>
			<td colspan="2"><button id="ok" onclick="ok()">确定</button> </td>
		</tr>	
		
	</table>
	<script type="text/javascript">
			document.write(dynObjStr);
			document.close();
	</script>
	<hr>
	
	<div>
    <zqh:frame height="210" style="" width="390" frameId="myframe2" src="showFileSystem.jsp"></zqh:frame>
	</div>
	
	<!-- 
	<div style="text-align: left;">
		<h2>列表</h2>
	<ul>
		<%
			if(!"".equals(packagePath)){
		 %>
		 	<li><a href="#" onclick="goBack()">...</a></li>
		<%	
			}
		 %>
		<%
		
			File[] fileList=FileUtil.getFileList(new File(request.getRealPath("/"+packagePath)));
			for(int i=0;i<fileList.length;i++){
			
			
				if(fileList[i].isDirectory()==true){
		%>
					<li><a href="#" onclick="showFile('<%=fileList[i].getName() %>')"><%=fileList[i].getName() %></a></li>
		<%	
				}else{
		%>
					<li><%=fileList[i].getName() %>
						<a href="#" onclick="replaceFile('<%=fileList[i].getName() %>')">&nbsp;替换</a>
						<a href="#" onclick="readFile('<%=fileList[i].getName() %>')">&nbsp;查看</a>
						<a href="#" onclick="loadFile('<%=fileList[i].getName() %>')">&nbsp;加载</a>
					 </li>
		<%			
				}
		 	}
		%>	
	
	</ul>

	</div>
	 -->
	<hr>
		
	</div>	
  </body>
</html>
