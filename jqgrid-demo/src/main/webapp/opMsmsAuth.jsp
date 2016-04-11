<%@ page language="java" pageEncoding="GB18030"%>
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.3.min.js"></script>
	
	
  </head>
  	<style type="text/css">
  	
  	* {
  		padding-top: 5px;
  		padding:2px;
  	}
	
	#table1 {
		margin-top:100px;
		padding:40px;
		 border:1px solid #999; 
		text-align:center;
	}
	
	#table1 tr td {
		margin:140px;
	}
	
	
	#msg {
		height: 0px;
		color: red;
	}
	
	#timeOutTimer {
		width: 35px;
		font-size: 12px;
	}
	
	</style>
  
	<script type="text/javascript">
	
	var defalutTimeOutSecond = 0;//默认验证码失效时间
	var timeOutCounter = 0;
	var timeOutTimer;
	$(function(){
	
		$.ajax({
	 		type:"post",
	 		url:"<%=path%>/core/core_autocreate_checkOpPriv",
	 		success:function(msg){
	 			if(msg=="NONEEDCHECK"){
	 				setMsg("当前操作员不需要做权限认证！");
	 				setTimeout("retrunTrue()",1500);
	 			}else if(msg=="ERROR"){
	 				setMsg("当前操作员没有找到上一级操作员！");
	 				setTimeout("retrunFalse()",1500);
	 			}else{
	 				defalutTimeOutSecond = 180;
	 			}
	 		}
	 	});
	
		$("#getVerificationCode").click(function(){
			setMsg("");
			$("#verificationCode").val("");
			
			$.ajax({
		 		type:"post",
		 		url:"<%=path%>/core/core_autocreate_getVerificationCode",
		 		success:function(msg){
		 			if(msg!="T"){
		 				alert(msg);
		 			}
		 		}
		 	});
			
			if(timeOutTimer != null){
				clearTimeout(timeOutTimer);
			}
			timeOutCounter = defalutTimeOutSecond+1;
			setTimeOutTimer();
			timeOutTimer = setInterval("setTimeOutTimer()",1000);
			
		});
	
		$("#cancel").click(function(){
			window.returnValue="F";
			window.close();
		});
		
		$("#ok").click(function(){
			if(timeOutCounter <= 0 && $("#timeOutTimer").html()==""){
				setMsg("请先获取验证码！");
				return;
			}
			if(timeOutCounter <= 0 && $("#timeOutTimer").html()!=""){
				setMsg("请重新获取验证码！");
				return;
			}
			
			var inputVal = $("#verificationCode").val();
			if(inputVal == "" || inputVal == null){
				setMsg("请输入验证码！");
				return;
			}
			
			$.ajax({
		 		type:"post",
		 		url:"<%=path%>/core/core_autocreate_checkVerificationCode?inputVal="+inputVal,
		 		success:function(msg){
		 			if(msg=="T"){
		 				setMsg("权限认证通过！");
	 					setTimeout("retrunTrue()",1500);
		 			}else{
		 				setMsg(msg);
		 				$("#verificationCode").val("");
		 			}
		 		}
	 		});
		});
	});
	
	
	function setMsg(msg){
		$("#msg").html(msg);
		$("#msg").hide();
		$("#msg").fadeIn("slow");
	}
	
	
	function setTimeOutTimer(){
		timeOutCounter = timeOutCounter- 1;
		if(timeOutCounter >= 0){
			$("#timeOutTimer").html(timeOutCounter + "秒");
		}
	}
	
	function  retrunFalse(){
		window.returnValue="F";
			window.close();
	}
	
	function  retrunTrue(){
		window.returnValue="T";
			window.close();
	}
	
	</script>
  
  <body style='text-align:center;'>
    <table id ='table1' >
	    <tr>
	    	<td >验证码：</td>
	    	<td ><input id='verificationCode' /></td>
	    	<td ><span id='timeOutTimer'></span></td>
	    	<td ><button id= 'getVerificationCode'>获取验证码</button></td>
	    </tr>
	     <tr >
	    	<td colspan='4'><label id = 'msg' style="color: red;"></label></td>
	     </tr>
	      <tr >
	    	<td colspan='4'><button id='cancel'>取消</button>&nbsp;&nbsp;&nbsp;<button id='ok'>确定</button></td>
	     </tr>
    </table>
    
  </body>
</html>
