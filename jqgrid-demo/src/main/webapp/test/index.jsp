<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	
	<script type="text/javascript" src="<%=path %>/js/common/openDialog.js"></script>
	
	<style type="text/css">
	
	.magnifier_img_sys  {
		
		border: 1px solid #ddd;
		margin-left: 1px;
		margin-bottom: 0px;
		padding-bottom: 0px;
	
	}
	
	</style>
	 	<script type="text/javascript">
  		
  		function open1(thisObj){
  		
	  		var transObj=new Object();
	  		transObj.name="����";
	  		transObj.age=48;
	  		/*
	  		1��refreshFlag:�Ƿ��ҳ��Ҫˢ��
				a.��ʾ��Ҫˢ��
				b.����Ҫˢ��	  		
 		
	  		*/
	  		var retObj=showMyDialog_Sys("myDialog.jsp",400,300,0,transObj);
	  		
	  		if(retObj!=null){
		  		alert(retObj[2]);
		  		alert(retObj[1][1]);
		  		
	  		}
		  		
  		}
  		
  	
  	</script>
  
  </head>
  	
 
  <zqh:button src="myDialog.jsp" model="dialog" text="����" param="" onreturn="refresh" targetobject="form1" targettype="form" isajax="true"></zqh:button>
  
  <zqh:button src="myDialog.jsp" model="dialog" text="ѡ��ֹ�˾" onreturn="setvalue" target="this.grid1"></zqh:button>
  <zqh:button src="myDialog.jsp" model="dialog" text="ɾ��" onreturn="refresh" target="this.grid1"></zqh:button>
  
  <body>
    
    <a href="javascript:void(0);" onclick="open1(this)">ѡ����֯</a>
    
    
    
   <input id="a2aa" ><input id="aaa" ><a href="javascript:void(0);" onclick="magnifier_Sys(this,'myDialog.jsp?VIEW=DiaryGroupList&TYPE=LIST&CONDITION=&TITLE=����','{user_id=100&user_name=zhangsan}',400,500)"><img src="<%=path %>/test/fangdajing.jpg" width="20" height="20" class="magnifier_img_sys"></a>
    
  </body>
</html>
