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
<!-- ����bootstrap -->
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
 <li><a href="javascript:RIGHT_save();">����</a></li>
 <li><a href="javascript:RIGHT_�༭();">�༭</a></li>
 <li><a href="javascript:RIGHT_save();">ɾ��</a></li>
 <li><a href="javascript:RIGHT_save();">�鿴</a></li>
 
 </ul>
</span>
<script>
//���û�ʹ������Ҽ������ͻ����������Ĳ˵�ʱ��������

var rightOpObj;
document.oncontextmenu=function (){    
	
//��λ id Ϊ contextmenu �Ĳ�� X ���굽��굥��ʱ�� X ����ƫ��10����
    contextmenu.style.posLeft= document.body.scrollLeft+event.x+10   
//��λ id Ϊ contextmenu �Ĳ�� Y ���굽��굥��ʱ�� Y ����ƫ��10����
    contextmenu.style.posTop= document.body.scrollTop+event.y+10
//������Ԫ�ط�ʽ��ʾ contextmenu ��
    contextmenu.style.display="inline"
    
    rightOpObj=document.activeElement;
    return false             //���� false���������������Ҽ��˵�����ʾ
}
document.onclick=function(){//���û��������������ͻ���ʱ��������
//��� contextmenu ��ǵ�ǰ�������
    if(document.activeElement!=contextmenu)
       contextmenu.style.display="none"   //���� contextmenu ��
}

function RIGHT_save(){

	rightOpObj.innerHTML+="1111111111111111111111111111<br/>";

}
function RIGHT_�༭(){

	rightOpObj.innerHTML+="1111111111111111111111111111<br/>";

}


</script> 
  </body>
</html>
