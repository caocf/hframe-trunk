<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String VIEW=request.getParameter("VIEW")==null?"":request.getParameter("VIEW");
String TYPE=request.getParameter("TYPE")==null?"":request.getParameter("TYPE");
String KEY=request.getParameter("KEY")==null?"":request.getParameter("KEY");
String TITLE=request.getParameter("TITLE")==null?"":request.getParameter("TITLE");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>对话框</title>
	<%@ include file="/webframe/commonhead.jsp" %>
	<%@ include file="/webframe/mycommonhead.jsp" %>
	
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path %>/js/common/openDialog.js"></script>

	<script type="text/javascript">
		function ok(){
		
		}
		
	</script>
	
  </head>
  
  <body onload="load();" style="padding: 10px;text-align: center;" >
  
  <%if("FORM".equals(TYPE)){ %>
  	<zqh:myform colNum="3" formid="test" view="<%=VIEW %>" objectId="<%=KEY %>" defaultBtn="ajax" title="<%=TITLE %>"></zqh:myform>
  <%} %>
    <button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"   onclick="ok();">确认</button>
    <button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"   onclick="cancel_Sys();">取消</button>
  </body>
</html>
