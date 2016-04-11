<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String VIEW=request.getParameter("VIEW")==null?"":request.getParameter("VIEW");
String TYPE=request.getParameter("TYPE")==null?"":request.getParameter("TYPE");



%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>对话框</title>
    
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
		
		
		function ok(){
		
			var value;
			var text;
			if('LIST'=='<%=TYPE%>'){
			
				for(i=0;i<document.all("grid1_INPUT").length;i++){
					if(document.all("grid1_INPUT")[i].checked){
						value=document.all("grid1_INPUT")[i].value;
						if(value.indexOf("|")!=-1){
							text=value.substring(value.indexOf("|")+1,value.length);
							value=value.substring(0,value.indexOf("|"));
						}
					}
				
				}
			}
			if(value!=""){
						pushToReturnVal_Sys(value,text);
			}
			
			pushToReturnVal_Sys(123,"张三");
			pushToReturnVal_Sys(345,"李四");
			pushToReturnVal_Sys(11111111111);
			returnVal_Sys();
		}
		
		
	
	</script>
	
  </head>
  
  <body onload="load();" style="padding: 10px;text-align: center;" >
  
  <%if("LIST".equals(TYPE)){ %>
    	  	    	<zqh:mylist listId="grid1" view="<%=VIEW %>" condition="1=1" objects="<%=null %>" width="470" height="400" title="DiaryGroupList"></zqh:mylist>
  
  	
  <%} %>
    <button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"   onclick="ok();">确认</button>
    <button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"   onclick="cancel_Sys();">取消</button>
  </body>
</html>
