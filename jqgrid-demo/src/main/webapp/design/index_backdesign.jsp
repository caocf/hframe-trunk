<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>����ϵͳ-�����ҳ</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=path %>/design/css/index.css">
	
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#db").click(function(){
				$("#dbcontent").css("display","");
			});
		});	
	
	
	</script>
	
  </head>
  
  <body background="./images/bg_13.jpg"  bgcolor="white" style="margin: 5px;">
  	<div id="left" style="float: left;width: 240px;height: 400px;">
		<ul class="left_menu">
			<li><a href="<%=path %>/core/core_enumgroup_displaylist">ö��ֵ����</a></li>
			<li><a href="<%=path %>/core/core_set_displaylist" >���ݼ�����</a></li>
			<li><a href="<%=path %>/core/core_set_displaylist" >�����ͼ</a></li>
			
		
		</ul>
  	</div>
  	<div id="right" style="width: 700px;height: 400px;">
  		<div id="dbcontent" >
  			<h1>���ݿ��б�</h1>
  			<table>
  				<c:forEach var="vo" items="${list }">
  					<tr>
  						<td>${vo.id }</td>
  						<td>${vo.dbName }</td>
  						<td>${vo.showName }</td>
  						<td>${vo.createTime }</td>
  						<td>${vo.descprition }</td>
  					</tr>
  				
  				</c:forEach>
  			
  			</table>
  		</div>
  		
  	</div>
  </body>
</html>
