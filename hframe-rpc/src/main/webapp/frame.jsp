<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/webframe/commonhead.jsp"%>
<%@ include file="/webframe/mycommonhead.jsp"%>
<%@page import="com.hframework.common.ssh.service.CommonServ"%>




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
		<!-- 引入bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<style type="text/css">
    .dropdown-submenu {
      position: relative;
    }
    .dropdown-submenu > .dropdown-menu {
      top: 0;
      left: 100%;
      margin-top: -6px;
      margin-left: -1px;
      -webkit-border-radius: 0 6px 6px 6px;
      -moz-border-radius: 0 6px 6px;
      border-radius: 0 6px 6px 6px;
    }
    .dropdown-submenu:hover > .dropdown-menu {
      display: block;
    }
    .dropdown-submenu > a:after {
      display: block;
      content: " ";
      float: right;
      width: 0;
      height: 0;
      border-color: transparent;
      border-style: solid;
      border-width: 5px 0 5px 5px;
      border-left-color: #ccc;
      margin-top: 5px;
      margin-right: -10px;
    }
    .dropdown-submenu:hover > a:after {
      border-left-color: #fff;
    }
    .dropdown-submenu.pull-left {
      float: none;
    }
    .dropdown-submenu.pull-left > .dropdown-menu {
      left: -100%;
      margin-left: 10px;
      -webkit-border-radius: 6px 0 6px 6px;
      -moz-border-radius: 6px 0 6px 6px;
      border-radius: 6px 0 6px 6px;
    }
  </style>
		<style type="text/css">
<!--
* {
	margin: 0;
	padding: 0;
	border: 0;
}

body {
	font: 12px/ 130% verdana, geneva, arial, helvetica, sans-serif, 宋体;
}

li {
	list-style: none;
}

.clearfix:after {
	content: " ";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.clearfix {
	display: inline-block;
}

a:link {
	color: #000;
	text-decoration: none;
}

a:visited {
	color: #000;
	text-decoration: none;
}

a:hover {
	color: #000;
	text-decoration: none;
}

.menu {
	width: 778px;
	height: 26px;
	background: #eee;
	margin: 0 auto;
}

.menusel {
	float: left;
	width: 100px;
	position: relative;
	height: 25px;
	background: #ddd;
	line-height: 25px;
	margin-left: 1px; *
	margin-left: 0px;
	_margin-left: -1px;
}

.menusel h2 {
	font-size: 12px;
}

.menusel a {
	display: block;
	text-align: center;
	width: 100px;
	border: 1px solid #a4a4a4;
	height: 25px;
	border-bottom: 1px solid #a4a4a4;
	position: relative;
	z-index: 2;
}

.menusel a:hover {
	border: 1px solid #a4a4a4;
	border-bottom: 1px dashed #eeeeee;
	position: relative;
	z-index: 2;
	height: 25px;
}

.ahover a {
	border-bottom: 1px dashed #eeeeee;
	background: #eeeeee;
}

.position {
	position: absolute;
	z-index: 1;
}

.menusel ul {
	width: 125px;
	background: #eee;
	border: 1px solid #a4a4a4;
	margin-top: -1px;
	position: relative;
	z-index: 1;
	display: none;
}

.menusel .block {
	display: block;
}

.typeul li {
	border-bottom: 1px dashed #a4a4a4;
	width: 125px;
	position: relative;
	float: left;
}

.typeul li a {
	border: none;
	width: 125px;
}

.typeul li a:hover {
	border: none;
	background: #ddd;
}

.typeul {
	margin-left: 0;
}

.typeul ul {
	left: 125px;
	top: 0;
	position: absolute;
}

.fli {
	margin-left: -1px;
	border-left: #eeeeee solid 1px;
}

.menusel .lli {
	border: none;
}
-->
</style>

	</head>

	<body>

		<div id="menusID" class="menu">
			<div id="menusID_menu1" class="menusel">
				<h2>
					<a href="index.jsp">AAAA</a>
				</h2>
				<div class="position">
					<ul class="typeul">
						<li class="fli">
							<a href="index.jsp">BBB</a>
						</li>
						<li class="lli">
							<a href="index.jsp">BBB-&gt;</a>
							<ul class="">
								<li class="fli">
									<a href="index.jsp">CCC</a>
								</li>
								<li class="">
									<a href="index.jsp">CCCC</a>
								</li>
								<li class="lli">
									<a href="index.jsp">CCCC-&gt;</a>
									<ul>
										<li class="fli">
											<a href="index.jsp">我是菜单选项2-2-1</a>
										</li>
										<li class="">
											<a href="index.jsp">我是菜单选项2-2-2</a>
										</li>
									</ul>
								</li>
								<li class="">
									<a href="index.jsp">我是菜单选项1-2</a>
								</li>
								<li class="">
									<a href="index.jsp">我是菜单选项1-3</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div id="menusID_menu2" class="menusel">
				<h2>
					<a href="index.jsp">我是菜单2</a>
				</h2>
				<div class="position">
					<ul class="typeul">
						<li class="lli">
							<a href="index.jsp">我是菜单2-1</a>
							<ul class="">
								<li class="fli">
									<a href="index.jsp">我是菜单2-2-1</a>
								</li>
								<li class="">
									<a href="index.jsp">我是菜单2-2-2</a>
								</li>
								<li class="lli">
									<a href="index.jsp">我是菜单2-2-3</a>
									<ul>
										<li class="fli">
											<a href="index.jsp">我是菜单2-2-3-1</a>
										</li>
										<li class="">
											<a href="index.jsp">我是菜单2-2-3-2</a>
										</li>
									</ul>
								</li>
							</ul>
						</li>
						<li class="">
							<a href="index.jsp">我是菜单2-2</a>
						</li>
					</ul>
				</div>
			</div>
			<div id="menusID_menu3" class="menusel">
				<h2>
					<a href="index.jsp">我是菜单3</a>
				</h2>
				<div class="position">
					<ul class="typeul">
						<li class="fli">
							<a href="index.jsp">我是菜单3-1</a>
						</li>
						<li class="">
							<a href="index.jsp">我是菜单3-2</a>
						</li>
						<li class="lli">
							<a href="index.jsp">我是菜单3-3</a>
							<ul>
								<li class="fli">
									<a href="index.jsp">我是菜单3-3-1</a>
								</li>
								<li class="">
									<a href="index.jsp">我是菜单3-3-2</a>
								</li>
								<li class="">
									<a href="index.jsp">我是菜单3-3-3</a>
								</li>
								<li class="lli">
									<a href="index.jsp">我是菜单3-3-4</a>
									<ul>
										<li class="fli">
											<a href="index.jsp">我是菜单3-3-4-1</a>
										</li>
										<li class="">
											<a href="index.jsp">我是菜单3-3-4-2</a>
										</li>
										<li class="lli">
											<a href="index.jsp">我是菜单3-3-4-3</a>
											<ul>
												<li class="fli">
													<a href="index.jsp">我是菜单3-3-4-3-1</a>
												</li>
												<li class="lli">
													<a href="index.jsp">我是菜单3-3-4-3-2</a>
													<ul>
														<li class="fli">
															<a href="index.jsp">我是菜单3-3-4-3-2-1</a>
														</li>
													</ul>
												</li>
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div id="menusID_menu4" class="menusel">
				<h2>
					<a href="index.jsp">xxxx</a>
				</h2>
			</div>
			<script type="text/javascript">injectMenuEvent("menusID");</script>
		</div>


		<script type="text/javascript">
for(var x = 1; x < 4; x++)
{
var menuid = document.getElementById("menu"+x);
menuid.num = x;
type();
}
function type()
{
var menuh2 = menuid.getElementsByTagName("h2");
var menuul = menuid.getElementsByTagName("ul");
var menuli = menuul[0].getElementsByTagName("li");
menuh2[0].onmouseover = show;
menuh2[0].onmouseout = unshow;
menuul[0].onmouseover = show;
menuul[0].onmouseout = unshow;
function show()
{
menuul[0].className = "clearfix typeul block"
}
function unshow()
{
menuul[0].className = "typeul"
}
for(var i = 0; i < menuli.length; i++)
 {
 menuli[i].num = i;
 var liul = menuli[i].getElementsByTagName("ul")[0];
  if(liul)
  {
  typeshow()
  }
 }
function typeshow()
{
menuli[i].onmouseover = showul;
menuli[i].onmouseout = unshowul;
}
function showul()
{
menuli[this.num].getElementsByTagName("ul")[0].className = "block";
}
function unshowul()
{
menuli[this.num].getElementsByTagName("ul")[0].className = "";
}
}
</script>

		<div style="height: 400px;">
			12
		</div>
		<nav class="navbar navbar-default" style="width:800px">
		<div class="container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-3">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">我是菜单-1
							<span class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li>
								<a href="#">我是菜单-1-1</a>
							</li>
							<li>
								<a href="#">我是菜单-1-2</a>
							</li>
							<li class="dropdown-submenu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"
									role="button" aria-haspopup="true" aria-expanded="false">我是菜单-1-3
									 </a>
								<ul class="dropdown-menu">
									<li>
										<a href="#">我是菜单-1-3-1</a>
									</li>
									<li>
										<a href="#">我是菜单-1-3-2</a>
									</li>
									<li>
										<a href="#">我是菜单-1-3-3</a>
									</li>
									<li role="separator" class="divider"></li>
									<li>
										<a href="#">我是菜单-1-3-4</a>
									</li>
									<li role="separator" class="divider"></li>
									<li>
										<a href="#">我是菜单-1-3-5</a>
									</li>
								</ul>
							</li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="#">Separated link</a>
							</li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="#">One more separated link</a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">Dropdown
							<span class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li>
								<a href="#">Action</a>
							</li>
							<li>
								<a href="#">Another action</a>
							</li>
							<li>
								<a href="#">Something else here</a>
							</li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="#">Separated link</a>
							</li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="#">One more separated link</a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">Dropdown
							<span class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li>
								<a href="#">Action</a>
							</li>
							<li>
								<a href="#">Another action</a>
							</li>
							<li>
								<a href="#">Something else here</a>
							</li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="#">Separated link</a>
							</li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="#">One more separated link</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		</nav>
		<hr>
		<zqh:frame height="" style="" width="" frameId="myframe2"
			src="framecontent2.jsp"></zqh:frame>
		<zqh:frame height="" style="" width="" frameId="myframe"
			src="framecontent.jsp"></zqh:frame>
		<zqh:frame height="" style="" width="" frameId="myframe3"
			src="framecontent2.jsp"></zqh:frame>
		<zqh:frame height="" style="" width="" frameId="myframe4"
			src="framecontent2.jsp"></zqh:frame>
		<zqh:frame height="" style="" width="" frameId="myframe5"
			src="framecontent2.jsp"></zqh:frame>



	</body>
</html>
