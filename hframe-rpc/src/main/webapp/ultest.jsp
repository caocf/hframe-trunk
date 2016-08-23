<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ultest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	<!--
	.box_menu_row{
	padding-bottom: 4px;font: bold 12px; border-bottom: #5FC84F 1px solid;margin:0px;
	}
	.box_menu_row li{
	display:inline;cursor:pointer;color:#006633;border: #5FC84F 1px solid;
	padding-right:0.5em;padding-left:1.0em;padding-bottom:4px; padding-top:5px;margin-left:1px;
	}
	
	.box_menu_row li.normal{
	background:#BADDB6;
	}
	.box_menu_row li.active{
	padding-top:8px;background:#fbfbfb; border-bottom:#fbfbfb 1px solid;
	}
	
	
	
	
	.box_menu_col{
	padding-bottom: 4px;font: bold 14px; margin:0px;width: 200px;
	list-style-image: url("imag1.jpg");
	}
	.box_menu_col li{
	cursor:pointer;color:#006633;border: #5FC84F 1px solid;
	padding-right:0.5em;padding-left:1.0em;padding-bottom:4px; padding-top:5px;margin-left:1px;width: 200px;
	list-style-image: url("imag1.jpg");
	}
	
	.box_menu_col li.normal{
	background:#BADDB6;
		list-style-image: url("imag1.jpg");
	
	}
	.box_menu_col li.active{
	background:#fbfbfb; border-left:#fbfbfb 1px solid;border-right:#fbfbfb 1px solid;
	}
	-->
	</style>
	
	<script type="text/jscript">
function funchage(obj)
{
 var boxmenuli=document.getElementsByTagName("li");
 for(var i=0; i<boxmenuli.length;i++)
 {
   boxmenuli[i].className = "normal";
 }
 obj.className="active";
}
</script>
  </head>
  
  <body>
	 <UL class=box_menu_row>&nbsp;
			<LI class=normal onclick="funchage(this)">首　页</LI>
			<LI class=active onclick="funchage(this)">公司资讯</LI>
			<LI class=normal onclick="funchage(this)">产品展示</LI>
			<LI class=normal onclick="funchage(this)">技术支持</LI>
			<LI class=normal onclick="funchage(this)">繁體中文</LI>
	</UL>
	 <UL class=box_menu_col>&nbsp;
			<LI class=normal onclick="funchage(this)">首　页</LI>
			<LI class=active onclick="funchage(this)">公司资讯</LI>
			<LI class=normal onclick="funchage(this)">产品展示</LI>
			<LI class=normal onclick="funchage(this)">技术支持</LI>
			<LI class=normal onclick="funchage(this)">繁體中文</LI>
	</UL>
  </body>
</html>
