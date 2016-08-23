<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframework.common.util.VelocityUtil"%>
<%@page import="com.hframework.common.bean.Column"%>
<%@page import="com.hframework.common.bean.ShowType"%>
<%@page import="com.hframework.common.bean.Option"%>
<%@page import="com.hframework.common.bean.User"%>
<%@page import="com.hframework.common.ssh.service.CommonServ"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
	#userGridDiv{
		overflow: scroll;
		width: 1000px;
	}
	
	
	.icon-sub{
		padding:10px;
		width: 100px;
		height: 40px;
		
	}
	

	
	
	</style>
	
	<script type="text/javascript">
	
	function ajaxsub_Sys(targetObjId,subObjId){
		
		
		
		var targStr=$("#"+targetObjId).serialize();
		
		if(subObjId!=null){
			var	subStr=$("#"+subObjId).serialize();
			targStr=targStr+"&"+subStr+"&isCombine=true";
		}
		var	action=$("#"+targetObjId).attr("action");
		alert(action+"\n"+targStr);
		$.ajax({
			type:"get",
			url:action,
			data:targStr,
			success:function(msg){
				alert(msg);
				
			}
		});
	}
	
		
	
	</script>8
  </head>
  
    
  <body >
  	
	<hr/>
	<h3 style="text-align: left;">1、枚举值</h3>
	<zqh:myform formid="core_enum_group" view="core_enum_group" url="Hframe/core/_core_enumgroup_create" colNum="2" title="创建枚举值" object="<%=null %>" editable="true" width="1800" defaultBtn="" isAjax="">
	</zqh:myform>
	<zqh:mygrid gridId="core_enum" view="core_enum" width="500"  url="Hframe/core/_core_enum_batchCreate"objects="<%=null %>" title="" editable="true" defaultBtn="ajax(core_enum_group)"></zqh:mygrid>
	<h3 style="text-align: left;">2、数据集</h3>
	<zqh:myform formid="core_set" view="core_set" url="Hframe/core/_core_set_create" colNum="4" title="测试" object="<%=null %>" editable="true" width="1800" defaultBtn="" isAjax="">
	</zqh:myform>
	<zqh:mygrid gridId="core_field" view="core_field" width="500"  url="Hframe/core/_core_enum_batchCreate"objects="<%=null %>" title="测试新建" editable="true" defaultBtn="ajax(core_enum_group)"></zqh:mygrid>
	<h3 style="text-align: left;">3、创建列</h3>
d
	<zqh:mygrid gridId="core_table_column" view="core_table_column" width="500"  url="Hframe/core/_core_enum_batchCreate"objects="<%=null %>" title="新建" editable="true" defaultBtn="ajax(core_enum_group)"></zqh:mygrid>
	
	<h3 style="text-align: left;">3、全部加载</h3>
	<button>全部重新装载</button>
	<button>全部重新装载</button>
	
  </body>
</html>
