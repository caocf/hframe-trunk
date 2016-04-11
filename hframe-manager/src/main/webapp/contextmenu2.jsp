<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
<!-- ÒýÈëbootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="bootstrap/js/bootstrap-contextmenu.js"></script>
   
  </head>
  <body  data-toggle="context" data-target="#context-menu" >
  
  <div id="context" data-toggle="context" data-target="#context-menu" style="height:300px;width:650px;border:1px solid #ddd">
	      </div>
  	<div id="context-menu" style="position: absolute; z-index: 9999; top: 236px; left: 385px;" class="">
	      	<ul class="dropdown-menu" role="menu">
            <li><a tabindex="-1">Action</a></li>
	           <li><a tabindex="-1">Another action</a></li>
	           <li><a tabindex="-1">Something else here</a></li>
	           <li class="divider"></li>
	           <li><a tabindex="-1">Separated link</a></li>
	      	</ul>
	      </div>

  <div id="main" data-toggle="context">This is an area where the context menu is active <span style="background-color: #cecece">However, we wont allow it here.</span> But anywhere else in this text should be perfectly fine. This one is started with only javascript</div>
 
  <div id="context2" data-toggle="context"  style="height:300px;width:650px;border:1px solid #ddd">
        </div>

     <div id="context-menu2">
        <ul class="dropdown-menu" role="menu">
            <li><a tabindex="-1">Action</a></li>
            <li><a tabindex="-1">Another action</a></li>
            <li><a tabindex="-1">Something else here</a></li>
            <li class="divider"></li>
            <li><a tabindex="-1">Separated link</a></li>
        </ul>
      </div>
      
 <script type="text/javascript">
      // Demo 2
      $('#main').contextmenu({
        target: '#context-menu2',
        before: function (e) {
          // This function is optional.
          // Here we use it to stop the event if the user clicks a span
          e.preventDefault();
          if (e.target.tagName == 'SPAN') {
            e.preventDefault();
            this.closemenu();
            return false;
          }
          this.getMenu().find("li").eq(2).find('a').html("<red>This was dynamically changed</red>");
          return true;
        }
      });
    </script>
      
 <script type="text/javascript">
      // Demo 3
      $('#context2').contextmenu({
        target: '#context-menu2',
        onItem: function (context, e) {
          alert($(e.target).text());
        }
      });

      $('#context-menu2').on('show.bs.context', function (e) {
        console.log('before show event');
      });

      $('#context-menu2').on('shown.bs.context', function (e) {
        console.log('after show event');
      });

      $('#context-menu2').on('hide.bs.context', function (e) {
        console.log('before hide event');
      });

      $('#context-menu2').on('hidden.bs.context', function (e) {
        console.log('after hide event');
      });
    </script>
  </body>
</html>
