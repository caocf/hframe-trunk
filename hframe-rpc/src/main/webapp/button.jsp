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
    
    <title>My JSP 'form.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.6.3.min.js"></script>
  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <!-- ����bootstrap -->
	  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	  <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	  <script src="bootstrap/js/bootstrap.min.js"></script>
  </head>
  
  <body>

	
<h1>diystyle demo</h1>
	<div>����������룺
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title="�޸�"
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'openwin-update','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">�޸�</button>
	
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'openwin-create','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">����</button>
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'action-delete','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">ɾ��</button>
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'action-save','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">����</button>
	</div>
<h1>bootstrap demo</h1>
<!-- Standard button -->
<button type="button" class="btn btn-default">��Ĭ����ʽ��Default</button>

<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
<button type="button" class="btn btn-primary">����ѡ�Primary</button>

<!-- Indicates a successful or positive action -->
<button type="button" class="btn btn-success">���ɹ���Success</button>

<!-- Contextual button for informational alert messages -->
<button type="button" class="btn btn-info">��һ����Ϣ��Info</button>

<!-- Indicates caution should be taken with this action -->
<button type="button" class="btn btn-warning">�����棩Warning</button>

<!-- Indicates a dangerous or potentially negative action -->
<button type="button" class="btn btn-danger">��Σ�գ�Danger</button>

<!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
<button type="button" class="btn btn-link">�����ӣ�Link</button>

<p>
  <button type="button" class="btn btn-primary btn-lg">����ť��Large button</button>
  <button type="button" class="btn btn-default btn-lg">����ť��Large button</button>
</p>
<p>
  <button type="button" class="btn btn-primary">��Ĭ�ϳߴ磩Default button</button>
  <button type="button" class="btn btn-default">��Ĭ�ϳߴ磩Default button</button>
</p>
<p>
  <button type="button" class="btn btn-primary btn-sm">��С��ť��Small button</button>
  <button type="button" class="btn btn-default btn-sm">��С��ť��Small button</button>
</p>
<p>
  <button type="button" class="btn btn-primary btn-xs">����С�ߴ磩Extra small button</button>
  <button type="button" class="btn btn-default btn-xs">����С�ߴ磩Extra small button</button>
</p>

<h1>jsptag demo</h1>
<zqh:button src="" model="openwin-update" buttonId="111" icon="" buttonName="111" title="�޸�"></zqh:button>
<zqh:button src="" model="openwin-create" buttonId="111" icon="" buttonName="111" title="����"></zqh:button>
<zqh:button src="" model="action-delete" buttonId="111" icon="" buttonName="111" title="ɾ��"></zqh:button>
<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="����"></zqh:button>

<button type="button" id="111" class="btn btn-default" onclick="btnClk_Sys(this,'openwin-update','test/createDialog.jsp',0,'{param=null&amp;isAjax=null&amp;priv=null&amp;targetObj=&amp;targetType=null}')">�޸�</button>
  </body>
</html>
