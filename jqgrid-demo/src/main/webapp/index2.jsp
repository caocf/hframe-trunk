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
  		<h1>��̬ҳ��</h1>
	  	<ul>
	  		<li><a href="javascript:addTab_Sys('aaatab','��ť','button.jsp');">��ť----button.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','��','form.jsp');" >��----form.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','���','grid.jsp');" >��----grid.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','���','list.jsp');" >�б�----list.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','���','tab.jsp');" >tab----tab.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','���','frame.jsp');" >���----frame.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','�Ҽ��˵�','contextmenu2.jsp');" >�Ҽ��˵�----contextmenu2.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','�����˵�','menubar.jsp');" >�����˵�----menubar.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','��ǩҳ','ultest.jsp');" >��ǩҳ----ultest.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','��','tree.jsp');">��----tree.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','��̬�����','tipInput.jsp');" >��̬������������----tipInput.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','��̬�����','accordion.jsp');" >������--accordion.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','��̬�����','model.jsp');" >ģ̬��--model.jsp</a></li>
	  	</ul>
	  	<h1>tagҳ��</h1>
	  	<ul>
	  		<li><a href="javascript:addTab_Sys('aaatab','���','demo/formdemo.jsp');">���----demo/formdemo.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','���','demo/griddemo.jsp');">���----demo/griddemo.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','�б�','demo/listdemo.jsp');">�б�----demo/listdemo.jsp</a></li>
	  		<li><a href="javascript:addTab_Sys('aaatab','��','demo/treedemo.jsp');">��----demo/treedemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','����������','demo/selectdemo.jsp');">����������----demo/selectdemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','�˵�','demo/menudemo.jsp');">�˵�----demo/menudemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','���','demo/framedemo.jsp');">���----demo/framedemo.jsp</a></li>
	 		<li><a href="javascript:addTab_Sys('aaatab','��ǩҳ','demo/tabdemo.jsp');" >��ǩҳ----demo/tab.jsp</a></li>
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

  		<a href="use/index.jsp" target="_blank">����һ����Ҫҳ��</a><br/>
  		<a href="test/testhidden.jsp" target="_blank">testԴ��</a><br/>
  		<a href="design/index.jsp" target="_blank">��̨�����</a><br/>
</div>	
  	<div style="hegiht:400px;">
  	<div style="height:500px;"></div>
  		  	<zqh:myform colNum="3" formid="enumgroupform" view="core_enum_group" objectId="" defaultBtn="" title="ö��ֵ��"></zqh:myform>
			<zqh:mygrid gridId="enumgrid" defaultBtn="ajax(enumgroupform|enumgrid)" view="core_enum" editable="true" title="ö��ֵ"></zqh:mygrid>
			
			
			<zqh:myform colNum="3" formid="enumgroupform" view="core_e111num" objectId="" defaultBtn="" title="test"></zqh:myform>
				<zqh:mylist listId="2222" view="SYS_DB_LIST" condition="1=1" objects="<%=null %>" title="���ݿ��б�" defaultBtn="">
			<zqh:button src="test/createDialog.jsp?VIEW=core_db&TYPE=FORM&CONDITION=&TITLE=���ݿ�" param="width:400;height:300" model="openwin-create" buttonId="1111" icon="" buttonName="�½�" title="�½�" ></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="test/createDialog.jsp?VIEW=core_db&TYPE=FORM&CONDITION=&TITLE=���ݿ�" param="width:400;height:300" model="openwin-update" buttonId="1121" icon="" buttonName="�޸�" title="�޸�"></zqh:button>&nbsp;&nbsp;&nbsp;
	  	  	<zqh:button src="111" model="action-delete" buttonId="1311" icon="" buttonName="ɾ��" title="ɾ��"></zqh:button>
  	  	</zqh:mylist>
		</div>		
			
  </body>
</html>
