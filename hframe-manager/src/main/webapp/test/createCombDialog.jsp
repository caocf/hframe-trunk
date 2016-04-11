<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String VIEWS=request.getParameter("VIEW")==null?"":request.getParameter("VIEW");
String TYPES=request.getParameter("TYPE")==null?"":request.getParameter("TYPE");
String KEY=request.getParameter("KEY")==null?"":request.getParameter("KEY");
String FK=request.getParameter("FK")==null?"":request.getParameter("FK");


String GRIDCONDITION="".equals(KEY)?"":FK+"='"+KEY+"'";

String TITLES=request.getParameter("TITLE")==null?"":request.getParameter("TITLE");

String[] VIEW=VIEWS.split("\\|");
String[] TITLE=TITLES.split("\\|");
String[] TYPE=TYPES.split("\\|");



%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>UTF-8</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	--><%@ include file="/webframe/commonhead.jsp" %>
	
	<script type="text/javascript" src="<%=path %>/js/common/openDialog.js"></script>
<%@ include file="/webframe/mycommonhead.jsp" %>

	<script type="text/javascript">
		load();
		function ok(){
		
		}
		
	</script>
	
  </head>
  
  <body onload="load();" style="padding: 10px;text-align: left;" >
  
  <%if("FORM".equals(TYPE[0])){ %>
  	<zqh:myform colNum="3" formid="createcombdialog_parent" view="<%=VIEW[0] %>" objectId="<%=KEY %>" defaultBtn="" title="<%=TITLE[0] %>"></zqh:myform>
  <%} %>
  <%if("GRID".equals(TYPE[1])){ %>
  	<zqh:mygrid gridId="createcombdialog_sub" defaultBtn="ajax(createcombdialog_parent|createcombdialog_sub)" editable="true" condition="<%=GRIDCONDITION %>"  view="<%=VIEW[1] %>"  title="<%=TITLE[1] %>"></zqh:mygrid>
  <%} %>
  </body>
</html>
