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
	<div id="float_top">
		<div style = 'width:1000px;text-align: left;padding-top:1px;'>
			<span >welcome to hongframe space ..</span><span style="padding-left:200px;"><a href="">新建项目</a>
			<span style="padding-left:350px;">
			</span><span>选择项目：</span>
			<select>
				<option>学习中国</option>
			</select>
		</div>
		<div id="topflash" style = ''>
  			<h1 style="padding-top: 30px;">HongFrame设计在线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
  		</div>
	</div>
  	<div id="container">
  		<div id="top">
  			<div id="log">
  			</div>
  			<div id="topmenu">
  				<ul >
  					<li><a href="index_po.jsp" id="menu_vo" target="iframe">数据建模</a></li>
  					<li><a href="index_page.jsp" id="menu_page" target="iframe">页面层设计</a></li>
  					<li><a href="index_autocreate.jsp" id="menu_store" target="iframe">代码管理</a></li>
  					<li><a href="index_backdesign.jsp" id="menu_store" target="iframe">后台设计</a></li>
  					<li><a href="backstage/coreindex.jsp" id="menu_store" target="iframe">总页面(</a>
  					<a href="javascript:showBackWin();">弹出</a>)
  					</li>
  					
  				</ul>
  			</div>
  		</div>
  		
  		<div  >
  			<iframe src="index_index.jsp" width="1000px" height="600px" name="iframe" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
  		</div>
  	</div>
   
  </body>
</html>
