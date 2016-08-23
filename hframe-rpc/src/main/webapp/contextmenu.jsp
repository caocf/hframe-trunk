<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ include file="/webframe/commonhead.jsp" %>
    <%@ include file="/webframe/mycommonhead.jsp" %>
    

	

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'frame.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 引入bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
      $('.context').contextmenu();
 </script>
		
		
		
  </head>
  
  <body>
  
  	<div id="context" data-toggle="context" data-target="#context-menu">
		<div id="context-menu">
		  <ul class="dropdown-menu" role="menu">
		    <li><a tabindex="-1" href="#">Action</a></li>
		    <li><a tabindex="-1" href="#">Separated link</a></li>
		  </ul>
		</div>
	</div>
  
  <div style="height: 1200px;" id="hahhahhah ">
  	
  
  </div>
 <span id="contextmenu" style="border:1px solid #666666;background:#eeeeee;width:55px;padding:5px;display:none;position:absolute">
 
 <ul>
 <li><a href="javascript:RIGHT_save();">保存</a></li>
 <li><a href="javascript:RIGHT_编辑();">编辑</a></li>
 <li><a href="javascript:RIGHT_save();">删除</a></li>
 <li><a href="javascript:RIGHT_save();">查看</a></li>
 
 </ul>
</span>
<script>
//当用户使用鼠标右键单击客户区打开上下文菜单时触发函数

var rightOpObj;
document.oncontextmenu=function (){    
	
//定位 id 为 contextmenu 的层的 X 坐标到鼠标单击时的 X 坐标偏右10象素
    contextmenu.style.posLeft= document.body.scrollLeft+event.x+10   
//定位 id 为 contextmenu 的层的 Y 坐标到鼠标单击时的 Y 坐标偏下10象素
    contextmenu.style.posTop= document.body.scrollTop+event.y+10
//以行内元素方式显示 contextmenu 层
    contextmenu.style.display="inline"
    
    rightOpObj=document.activeElement;
    return false             //返回 false，以屏蔽真正的右键菜单的显示
}
document.onclick=function(){//当用户用鼠标左键单击客户区时触发函数
//如果 contextmenu 层非当前焦点对象
    if(document.activeElement!=contextmenu)
       contextmenu.style.display="none"   //隐藏 contextmenu 层
}

function RIGHT_save(){

	rightOpObj.innerHTML+="1111111111111111111111111111<br/>";

}
function RIGHT_编辑(){

	rightOpObj.innerHTML+="1111111111111111111111111111<br/>";

}


</script> 
  </body>
</html>
