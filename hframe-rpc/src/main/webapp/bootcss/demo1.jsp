<%--
  Created by IntelliJ IDEA.
  User: zhangqh6
  Date: 2015/11/24
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="title" content="LayoutIt! - Bootstrap可视化布局系统">
  <meta name="description" content="LayoutIt! 可拖放排序在线编辑的Bootstrap可视化布局系统">
  <meta name="keywords" content="可视化,布局,系统">
  <title>Bootstrap可视化布局系统</title>


  <!-- Le styles -->
  <link href="<%=request.getContextPath() %>/bootcss/css/bootstrap-combined.min.css" rel="stylesheet">

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="<%=request.getContextPath() %>/bootcss/js/html5shiv.js"></script>
  <![endif]-->
  <!-- Fav and touch icons -->
  <link rel="shortcut icon" href="<%=request.getContextPath() %>/bootcss/img/favicon.png">

  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/js/jquery-2.0.0.min.js"></script>

  <!--[if lt IE 9]>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <![endif]-->
  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/js/jquery-ui.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/js/jquery.ui.touch-punch.min.js"></script>

  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/js/jquery.htmlClean.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/ckeditor/config.js"></script>

</head>

<body style=""><div class="container-fluid">
  <div class="row-fluid">
    <div class="span12">
      <div class="navbar navbar-inverse">
        <div class="navbar-inner">
          <div class="container-fluid">
            <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">网站名</a>
            <div class="nav-collapse collapse navbar-responsive-collapse">
              <ul class="nav">
                <li class="active">
                  <a href="#">主页</a>
                </li>
                <li>
                  <a href="#">链接</a>
                </li>
                <li>
                  <a href="#">链接</a>
                </li>
                <li class="dropdown">
                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="#">下拉导航1</a>
                    </li>
                    <li>
                      <a href="#">下拉导航2</a>
                    </li>
                    <li>
                      <a href="#">其他</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li class="nav-header">
                      标签
                    </li>
                    <li>
                      <a href="#">链接1</a>
                    </li>
                    <li>
                      <a href="#">链接2</a>
                    </li>
                  </ul>
                </li>
              </ul>
              <ul class="nav pull-right">
                <li>
                  <a href="#">右边链接</a>
                </li>
                <li class="divider-vertical">
                </li>
                <li class="dropdown">
                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="#">下拉导航1</a>
                    </li>
                    <li>
                      <a href="#">下拉导航2</a>
                    </li>
                    <li>
                      <a href="#">其他</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                      <a href="#">链接3</a>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>

          </div>
        </div>

      </div>
    </div>
  </div>
  <div class="row-fluid">
    <div class="span12">
      <div class="carousel slide" id="carousel-236785">
        <ol class="carousel-indicators">
          <li data-slide-to="0" data-target="#carousel-236785">
          </li>
          <li data-slide-to="1" data-target="#carousel-236785" class="active">
          </li>
          <li data-slide-to="2" data-target="#carousel-236785">
          </li>
        </ol>
        <div class="carousel-inner">
          <div class="item">
            <img alt="" src="/frame/bootcss/img/1.jpg" />
            <div class="carousel-caption">
              <h4>
                棒球
              </h4>
              <p>
                棒球运动是一种以棒打球为主要特点，集体性、对抗性很强的球类运动项目，在美国、日本尤为盛行。
              </p>
            </div>
          </div>
          <div class="item active">
            <img alt="" src="/frame/bootcss/img/2.jpg" />
            <div class="carousel-caption">
              <h4>
                冲浪
              </h4>
              <p>
                冲浪是以海浪为动力，利用自身的高超技巧和平衡能力，搏击海浪的一项运动。运动员站立在冲浪板上，或利用腹板、跪板、充气的橡皮垫、划艇、皮艇等驾驭海浪的一项水上运动。
              </p>
            </div>
          </div>
          <div class="item">
            <img alt="" src="/frame/bootcss/img/3.jpg" />
            <div class="carousel-caption">
              <h4>
                自行车
              </h4>
              <p>
                以自行车为工具比赛骑行速度的体育运动。1896年第一届奥林匹克运动会上被列为正式比赛项目。环法赛为最著名的世界自行车锦标赛。
              </p>
            </div>
          </div>
        </div> <a data-slide="prev" href="#carousel-236785" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-236785" class="right carousel-control">›</a>
      </div>
    </div>
  </div>
  <div class="row-fluid">
    <div class="span12">
      <div class="navbar">
        <div class="navbar-inner">
          <div class="container-fluid">
            <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">网站名</a>
            <div class="nav-collapse collapse navbar-responsive-collapse">
              <ul class="nav">
                <li class="active">
                  <a href="#">主页</a>
                </li>
                <li>
                  <a href="#">链接</a>
                </li>
                <li>
                  <a href="#">链接</a>
                </li>
                <li class="dropdown">
                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="#">下拉导航1</a>
                    </li>
                    <li>
                      <a href="#">下拉导航2</a>
                    </li>
                    <li>
                      <a href="#">其他</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li class="nav-header">
                      标签
                    </li>
                    <li>
                      <a href="#">链接1</a>
                    </li>
                    <li>
                      <a href="#">链接2</a>
                    </li>
                  </ul>
                </li>
              </ul>
              <ul class="nav pull-right">
                <li>
                  <a href="#">右边链接</a>
                </li>
                <li class="divider-vertical">
                </li>
                <li class="dropdown">
                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="#">下拉导航1</a>
                    </li>
                    <li>
                      <a href="#">下拉导航2</a>
                    </li>
                    <li>
                      <a href="#">其他</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                      <a href="#">链接3</a>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>

          </div>
        </div>

      </div>
    </div>
  </div>
  <div class="row-fluid">
    <div class="span3">
      <ul class="nav nav-list" hframe-component="tree" hframe-dataset="hfpm_page_tree">
        <li class="nav-header">
          列表标题
        </li>
        <li class="active">
          <a href="#">首页</a>
        </li>
        <li>
          <a href="#">库</a>
        </li>
        <li>
          <a href="#">应用</a>
        </li>
        <li class="nav-header">
          功能列表
        </li>
        <li>
          <a href="#">资料</a>
        </li>
        <li>
          <a href="#">设置</a>
        </li>
        <li class="divider">
        </li>
        <li>
          <a href="#">帮助</a>
        </li>
      </ul>
    </div>
    <div class="span9">
      <table class="table" hframe-component="list" hframe-dataset="hfpm_page_list">
        <thead>
        <tr>
          <th>
            编号
          </th>
          <th>
            产品
          </th>
          <th>
            交付时间
          </th>
          <th>
            状态
          </th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>
            1
          </td>
          <td>
            TB - Monthly
          </td>
          <td>
            01/04/2012
          </td>
          <td>
            Default
          </td>
        </tr>
        <tr class="success">
          <td>
            1
          </td>
          <td>
            TB - Monthly
          </td>
          <td>
            01/04/2012
          </td>
          <td>
            Approved
          </td>
        </tr>
        <tr class="error">
          <td>
            2
          </td>
          <td>
            TB - Monthly
          </td>
          <td>
            02/04/2012
          </td>
          <td>
            Declined
          </td>
        </tr>
        <tr class="warning">
          <td>
            3
          </td>
          <td>
            TB - Monthly
          </td>
          <td>
            03/04/2012
          </td>
          <td>
            Pending
          </td>
        </tr>
        <tr class="info">
          <td>
            4
          </td>
          <td>
            TB - Monthly
          </td>
          <td>
            04/04/2012
          </td>
          <td>
            Call in to confirm
          </td>
        </tr>
        </tbody>
      </table>
      <div class="row-fluid">
        <div class="span4">
          <img alt="140x140" src="/frame/bootcss/img/a.jpg" class="img-polaroid" />
        </div>
        <div class="span4">
          <img alt="140x140" src="/frame/bootcss/img/a.jpg" class="img-rounded" />
        </div>
        <div class="span4">
          <img alt="140x140" src="/frame/bootcss/img/a.jpg" class="img-rounded" />
        </div>
      </div>
    </div>
  </div>
  <div class="row-fluid">
    <div class="span12">
    </div>
  </div>
</div>
</body>
</html>
