<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframe.demo.vo.MenuVO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@ include file="/webframe/commonhead.jsp" %>
    
    <title>My JSP 'tree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="third/dtree/dtree.css">
<%@ include file="/webframe/mycommonhead.jsp" %>

	<script type="text/javascript" src="third/dtree/dtree.js"></script>
  </head>
  
  <body>
    This is my JSP page. <br>
    <script type="text/javascript">
      function getChecked(){
      	alert(munuTreedf.getCheckedNodes());
      }
 </script>
 	
 	 <div id="munuTreedf"  class="dhtmlxTree_zqh" style="width:260px; height:218px; background-color:#f5f5f5;border :1px solid Silver;; overflow:auto;text-align:left;"> </div>
 	 
 	 <script type="text/javascript">
     var munuTreedf=new dhtmlXTreeObject("munuTreedf","100%","100%",0);
     munuTreedf.setImagePath("third/dhtmlxTree/imgs/");
     munuTreedf.enableCheckBoxes(1);
     munuTreedf.enableDragAndDrop(1);
     munuTreedf.loadXMLString("<tree id='0'><item text='��֯�ṹ'>   <item text='�Ĵ�ʡ' id='11' open='1' select='0'>   <item text='�ɶ���' id='111' open='1' select='0'></item>   <item text='�ϳ���' id='112' open='1' select='0'></item></item>   <item text='�㶫ʡ' id='22' open='1' select='0'>   <item text='������' id='221' open='1' select='0'></item>   <item text='��ɽ��' id='222' open='1' select='0'>   <item text='�Ϻ���' id='620396744712803' open='1' select='0'></item></item></item></item></tree>");
</script>

<script type="text/javascript">
      var dtree = new dTree('dtree');
      dtree.add(0,-1,"T16��");
      dtree.add(1,0,"��ʦ",'index.jsp');
      dtree.add(2,1,"������",'index.jsp');
      dtree.add(3,1,"��Ա");
      dtree.add(4,0,'��ɲ�','index.jsp');
      dtree.add(5,4,'�೤','index.jsp');
      dtree.add(6,4,'ѧί','index.jsp');
      dtree.add(7,0,'ѧԱ','index.jsp');
      dtree.add(8,7,'ѧԱ1','index.jsp');
      dtree.add(9,7,'ѧԱ2','index.jsp');
      dtree.add(10,7,'ѧԱ3','index.jsp');
      dtree.add(11,7,'ѧԱ4','index.jsp');
      dtree.add(12,7,'ѧԱ5','index.jsp');
      document.write(dtree);
 </script>
  </body>
</html>
