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
			
				var dbname=$("#dbname").val();
				var showname=$("#showname").val();
				var description=$("#description").val();
				
				var id=$("#id1").val();
				
				if(showname==""){
					showname=dbname;
				}
				if(description==""){
					description=showname;
				}
				var url="<%=path%>/core/core_db_create";
				var data="dbname="+dbname+"&showname="+showname+"&description="+description+"&id="+id;	
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
    	<input id="id1" name="id1" value="${coreDb.id }" type="hidden">
  	<table>
		<tr>
			<td>数据库名称</td>
			<td><input id="dbname" name="dbname" value="${coreDb.dbName }"></td>
		</tr>
		<tr>
			<td>库显示名称</td>
			<td><input id="showname" name="showname"  value="${coreDb.showName }"></td>
		</tr>
		<tr>
			<td>数据库描述</td>
			<td><input id="description" name="description"  value="${coreDb.descprition }"></td>
		</tr>
		
		<c:if test="${coreDb.createTime ne null}">
			<tr>	
				<td>创建时间</td>
				<td><input id="createtime" name="createtime"  value="${coreDb.createTime }"></td>
			</tr>
		</c:if>
		
		<tr>
			<td colspan="2" align="center"><button id="submit1" name="submit1">提交</button></td>
		</tr>
    </table>
    </form>
    
  </body>
  
</html>
