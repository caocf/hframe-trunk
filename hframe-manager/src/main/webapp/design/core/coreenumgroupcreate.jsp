<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>枚举值创建</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		body {
			padding: 30px;
		}
	
	</style>
	
	<script type="text/javascript">
	
	$(function(){
			$("#submit1").click(function(){
			
				$("#form1").submit();
				alert("创建成功!");
				window.close();
			
			});
			
			$("#addBtn").click(function(){
			
			
				var tds=$("table tbody tr:last").html();
				
				$("table tbody:last").append($("table tbody tr:last").clone());
				//$("table tbody:last").append("<tr>"+tds+"</tr>");
				
			
			});
			
	});
		
	
	</script>

  </head>
  
  <body>
    <form id="form1" action="<%=path%>/core/core_enumgroup_create" target="_hiddenFrame">
    	<input id="id1" name="id1" value="${coreDb.id }" type="hidden">
  	<table>
		<tr>
			<td>枚举值组名称</td>
			<td><input id="dbname" name="coreEnumGroup.coreEnumGroupName" value="${coreDb.dbName }"></td>
		</tr>
		<tr>
			<td>枚举值显示类型</td>
			<td><input id="showname" name="coreEnumGroup.coreEnmuGroupShowType"  value="${coreDb.showName }"></td>
		</tr>
		<tr>
			<td>枚举值描述</td>
			<td><input id="description" name="coreEnumGroup.coreEnumDesc"  value="${coreDb.descprition }"></td>
		</tr>
		
		
    </table>
    
    <table border="1">
    	<thead>
    		<th>值</th><th>显示值</th><th>是否默认</th><th>优先级</th><th>描述</th>
    	</thead>
    	
    	<tbody>
	    	<tr>
	    		<td><input name="coreEnums.coreEnumValue"/> </td><td><input name="coreEnums.coreEnumDisplayValue"/></td> <td><input type="radio" name="coreEnums.coreEnumDefault" value="1">是<input type="radio" name="coreEnums.coreEnumDefault" value="0">否</td><td><input name="coreEnums.coreEnumPri" size="3"/></td><td><input name="coreEnums.coreEnumDesc"/> </td>
	    	</tr>
    	</tbody>
    
    	<tfoot>
	    	<tr>
	    		<td colspan="100%" align="right"><button id="submit1" name="submit1">提交</button><label style="width: 40px;"></label> <input id="addBtn" type="button"  value="添加一行"> </td>
	    	</tr>
	    	<tr>
		</tr>
    	</tfoot>
    </table>
    
    </form>
    
    <iframe id="_hiddenFrame" name="_hiddenFrame" style="display: none;"></iframe>
  </body>
  
</html>
