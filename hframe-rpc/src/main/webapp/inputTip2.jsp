<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
      <title>表单验证</title>
      <script type="text/javascript">
      function setDiv(sender){
	  //var info=document.createElement('div');
	  //info.innerHTML="Error";
	  //document.body时，可以在最后创建一个Div
	  //document.uname时，无法创建
	  //document.body.appendChild(info);
	  //document.uname.appendChild(info);
	      var uname_err_div = document.getElementById('uname_err');
	      if (sender.value.length < 6){
              uname_err_div.style.display = 'block';
	      } else {
              uname_err_div.style.display = 'none';
		  }
	      
	  }
      </script>
   </head>
   <body >
      <div style="clear:both;">
	     <div style="float:left;">用户名:<input type="text" name="uname" id="uname" onchange="setDiv(this);" /></div>
		 <div id="uname_err" style="display:none;">Error</div>
	  </div>
	  <div style="clear:both;">
	     <div style="float:left;">密 码:<input type=”pwd" name="upwd" id="upwd" /></div> 
		 <div id="upwd_err" style="display:none;">Error</div>
	  </div>
	  <div style="clear:both;">
	      <input type="submit" value="注册" /><input type="reset" value="取消" />
	  </div>
   </body>
</html>
