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
	
	function showBackWin(){
		window.showModelessDialog("backstage/coreindex.jsp",null,
					"scroll:yes;help:no;resizable:yes;status:yes;dialogHeight:700px;dialogWidth:900px");
	}
	</script>
	  <style type="text/css">
  

@media (min-width: 768px) {
  .sidebar {
    bottom: 0;
    left: 0;
    z-index: 1000;
    display: inline;
    overflow-x: hidden;
    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
  }
}

/* Sidebar navigation */
.nav-sidebar {
  margin-right: -21px; /* 20px padding + 1px border */
  margin-bottom: 20px;
  margin-left: -20px;
}
.nav-sidebar > li > a {
  padding-right: 20px;
  padding-left: 20px;
}
.nav-sidebar > .active > a,
.nav-sidebar > .active > a:hover,
.nav-sidebar > .active > a:focus {
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

	  <script type="text/javascript">
		  $(function(){
			  $("#create_page_default_btn").click(function(){
				  $.ajax({
					  type:"post",
					  url:getSYSTEMPATH()+"/hframe//create_page_default.html",
					  success:function(msg){
						  alert(msg);
					  }
				  });
			  });
		  });
	  </script>

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
		  <a href="#" class="btn btn-link" role="button">添加项目</a>  | <a href="javascript:void(0);" class="btn btn-link" role="button">管理</a>  
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
	  <li role="presentation" class="active"><a href="plant/index_po.jsp" id="menu_vo" target="iframe">数据建模</a></li>
	  <li role="presentation"><a href="plant/function_config.jsp" id="menu_fun" target="iframe">功能定制</a></li>
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
		<div class="col-sm-3 col-md-3 col-md-offset-0 sidebar" style="">
		    <div class="list-group">
				<a href="#profile2"  aria-controls="profile2" role="tab" data-toggle="tab" class="list-group-item active">
					    新增实体
				</a>
			  	<a href="#messages3" aria-controls="messages3" role="tab" data-toggle="tab" class="list-group-item">管理实体</a>
			  	<a href="#settings4" aria-controls="settings4" role="tab" data-toggle="tab" class="list-group-item">可视化编辑</a>
				<a id="create_page_default_btn" href="javascript:void(0);" aria-controls="" role="" data-toggle="" class="list-group-item">默认页面生成</a>
			</div>
		</div>
		<div class="col-md-8  main" style="">
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane fade in  active" id="home1">
		    		<iframe id="n1ull" src="design/backstage/coredblist.jsp?_FRAMEID=null"
						frameborder="0" style="height: 900px; width: 1000px;" height="143"
						width="160"></iframe></div>
		    <div role="tabpanel" class="tab-pane fade" id="profile2">
		    	<iframe id="nul22l"
						src="design/backstage/coretablelist.jsp?_FRAMEID=null"
						frameborder="0" style="height: 550px; width: 800px;"></iframe>
		    </div>
		    <div role="tabpanel" class="tab-pane fade" id="messages3"><iframe id="n1u1ll"
						src="design/backstage/coretablecolumnlist.jsp?_FRAMEID=null"
						frameborder="0" style="height: 550px; width: 800px;"></iframe></div>
		    <div role="tabpanel" class="tab-pane fade" id="settings4"><iframe id="nu1ll"
						src="design/backstage/coreshowtypelist.jsp?_FRAMEID=null"
						frameborder="0" style="height: 550px; width: 800px;"></iframe></div>
		  </div>
		<div>
	</div>	
</div>		
<zqh:model id="fdsfdsafdsa">
			<zqh:myform formid="aaaafdsfdsa" view="core_db" colNum="4" defaultBtn="save">
			</zqh:myform>
		</zqh:model>
  <!-- Nav tabs -->
  <!--  
</div>
  	<div id="container">
  		<div  >
  			<iframe src="index_index.jsp" width="1000px" height="600px" name="iframe" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
  		</div>
  	</div>
  -->  
  </body>
</html>
