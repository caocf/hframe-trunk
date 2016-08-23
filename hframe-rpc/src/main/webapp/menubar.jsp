<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframework.common.ssh.service.CommonServ"%>
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
	
	<title>css+js无限级菜单-w3css.com</title>
<style type="text/css">
<!--
*{ margin:0; padding:0; border:0;}
	body{font:12px/130% verdana,geneva,arial,helvetica,sans-serif,宋体;}li{list-style:none;}
	.clearfix:after{content:" ";display:block;height:0;clear:both;visibility:hidden;}.clearfix{display:inline-block;}
	a:link{ color:#000; text-decoration:none; }
	a:visited{ color:#000; text-decoration:none; }
	a:hover{ color:#000; text-decoration:none; }
	.menu{ width:778px; height:26px; background:#eee; margin:0 auto;}
	.menusel{ float:left;width:100px; position:relative; height:25px; background:#ddd; line-height: 25px; margin-left: 1px; *margin-left: 0px;_margin-left: -1px;}
	.menusel h2{ font-size:12px; }
	.menusel a{ display: block;text-align:center; width:100px;border:1px solid #a4a4a4; height:25px; border-bottom:1px solid #a4a4a4; position:relative; z-index:2;}
	.menusel a:hover{ border:1px solid #a4a4a4; border-bottom:1px dashed #eeeeee; position:relative; z-index:2; height:25px;}
	.ahover a{border-bottom:1px dashed #eeeeee; background:#eeeeee; }
	.position{ position:absolute; z-index:1;}
	.menusel ul{width:125px; background:#eee; border:1px solid #a4a4a4; margin-top: -1px; position:relative; z-index:1; display:none;}
	.menusel .block{ display:block;}
	.typeul li{border-bottom:1px dashed #a4a4a4;width:125px; position:relative; float:left; }
	.typeul li a{ border:none;width:125px; }
	.typeul li a:hover{ border:none; background:#ddd;}
	.typeul{  margin-left:0;  }
	.typeul ul{left:125px; top:0; position:absolute;}
	.fli{ margin-left: -1px; border-left:#eeeeee solid 1px;}
	.menusel .lli{ border:none; }
-->
</style>
<script type="text/javascript">document.execCommand("BackgroundImageCache", false, true);</script><!-- IE6背景图片闪烁问题  -->

  </head>
  
  <body>
  	<zqh:menu condition="" menuId="menusID" objects="<%=new CommonServ().getMenuList()%>" view="menu"></zqh:menu> 
  	
  	<br/>
  	<br/>
  	<br/>
  	<br/><br/><br/><br/><br/>
  	<br/>
  	
  	<script type="text/javascript">
 	injectMenuEvent("menusID");

</script>
















  	<zqh:menu condition="" menuId="menusID2" direction="2" objects="<%=new CommonServ().getMenuList()%>" view="menu"></zqh:menu> 

 	<script type="text/javascript">
 	
 	injectMenuEvent("menusID2");
 	/*
function injectMenuEvent(menuId){
		 for(var x = 1; x < 4; x++)
		{
		var menuid = document.getElementById(menuId+"_menu"+x);
		menuid.num = x;
		menuInit();
		}
 	}
 	

function menuInit()
	{
	var menuh2 = menuid.getElementsByTagName("h2");
	var menuul = menuid.getElementsByTagName("ul");
	var menuli = menuul[0].getElementsByTagName("li");
	menuh2[0].onmouseover = show;
	menuh2[0].onmouseout = unshow;
	menuul[0].onmouseover = show;
	menuul[0].onmouseout = unshow;
	function show()
	{
	menuul[0].className = "clearfix typeul block"
	}
	function unshow()
	{
	menuul[0].className = "typeul"
	}
	for(var i = 0; i < menuli.length; i++)
	 {
	 menuli[i].num = i;
	 var liul = menuli[i].getElementsByTagName("ul")[0];
	  if(liul)
	  {
	  typeshow()
	  }
	 }
	function typeshow()
	{
	menuli[i].onmouseover = showul;
	menuli[i].onmouseout = unshowul;
	}
	function showul()
	{
	menuli[this.num].getElementsByTagName("ul")[0].className = "block";
	}
	function unshowul()
	{
	menuli[this.num].getElementsByTagName("ul")[0].className = "";
	}
}
*/
</script>
  </body>
</html>
