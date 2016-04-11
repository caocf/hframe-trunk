<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String dbId=request.getParameter("dbid");
	String dbIdPara="";
	if(dbId!=null){
		 dbIdPara=dbId;
	}
	
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'coredbcreate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	
	$(function(){
			$("#submit1").click(function(){
			
				var tableName=$("input[name=coreTable.tableName]").val();
				var tableDesc=$("input[name=coreTable.tableDesc]").val();
				
				var id=$("#id1").val();
				var dbId=$("#dbId").val();
				
				if('<%=dbIdPara%>'!=""){
					dbId='<%=dbIdPara%>';
				}
				
				var url="<%=path%>/core/core_table_create";
				var data="coreTable.tableName="+tableName+"&coreTable.tableDesc="+tableDesc+"&coreTable.tableId="+id+"&coreTable.dbId="+dbId;
				$.ajax({
					type:"post",
					url:url,
					data:data,
					success:function(msg){
						if("T"==msg){
							alert("创建成功！");
							window.close();
						}
						if("TT"==msg){
							alert("修改成功！");
							window.close();
						}
					}
				});
			
			});
	});
		
	
	</script>

  </head>
  
  <body>
    <form action="">
    	<input id="id1" name="id1" value="${coreTable.tableId }" type="hidden">
    	<input id="dbId" name="dbId" value="${coreTable.dbId }" type="hidden">
  	<table>
		<tr>
			<td>数据表名称</td>
			<td><input id="coreTable.tableName" name="coreTable.tableName" value="${coreTable.tableName }"></td>
		</tr>
		<tr>
			<td>表显示名称</td>
			<td><input id="coreTable.tableDesc" name="coreTable.tableDesc"  value="${coreTable.tableDesc }"></td>
		</tr>
		
		<c:if test="${coreTable.createTime ne null}">
			<tr>	
				<td>创建时间</td>
				<td><input id="coreTable.createTime" name="coreTable.createTime"  value="${coreTable.createTime }"></td>
			</tr>
		</c:if>
		
		<tr>
			<td colspan="2" align="center"><button id="submit1" name="submit1">提交</button></td>
		</tr>
    </table>
    </form>
    
  </body>
  
</html>
