<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


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
		
			$("#createBtn").click(function(){
				var url="<%=path%>/design/core/coreenumgroupcreate.jsp?type=create";
				window.showModalDialog(url,"","dialogHeight:400px;dialogWidth:800px;center:yes;help:no;resizable:yes;status:no;scroll:yes;");
				window.location.reload();
			});
			
			$("#updateBtn").click(function(){
				var dbid=$("input[name=dbid][checked]").val();
				var url="<%=path%>/core/_core_db_detail_db_create?dbId="+dbid;
				window.showModalDialog(url,"","dialogHeight:400px;dialogWidth:600px;center:yes;help:no;resizable:yes;status:no;scroll:yes;");
				window.location.reload();
			});
			
			$("#deleteBtn").click(function(){
				var dbid=$("input[name=dbid][checked]").val();
				if("undefined"==dbid||dbid==""){
					alert("请选择要删除的数据库");
					return ;
				}
				$.ajax({
					type:"post",
					url:"<%=path%>/core/core_db_delete?coreDb.id="+dbid,
					success:function(msg){
						if("T"==msg){
							alert("删除成功！");						
						}else{
							alert("删除失败！");	
						}
						window.location.reload();
					}
				});
			});
		});	
	</script>

	</head>

	<body background="<%=path%>/design/images/bg_13.jpg" bgcolor="white"
		style="margin: 5px;">
	<div id="left" style="float: left;width: 240px;height: 400px;">
		<ul class="left_menu">
			<li><a href="<%=path %>/core/core_enumgroup_displaylist">枚举值管理</a></li>
			<li><a href="<%=path %>/core/core_table_list" >数据集管理</a></li>
			
		
		</ul>
  	</div>		<div id="right" style="width: 700px; height: 400px;">
			<div id="dbcontent">
				<h1>
					枚举值列表1
				</h1>
				<table border="1" bordercolor="#bbb" cellpadding="5px;">

					<c:forEach var="vo" items="${coreEnumGroupList }">
						<tr>
							<td>
								<input type="checkbox" name="dbid" value="${vo }" />
							</td>
							<td>
								${vo }
							</td>
						</tr>
					</c:forEach>
					<tfoot>
						<tr>
							<th colspan="100%" align="center">
								<button id="createBtn">
									新建
								</button>
								&nbsp;&nbsp;&nbsp;
								<button id="updateBtn">
									修改
								</button>
								&nbsp;&nbsp;&nbsp;
								<button id="deleteBtn">
									删除
								</button>


							</th>
						</tr>
					</tfoot>

				</table>
			</div>

		</div>
	</body>
</html>
