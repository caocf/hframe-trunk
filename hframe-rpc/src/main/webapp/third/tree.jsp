<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="third/dtree/dtree.css">

	<script type="text/javascript" src="third/dtree/dtree.js"></script>
  </head>
  
  <body>
    This is my JSP page. <br>
    <script type="text/javascript">
      var dtree = new dTree('dtree');
      dtree.add(0,-1,"T16班");
      dtree.add(1,0,"教师",'index.jsp');
      dtree.add(2,1,"班主任",'index.jsp');
      dtree.add(3,1,"教员");
      dtree.add(4,0,'班干部','index.jsp');
      dtree.add(5,4,'班长','index.jsp');
      dtree.add(6,4,'学委','index.jsp');
      dtree.add(7,0,'学员','index.jsp');
      dtree.add(8,7,'学员1','index.jsp');
      dtree.add(9,7,'学员2','index.jsp');
      dtree.add(10,7,'学员3','index.jsp');
      dtree.add(11,7,'学员4','index.jsp');
      dtree.add(12,7,'学员5','index.jsp');
      document.write(dtree);
 </script>
 
 <img src="third/dtree/img/base.gif">
  </body>
</html>
