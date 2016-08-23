<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>智能系统-设计首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=path %>/design/css/index.css">
	
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

	<script type="text/javascript">
	$(function(){
		$("#sessionBtn").click(function(){
			
			var content="当前数据库：<select id=\"currentDb\" name=\"currentDb\"  onchange='getTables()'>";
			
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
					content+="</select>";
					$("#right").html(content);
				}
			});
		
		});
		
		 
	});
	
	function getTables(){
		
		var dbId=$("select[name=currentDb]").find("option:selected").val();
		
		var content="当前数据表：<select id=\"currentTable\" name=\"currentTable\" >";
			
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_ajaxData?type=tableList",
			data:"dbId="+dbId,
			success:function(msg){
				var rows=msg.split(";");
				for(var i=0;i<rows.length;i++){
					if(rows[i]!=""){
						var cols=rows[i].split(":");
						content+="<option value='"+cols[0]+"'>"+cols[1]+"("+cols[2]+")</option>";
					}
				}
				content+="</select>";
				$("#right").html($("#right").html().split("当前数据表")[0]+content+"<button onclick='sessionChoose()'>确定</button>");
			}
		});

	}
	
	function sessionChoose(){
	
			var dbId=$("select[name=currentDb]").find("option:selected").val();
			var tableId=$("select[name=currentTable]").find("option:selected").val();
			
			$.ajax({
				type:"post",
				url:"<%=path%>/core/core_page_saveSession?dbId="+dbId+"&tableId="+tableId,
				SUCCESS:function(msg){
					if("T"==msg){
						alert("已保存");
					}
				
				}
			});
	}
	
	</script>
			
  </head>
  
 <body  background="./images/bg_14.jpg"  bgcolor="white" style="margin: 5px;">
 	<div id="left" style="float: left;width: 240px;height: 400px;text-align: left;">
		<ul class="left_menu">
			<li><a id="sessionBtn" href="#">第一步：选择session对象</a></li>
			<li><a href="<%=path %>/design/pagedesigner/pagedesigner.jsp?type=logindesigner" target="_blank">第二步：启用登陆对象</a></li>
			<li><a href="<%=path %>/core/core_tablecolumn_list" >数据列管理</a></li>
			<li><a href="<%=path %>/core/core_tablefk_list" >外键管理</a></li>
			<li><a href="<%=path %>/design/pagedesigner/pagedesigner.jsp" target="_blank">页面diy</a></li>
			<li><a href="<%=path %>/design/pagedesigner/pagedesignerframe.jsp" target="_blank">mutix页面diy</a></li>
			
			
		</ul>
  	</div>
  	<div id="right" style="width: 700px;height: 400px;text-align: left;">
  		
  	</div>
  </body>
</html>
