<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    
    <title>智能系统-设计首页</title>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-csontrol" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=path %>/design/css/index.css">
	<!-- 引入bootstrap -->
	<script type="text/javascript">
	
	var curTab = null;
	 $(function(){
	      $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
	      
	      // 获取已激活的标签页的名称
	      var activeTab = $(e.target).attr("href"); 
	      if(curTab == null || activeTab == curTab) {
	      	var contentPanel = $(e.target).parent().parent().next().attr("id");
	      	$('#' + contentPanel).collapse("toggle");
	      //	$('#rightToolbarContDiv').collapse("toggle");
	      }else {
	      	$('#' + contentPanel).collapse("show");
	      }
	      curTab = activeTab;
	      // 获取前一个激活的标签页的名称
	      //var previousTab = $(e.relatedTarget).html(); 
	      //alert("当前标签：" + activeTab + ";前一个标签:" + previousTab +";");
	      

	   });
	});
	
	function showBackWin(){
		window.showModelessDialog("backstage/coreindex.jsp",null,
					"scroll:yes;help:no;resizable:yes;status:yes;dialogHeight:700px;dialogWidth:900px");
	}
	
	
	</script>
	  <style type="text/css">
  

@media (min-width: 768px) {
  .leftToolBarTitle {
  	width:30px;
  	position: fixed;
  	top:51px;
    bottom: 0;
    left: 0;
    z-index: 1000;
    display: inline;
    overflow-x: hidden;
    overflow-y: auto;/* Scrollable contents if viewport is shorter than content. */
    padding-left: 0px; 
  }
  .lefttoolbarCont {
  	width:200px;
  	position: fixed;
  	top:52px;
    bottom: 0;
    left: 30px;
    z-index: 900;
    display: inline;
    overflow-x: hidden;
    overflow-y: auto;/* Scrollable contents if viewport is shorter than content. */
    padding-left: 0px; 
  }
  
  
  .rightToolBarTitle {
  	width:30px;
  	position: fixed;
  	top:51px;
    bottom: 0;
    right: 0;
    z-index: 1000;
    display: inline;
    overflow-x: hidden;
    overflow-y: auto;/* Scrollable contents if viewport is shorter than content. */
    padding-right: 0px; 
  }
  
  .rightToolBarCont {
  	width:200px;
  	position: fixed;
  	top:52px;
    bottom: 0;
    right: 30px;
    z-index: 900;
    display: inline;
    overflow-x: hidden;
    overflow-y: auto;/* Scrollable contents if viewport is shorter than content. */
    padding-right: 0px; 
  }
}

/* Sidebar navigation */
.nav-fixsidebar {
  margin-right: -8px; /* 20px padding + 1px border */
  margin-bottom: 20px;
  margin-left: -8px;
}
.nav-fixsidebar > li > a {
  padding-right: 0px;
  padding-left: 0px;
}
.nav-fixsidebar > .active > a,
.nav-fixsidebar > .active > a:hover,
.nav-fixsidebar > .active > a:focus {
  color: #fff;
  background-color: #428bca;
}


/*
 * Main content
 */

.main {
  padding: 0px;
  display: inline;
  float:left;
}
@media (min-width: 768px) {
  .main {
    padding-right: 0px;
    padding-left: 0px;
  }
}
.main .page-header {
  margin-top: 0;
}


/*
 * Placeholder dashboard ideas
 */

.placeholders {
  margin-bottom: 30px;
  text-align: center;
}
.placeholders h4 {
  margin-bottom: 0;
}
.placeholder {
  margin-bottom: 20px;
}
.placeholder img {
  display: inline-block;
  border-radius: 50%;
}
</style>
  </head>
  <body  style="margin: 0px;">
  <%@ include file="/webframe/model.jsp" %>
  <div class="collapse in" id="collapseExample">
<nav class="navbar navbar-default  navbar-fixed-top ">
  <div class="container">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
      	<li><a href="javascript:void(0);">欢迎来到鸿鹄中心</a></li>
      </ul>
      <form class="navbar-form navbar-left" >
       <div class="form-group form-inline">
     	 		<p class="form-control-static">游客，上午好 | 请<a href="#">[登陆]</a>还没有账号？我要<a href="#">[注册]</a></p>
        </div>
        </form>
      <form class="navbar-form navbar-right" >
        <div class="form-group">
         项目列表：
          <select name='BirthDate' class="form-control" style=''>
			<option  value=1987>学习中国</option>
		  </select><span style="width:30px;">&nbsp;</span>
		  <a href="#" class="btn btn-link" role="button" id="test">添加项目</a>  | <a href="javascript:void(0);" class="btn btn-link" role="button">管理</a>  
        </div>
      </form>
    </div>
  </div>
</nav>
  <div class="jumbotron" style="background: url('plant/images/mofang.png') center left no-repeat;"><span style="width:100px;">&nbsp;</span>
  <h1 style="padding-top: 30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HFrame设计在线</h1>
</div>
</div>
<nav class="navbar navbar-default">
  <div class="container">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
	<ul class="nav  navbar-nav" id="myTabs">
	  <li role="presentation"><a href="plant/index_po.jsp" id="menu_vo" target="iframe">数据建模</a></li>
	  <li role="presentation"  class="active"><a href="plant/index_page.jsp" id="menu_fun" target="iframe">功能定制</a></li>
	  <li role="presentation"><a href="plant/index_page.jsp" id="menu_page" target="iframe">页面层设计</a></li>
	  <li role="presentation"><a href="plant/index_autocreate.jsp" id="menu_store" target="iframe">代码管理</a></li>
	  <li role="presentation"><a href="plant/backstage/coreindex.jsp" id="menu_store" target="iframe">总页面</a></li>
	  <li role="presentation"><a href="javascript:showBackWin();">(弹出)</a></li>
	</ul>
	
	 <form class="navbar-form navbar-right" >
        <div class="form-group">
		  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
  			视图切换
		  </button>
        </div>
      </form>
	</div>
  </div>
</nav>
<div class="container-fluid">
	<div class="row">
		
			
		<div class="leftToolBarTitle" style="">
		    <div class="list-group nav-fixsidebar">
				<a href="#functionEditPanel"  aria-controls="functionEditPanel" role="tab" data-toggle="tab" class="list-group-item active">功能列表</a>
			  	<a href="#otherEditPanel" aria-controls="otherEditPanel" role="tab" data-toggle="tab" class="list-group-item">其他</a>
			</div>
		</div>
		<div class="collapse"  id="leftToolbarContDiv" style="">
		    	<div class="panel panel-default panel-primary leftToolbarCont">
				  <div class="panel-body">
				    <div class="tab-content">
					    <div role="tabpanel" class="tab-pane  in  active" id="functionEditPanel">
					    	 Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>		
						</div>
					    <div role="tabpanel" class="tab-pane " id="otherEditPanel">
		 					Panel conten3t<br/>
						    Panel conten3t<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>	
						  </div>
					  </div>
					<div>
				  </div>
				</div>
			</div>
	</div>	
		
		
		
		<div class="rightToolBarTitle" style="">
		    <div class="list-group nav-fixsidebar">
				<a href="#entityEditPanel"  aria-controls="entityEditPanel" role="tab" data-toggle="tab" class="list-group-item active">页面实体</a>
			  	<a href="#componentEditPanel" aria-controls="componentEditPanel" role="tab" data-toggle="tab" class="list-group-item">页面组件</a>
			  	<a href="#eventEditPanel" aria-controls="eventEditPanel" role="tab" data-toggle="tab" class="list-group-item">页面事件</a>
			</div>
		</div>
		<div class="collapse"  id="rightToolbarContDiv" style="">
		    	<div class="panel panel-default panel-primary rightToolbarCont">
				  <div class="panel-body">
				    <div class="tab-content">
					    <div role="tabpanel" class="tab-pane  in  active" id="entityEditPanel">
					    	 Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>		
						</div>
					    <div role="tabpanel" class="tab-pane " id="componentEditPanel">
					    	 Panel content2<br/>
						    Panel content2<br/>
						    Panel conten2t<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>		
					    </div>
					    <div role="tabpanel" class="tab-pane " id="eventEditPanel">
		 					Panel conten3t<br/>
						    Panel conten3t<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>
						    Panel content<br/>	
						  </div>
					  </div>
					<div>
				  </div>
				</div>
			</div>
	</div>	
		
</div>		
<zqh:model id="fdsfdsafdsa">
			<zqh:myform formid="aaaafdsfdsa" view="core_db" colNum="4" defaultBtn="save">
			</zqh:myform>
		</zqh:model>
		
		
<ul id="myTab" class="nav nav-tabs">
   <li class="active"><a href="#home" data-toggle="tab">
      W3Cschool Home</a></li>
   <li><a href="#ios" data-toggle="tab">iOS</a></li>
   <li class="dropdown">
      <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">
         Java <b class="caret"></b></a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
         <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
         <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
      </ul>
   </li>
</ul>
<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade in active" id="home">
   </div>
   <div class="tab-pane fade" id="ios">
   </div>
   <div class="tab-pane fade" id="jmeter">
   </div>
   <div class="tab-pane fade" id="ejb">
   </div>
</div>
  </body>
</html>
