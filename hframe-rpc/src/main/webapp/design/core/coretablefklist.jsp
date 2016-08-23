<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

		<link rel="stylesheet" type="text/css"
			href="<%=path%>/design/css/index.css">

		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

		<script type="text/javascript">
		$(function(){
			
			
			
			 $("#currentDb").change(function(){
			 
			 	var dbid=$("select[name=currentDb]").find("option:selected").val();
			 	window.location.href="<%=path%>/core/core_tablefk_list?curDbId="+dbid;
			 
			 });
			 $("#currentTable").change(function(){
			 	
			 	var dbid=$("select[name=currentDb]").find("option:selected").val();
			 	var tableid=$("select[name=currentTable]").find("option:selected").val();
			 	var tableid2=$("select[name=currentTable2]").find("option:selected").val();
			 	
			 	window.location.href="<%=path%>/core/core_tablefk_list?curDbId="+dbid+"&curTableId="+tableid+"&curTableId2="+tableid2;
			 });
			  $("#currentTable2").change(function(){
			 	
			 	var dbid=$("select[name=currentDb]").find("option:selected").val();
			 	var tableid=$("select[name=currentTable]").find("option:selected").val();
			 	var tableid2=$("select[name=currentTable2]").find("option:selected").val();
			 	window.location.href="<%=path%>/core/core_tablefk_list?curDbId="+dbid+"&curTableId="+tableid+"&curTableId2="+tableid2;
			 });
			 
			
			$("#addBtn").click(function(){
					var columnId=$("input[name=columnId][checked]").val();
					var columnId2=$("input[name=columnId2][checked]").val();
					var relation=$("input[name=relation][checked]").val();
					
					
					
					$.ajax({
					
						type:"post",
						url:"<%=path%>/core/core_tablefk_add",
						data:"columnId="+columnId+"&columnId2="+columnId2+"&relation="+relation,
						success:function(msg){
							if("T"==msg){
								alert("保存成功！");	
								window.location.reload();						
							}
						}
					
					});
							
			});
			$("#deleteBtn").click(function(){
					
				var id=$("input[name=rids][checked]").val();
				var fkIds="";
				
				$("input[name=rids][checked]").each(function(){
						fkIds+=$(this).val()+",";
				});
					$.ajax({
						type:"post",
						url:"<%=path%>/core/core_tablefk_delete",
						data:"fkIds="+fkIds,
						success:function(msg){
							if("T"==msg){
								alert("保存成功！");	
								window.location.reload();						
							}
						}
					
					});
							
			});
			
			
			
		});	
	
	
	</script>

	</head>

	<body background="<%=path%>/design/images/bg_13.jpg" bgcolor="white"
		style="margin: 5px;">
		<div id="left" style="float: left; width: 240px; height: 400px;">
			<ul class="left_menu">
			<li><a href="<%=path %>/core/core_db_list">数据库管理</a></li>
			<li><a href="<%=path %>/core/core_table_list" >数据表管理</a></li>
			<li><a href="<%=path %>/core/core_tablecolumn_list" >数据列管理</a></li>
			<li><a href="<%=path %>/core/core_tablefk_list" >外键管理</a></li>


			</ul>
		</div>
		<div id="right" style="width: 700px; height: 400px;">
			
			<div style="text-align: left;">
				当前数据库：<select id="currentDb" name="currentDb" >
					<c:forEach var="db" items="${dbList}">
						<c:if test="${defaultDb.id eq db.id }">
							<option value="${db.id }" selected="selected">${db.showName }</option>	
						</c:if>
						<c:if test="${defaultDb.id ne db.id }">
							<option value="${db.id }">${db.showName }</option>	
						</c:if>
					</c:forEach>
				</select>
				当前数据表：<select id="currentTable" name="currentTable" >
					<c:forEach var="table" items="${tableList}">
						<c:if test="${defaultTable.tableId eq table.tableId }">
							<option value="${table.tableId }" selected="selected">${table.tableName }</option>	
						</c:if>
						<c:if test="${defaultTable.tableId ne table.tableId }">
							<option value="${table.tableId }">${table.tableName }</option>	
						</c:if>
					</c:forEach>
				</select>
				
			
				引用数据表：<select id="currentTable2" name="currentTable2" >
					<c:forEach var="table" items="${tableList}">
						<c:if test="${defaultTable2.tableId eq table.tableId }">
							<option value="${table.tableId }" selected="selected">${table.tableName }</option>	
						</c:if>
						<c:if test="${defaultTable2.tableId ne table.tableId }">
							<option value="${table.tableId }">${table.tableName }</option>	
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div id="dbcontent">
				<h1>
					数据列列表
				</h1>
				
				<table border="1" bordercolor="#bbb">
					<thead>
						<tr>
							<th width="100px">全选</th>
							<th width="100px">数据列</th>
							<th width="100px">关系</th>
							<th width="100px">引用列</th>
						</tr>
					</thead>
					
					<c:forEach var="relation" items="${relationList}">
						<tr>
							<td><input name="rids" type="checkbox" value="${relation.idFk }"> </td>
							<td>${relation.columnName }</td>
							<td>
							<c:if test="${relation.type eq 1}">一对一</c:if>
							<c:if test="${relation.type eq 2}">一对多</c:if>
							<c:if test="${relation.type eq 3}">多对一</c:if>
							<c:if test="${relation.type eq 4}">多对多</c:if>
							</td>
							<td>${relation.tableNameTo }.${relation.columnNameTo }</td>
						</tr>
					</c:forEach>
					
				</table>
				
				<form action="<%=path %>/core/_core_tablecolumn_update_tablecolumn_list" method="post">
				
				<input name="curDbId" value="${defaultDb.id }" type="hidden">
				<input name="curTableId" value="${defaultTable.tableId }" type="hidden">
				<input name="curTableId2" value="${defaultTable2.tableId }" type="hidden">
				<input id="deleteIds" name="deleteIds" value="" type="hidden">
				
				<table>
					<tr>
						<td>
							<table border="1" bordercolor="#bbb" cellpadding="5px;">

								<thead>
									<tr>
										<th>
											全选
										</th>
										<th>
											列名称
										</th>
										
										<th>
											展示名
										</th>
										
									</tr>
								</thead>
								<c:forEach var="vo" items="${columnList }">
										<c:if test="${fn:length(vo.relations) eq 0  }">
												<tr>
													<td>
														<input type="radio" name="columnId" value="${vo.id }"  />
													</td>
													<td>
														${vo.columnName }
													</td>
													<td>
													${vo.showName }
													</td>
													
												</tr>
										</c:if>
								</c:forEach>
							</table>
						</td>
						<td valign="middle">
							<table>
								<tr>
									<td><input name="relation" type="radio" value="1"></td>
									<td>一对一</td>
								</tr>
								<tr>
									<td><input name="relation" type="radio" value="2"></td>
									<td>一对多</td>
								</tr>
								<tr>
									<td><input name="relation" type="radio" value="3"></td>
									<td>多对一</td>
								</tr>
								<tr>
									<td><input name="relation" type="radio" value="4"></td>
									<td>多对多</td>
								</tr>
							
							
							</table>
						
						</td>
						<td>
							<table border="1" bordercolor="#bbb" cellpadding="5px;">

								<thead>
									<tr>
										<th>
											全选
										</th>
										<th>
											列名称
										</th>
										
										<th>
											展示名
										</th>
										
									</tr>
								</thead>
								<c:forEach var="vo" items="${columnList2 }">
			
									<tr>
										<td>
											<input type="radio" name="columnId2" value="${vo.id }" />
										</td>
										<td>
											${vo.columnName }
										</td>
										<td>
											${vo.showName }
										</td>
										
									</tr>
			
								</c:forEach>
							</table>
							
						</td>
						
						
					</tr>
					<tr>
						<th colspan="100%" align="center">
								<button id="addBtn">
									添加
								</button>
								&nbsp;&nbsp;&nbsp;
								<button id="deleteBtn">
									删除
								</button>


							</th>
					</tr>
					
				</table>
				

				
				</form>
			</div>

		</div>
	</body>
</html>
