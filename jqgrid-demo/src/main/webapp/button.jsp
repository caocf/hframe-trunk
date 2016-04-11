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
	  <!-- 引入bootstrap -->
	  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	  <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	  <script src="bootstrap/js/bootstrap.min.js"></script>
  </head>
  
  <body>

	
<h1>diystyle demo</h1>
	<div>最终组件代码：
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title="修改"
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'openwin-update','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">修改</button>
	
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'openwin-create','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">创建</button>
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'action-delete','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">删除</button>
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'action-save','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">保存</button>
	</div>
<h1>bootstrap demo</h1>
<!-- Standard button -->
<button type="button" class="btn btn-default">（默认样式）Default</button>

<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
<button type="button" class="btn btn-primary">（首选项）Primary</button>

<!-- Indicates a successful or positive action -->
<button type="button" class="btn btn-success">（成功）Success</button>

<!-- Contextual button for informational alert messages -->
<button type="button" class="btn btn-info">（一般信息）Info</button>

<!-- Indicates caution should be taken with this action -->
<button type="button" class="btn btn-warning">（警告）Warning</button>

<!-- Indicates a dangerous or potentially negative action -->
<button type="button" class="btn btn-danger">（危险）Danger</button>

<!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
<button type="button" class="btn btn-link">（链接）Link</button>

<p>
  <button type="button" class="btn btn-primary btn-lg">（大按钮）Large button</button>
  <button type="button" class="btn btn-default btn-lg">（大按钮）Large button</button>
</p>
<p>
  <button type="button" class="btn btn-primary">（默认尺寸）Default button</button>
  <button type="button" class="btn btn-default">（默认尺寸）Default button</button>
</p>
<p>
  <button type="button" class="btn btn-primary btn-sm">（小按钮）Small button</button>
  <button type="button" class="btn btn-default btn-sm">（小按钮）Small button</button>
</p>
<p>
  <button type="button" class="btn btn-primary btn-xs">（超小尺寸）Extra small button</button>
  <button type="button" class="btn btn-default btn-xs">（超小尺寸）Extra small button</button>
</p>

<h1>jsptag demo</h1>
<zqh:button src="" model="openwin-update" buttonId="111" icon="" buttonName="111" title="修改"></zqh:button>
<zqh:button src="" model="openwin-create" buttonId="111" icon="" buttonName="111" title="创建"></zqh:button>
<zqh:button src="" model="action-delete" buttonId="111" icon="" buttonName="111" title="删除"></zqh:button>
<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="保存"></zqh:button>

<button type="button" id="111" class="btn btn-default" onclick="btnClk_Sys(this,'openwin-update','test/createDialog.jsp',0,'{param=null&amp;isAjax=null&amp;priv=null&amp;targetObj=&amp;targetType=null}')">修改</button>
  </body>
</html>
