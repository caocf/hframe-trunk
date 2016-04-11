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
	
	<script type="text/javascript">
	
	function showBackWin(){
		window.showModelessDialog("backstage/coreindex.jsp",null,
					"scroll:yes;help:no;resizable:yes;status:yes;dialogHeight:700px;dialogWidth:900px");
	}
	
	
	</script>
  </head>
  <body  style="margin: 0px;">
	<div id="float_top" >
	
	</div>
  	<div id="container">
  		<div id="top">
  			<div id="log">
  			
  			</div>
  			<div id="topflash">
  				<h1 style="padding-top: 20px;">设计在线</h1>
  			</div>
  			<div id="topmenu">
  				<ul >
  					<li><a href="index_index.jsp" id="menu_home" target="iframe">首页</a></li>
  					<li><a href="index_po.jsp" id="menu_vo" target="iframe">PO层设计</a></li>
  					<li><a href="index_dao.jsp" id="menu_dao" target="iframe">DAO层设计</a></li>
  					<li><a href="index_service.jsp" id="menu_service" target="iframe">service层设计</a></li>
  					<li><a href="index_action.jsp" id="menu_action" target="iframe">action层设计</a></li>
  					<li><a href="index_page.jsp" id="menu_page" target="iframe">页面层设计</a></li>
  					<li><a href="index_store.jsp" id="menu_store" target="iframe">个人搜藏</a></li>
  					<li><a href="index_autocreate.jsp" id="menu_store" target="iframe">代码生成</a></li>
  					<li><a href="index_backdesign.jsp" id="menu_store" target="iframe">后台设计</a></li>
  					<li><a href="backstage/coreindex.jsp" id="menu_store" target="iframe">总页面(</a>
  					<a href="javascript:showBackWin();">弹出</a>)
  					</li>
  					
  				</ul>
  			</div>
  		</div>
  		
  		<div  >
  			<iframe src="index_index.jsp" width="1000px" height="400px" name="iframe"></iframe>
  		</div>
  		<div id="footer"></div>
  	</div>
   
  </body>
</html>
