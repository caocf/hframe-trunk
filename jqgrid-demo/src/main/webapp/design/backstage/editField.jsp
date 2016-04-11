<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editField.jsp' starting page</title>
    <%@ include file="/webframe/commonhead.jsp" %>
	<%@ include file="/webframe/mycommonhead.jsp" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		
		function add (value){
			
			document.getElementById("effect").innerHTML=document.getElementById("effect").innerHTML+value;
		}

		function columnAdd(){
			var column= document.getElementById("123456").options[document.getElementById("123456").selectedIndex].text;
			var curVal =$("#effect").val();
			$("#effect").val(curVal+"\\${column:"+column+"}");
			
			var expression =$("#expression").val();
			$("#expression").val(expression+"\\${column:"+column+"}");
		}
		
		function textAdd(){
			var curVal =$("#effect").val();
			$("#effect").val(curVal+$("#textInput").val());
			var expression =$("#expression").val();
			$("#expression").val(expression+$("#textInput").val());
		}	
		
		function ok(){
		
		pushToReturnVal_Sys($("#effect").val(),$("#expression").val());
		returnVal_Sys();
		}

		function suchAdd(){
				var curVal =$("#effect").val();
				var expression =$("#expression").val();
				$("#effect").val(curVal+$("input[name=suchas]").val());
				$("#expression").val(expression+$("input[name=suchas]").val());
		} 
	
	</script>

  </head>
  
  <body>
  		<hr/>
		<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys('','1234516',this.value);" initial="true"></zqh:select><br/>
		<zqh:select selectId="1234516" visible="" view="table_ele" width="150px" onChange="refresh_Sys('','123456',this.value);" initial="true"></zqh:select><br/>
		<zqh:select selectId="123456" visible="" view="column_ele" width="150px" onChange="" initial="true"></zqh:select> 
		<button id="columnbtn" onclick="columnAdd()">add</button>
		<br/><br/>
		
		<hr/>
		t e x t :<input id="textInput"> <button id="colum1nbtn" onclick="textAdd()">add</button><br/><br/>
		<hr/>
		s q l :
		<hr/>
		h r e f :
		<hr/>
		
		<input type="radio" name="suchas" id="suchas" value="全选\${sys:allSelect}" onclick="">全选\${sys:allSelect} 
		<button id="colum1nbtn" onclick="suchAdd()">add</button><br/><br/>
		<hr />
		<br/><br/><br/><br/>
		expression<input id="expression" size="50"><br/><br/>
		effect<input id="effect" size="50"><br/>
		
		<center style="font-size: 18px;">
		<button id="colum1nbtn" onclick="ok()">ok</button><br/><br/>
		</center>
		  </body>
</html>
