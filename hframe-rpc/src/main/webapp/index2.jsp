<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
	
	function refreshList(id,pageIndex,pageSize){
	
		var taginfo=document.getElementById(id+"_TAGINFO").value;
		taginfo=taginfo+"&pageSize="+pageSize+"&pageIndex="+pageIndex;
	
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_getListContentByAjax?"+taginfo,
			success:function(msg){
				var htmls = msg.split('@@');
				$("#"+id+" tbody").html(htmls[0]);
				$("#"+id+" .zqh_list_pagination").html(htmls[1]);
			}
		});
	}
	
	function openwin(){
		var retVal = window.showModalDialog("opMsmsAuth.jsp",window,"dialogHeight:350px;dialogWidth:450px;center:yes;");
		alert(retVal);
	}
	</script>
  </head>
  <body>
  <button onclick ="openwin();">open</button>
  <div>
  	<div style="width: 200px;height:500px;float: left;">
  		<h1>静态页面</h1>
	  	<ul>
	  		<li><a href="javascript:addTab_Sys('aaatab','按钮','button.jsp');">按钮----button.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','表单','form.jsp');" >表单----form.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','表格','grid.jsp');" >表单----grid.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','表格','list.jsp');" >列表----list.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','框架','tab.jsp');" >tab----tab.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','框架','frame.jsp');" >框架----frame.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','右键菜单','contextmenu2.jsp');" >右键菜单----contextmenu2.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','级联菜单','menubar.jsp');" >级联菜单----menubar.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','标签页','ultest.jsp');" >标签页----ultest.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','树','tree.jsp');">树----tree.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','动态输入框','tipInput.jsp');" >动态输入框，其他组件----tipInput.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','动态输入框','accordion.jsp');" >卷帘门--accordion.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','动态输入框','model.jsp');" >模态门--model.jsp</a></li>
	  	</ul>
	  	<h1>tag页面</h1>
	  	<ul>
	  		<li><a href="javascript:addTab_Sys('aaatab','表框','demo/formdemo.jsp');">表框----demo/formdemo.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','表格','demo/griddemo.jsp');">表格----demo/griddemo.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','列表','demo/listdemo.jsp');">列表----demo/listdemo.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','树','demo/treedemo.jsp');">树----demo/treedemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','级联下拉框','demo/selectdemo.jsp');">级联下拉框----demo/selectdemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','菜单','demo/menudemo.jsp');">菜单----demo/menudemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','框架','demo/framedemo.jsp');">框架----demo/framedemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','标签页','demo/tabdemo.jsp');" >标签页----demo/tab.jsp</a></li>
	  	</ul>
		</div>
		<zqh:frame height="500" style="float:left;clear:right;" width="1000" frameId="aaatab" isTab="true" src=""></zqh:frame>
		 <center>
		<%//VelocityUtil.produceTemplateContent("com/hframe/tag/vm/autoformtemplate.vm",map)
		 %>
		</center>
		 <center>
		<%//VelocityUtil.produceTemplateContent("com/hframe/tag/vm/autogridtemplate.vm",map)
		 %>
  		</center>

  		<a href="use/index.jsp" target="_blank">另外一个重要页面</a><br/>
  		<a href="test/testhidden.jsp" target="_blank">test源码</a><br/>
  		<a href="design/index.jsp" target="_blank">后台设计器</a><br/>
</div>	
  	<div style="hegiht:400px;">
  	<div style="height:500px;"></div>
  		  	<zqh:myform colNum="3" formid="enumgroupform" view="core_enum_group" objectId="" defaultBtn="" title="枚举值组"></zqh:myform>
			<zqh:mygrid gridId="enumgrid" defaultBtn="ajax(enumgroupform|enumgrid)" view="core_enum" editable="true" title="枚举值"></zqh:mygrid>
			
			
			<zqh:myform colNum="3" formid="enumgroupform" view="core_e111num" objectId="" defaultBtn="" title="test"></zqh:myform>
				<zqh:mylist listId="2222" view="SYS_DB_LIST" condition="1=1" objects="<%=null %>" title="数据库列表" defaultBtn="">
			<zqh:button src="test/createDialog.jsp?VIEW=core_db&TYPE=FORM&CONDITION=&TITLE=数据库" param="width:400;height:300" model="openwin-create" buttonId="1111" icon="" buttonName="新建" title="新建" ></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="test/createDialog.jsp?VIEW=core_db&TYPE=FORM&CONDITION=&TITLE=数据库" param="width:400;height:300" model="openwin-update" buttonId="1121" icon="" buttonName="修改" title="修改"></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="111" model="action-delete" buttonId="1311" icon="" buttonName="删除" title="删除"></zqh:button>
  	  	</zqh:mylist>
		</div>		
			
  </body>
</html>
