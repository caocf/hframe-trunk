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
  <title>在线模板编辑</title>


  <!-- Le styles -->
  <link href="<%=request.getContextPath() %>/bootcss/css/bootstrap-combined.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/bootcss/css/layoutit.css" rel="stylesheet">

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
  <script type="text/javascript" src="<%=request.getContextPath() %>/bootcss/js/scripts.js"></script>

  <style type="text/css">
    body {
      margin-left:300px;
    }
    .devpreview .sidebar-nav {
      left:-300px;
    }
    .sidebar-nav {
      width:300px;
    }

    .edit .demo {
      margin-left:0px;
      margin-top:0px;
      padding:0px 0px 0px;
      border: 1px solid #DDDDDD;
      border-radius: 4px;
    }
    .edit .demo:after {
      content: "";
    }
  </style>

</head>

<body style="min-height: 660px; cursor: auto;" class="edit">
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container-fluid">
      <button data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar" type="button"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="brand" href="javascript:void(0);"><img src="<%=request.getContextPath() %>/bootcss/img/favicon.png"> 在线模板编辑</a>
      <div class="nav-collapse collapse">
        <ul class="nav" id="menu-layoutit">
          <li class="divider-vertical"></li>
          <li>
            <div class="btn-group" data-toggle="buttons-radio">
              <button type="button" id="edit" class="btn btn-primary active"><i class="icon-edit icon-white"></i>文件列表</button>
              <button type="button" class="btn btn-primary" id="devpreview"><i class="icon-eye-close icon-white"></i>布局编辑</button>
              <button type="button" class="btn btn-primary" id="sourcepreview"><i class="icon-eye-open icon-white"></i>预览</button>
              <button type="button" class="btn btn-primary" id="markHtml"><i class="icon-eye-open icon-white"></i>识别</button>

            </div>
            <div class="btn-group">
              <button type="button" class="btn btn-primary" data-target="#downloadModal" rel="/build/downloadModal" role="button" data-toggle="modal"><i class="icon-chevron-down icon-white"></i>下载</button>
              <button class="btn btn-primary" href="/share/index" role="button" data-toggle="modal" data-target="#shareModal"><i class="icon-share icon-white"></i>保存</button>
              <button class="btn btn-primary" href="#clear" id="clear"><i class="icon-trash icon-white"></i>清空</button>
            </div>
            <div class="btn-group">
              <button class="btn btn-primary" href="#undo" id="undo" ><i class="icon-arrow-left icon-white"></i>撤销</button>
              <button class="btn btn-primary" href="#redo" id="redo" ><i class="icon-arrow-right icon-white"></i>重做</button>
            </div>
          </li>
        </ul>
        <ul class="nav pull-right">
          <li><a href="/">网站首页</a></li>
          <li>
            <div class="btn-group">
              <%--<span><iframe class="github-btn" src="http://ghbtns.com/github-btn.html?user=dodgepudding&repo=layoutit&type=watch&count=true" allowtransparency="true" frameborder="0" scrolling="0" width="80px" height="20px"></iframe></span>--%>
              <%--<span><iframe class="github-btn" src="http://ghbtns.com/github-btn.html?user=dodgepudding&repo=layoutit&type=fork&count=true" allowtransparency="true" frameborder="0" scrolling="0" width="80px" height="20px"></iframe></span>--%>
            </div>
          </li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
  </div>
</div>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="">
      <div class="sidebar-nav">
        <ul class="nav nav-list accordion-group">
          <li class="nav-header">
            <div class="pull-right popover-info"><i class="icon-question-sign "></i>
              <div class="popover fade right">
                <div class="arrow"></div>
                <h3 class="popover-title">功能</h3>
                <div class="popover-content">这里是说明</div>
              </div>
            </div>
            <i class="icon-plus icon-white"></i> 文件列表 </li>
          <li style="display: list-item;" class="rows" id="fileList">
              <iframe src="<%=request.getContextPath()  %>/template/editor/filelist.jsp" width="100%" frameborder="0" width="100%" name="iframe" class="iframe" scrolling="yes" style="" title="iframe" height="450px;"></iframe>
          </li>
        </ul>
        <ul class="nav nav-list accordion-group">
          <li class="nav-header">
            <div class="pull-right popover-info"><i class="icon-question-sign "></i>
              <div class="popover fade right">
                <div class="arrow"></div>
                <h3 class="popover-title">功能</h3>
                <div class="popover-content">这里是说明</div>
              </div>
            </div>
            <i class="icon-plus icon-white"></i> 组件列表 </li>
          <li style="display: none;" class="rows" id="componentList">
            <iframe src="" width="100%" frameborder="0" width="100%" id="componentframe"  name="componentframe" class="componentframe" scrolling="yes" style="" title="componentframe" height="450px;"></iframe>
          </li>
        </ul>
        <%--<ul class="nav nav-list accordion-group">--%>
          <%--<li class="nav-header"><i class="icon-plus icon-white"></i> 基本CSS--%>
            <%--<div class="pull-right popover-info"><i class="icon-question-sign "></i>--%>
              <%--<div class="popover fade right">--%>
                <%--<div class="arrow"></div>--%>
                <%--<h3 class="popover-title">帮助</h3>--%>
                <%--<div class="popover-content">这里提供了一系列基本元素样式，你可以通过区块右上角的编辑按钮修改样式设置。如需了解更多信息，请访问<a target="_blank" href="http://twitter.github.io/bootstrap/base-css.html">基本CSS.</a></div>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</li>--%>
          <%--<li style="display: none;" class="boxes" id="elmBase">--%>
            <%--<div class="box box-element ui-draggable"> <a href="#close" class="remove label label-important"><i class="icon-remove icon-white"></i>删除</a> <span class="drag label"><i class="icon-move"></i>拖动</span>--%>
            	 <%--<span class="configuration"><button type="button" class="btn btn-mini" data-target="#editorModal" role="button" data-toggle="modal">编辑</button> <span class="btn-group"> <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">对齐 <span class="caret"></span></a>--%>
              <%--<ul class="dropdown-menu">--%>
                <%--<li class="active"><a href="#" rel="">默认</a></li>--%>
                <%--<li class=""><a href="#" rel="text-left">靠左</a></li>--%>
                <%--<li class=""><a href="#" rel="text-center">居中</a></li>--%>
                <%--<li class=""><a href="#" rel="text-right">靠右</a></li>--%>
              <%--</ul>--%>
              <%--</span> <span class="btn-group"> <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">标记 <span class="caret"></span></a>--%>
              <%--<ul class="dropdown-menu">--%>
                <%--<li class="active"><a href="#" rel="">默认</a></li>--%>
                <%--<li class=""><a href="#" rel="muted">禁用</a></li>--%>
                <%--<li class=""><a href="#" rel="text-warning">警告</a></li>--%>
                <%--<li class=""><a href="#" rel="text-error">错误</a></li>--%>
                <%--<li class=""><a href="#" rel="text-info">提示</a></li>--%>
                <%--<li class=""><a href="#" rel="text-success">成功</a></li>--%>
              <%--</ul>--%>
              <%--</span> </span>--%>
              <%--<div class="preview">标题栏</div>--%>
              <%--<div class="view">--%>
                <%--<h3 contenteditable="true">h3. 这是一套可视化布局系统.</h3>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</li>--%>
        <%--</ul>--%>
      </div>
    </div>
    <!--/span-->
    <div style="min-height: 590px;" class="demo ui-sortable">
      <iframe src="" width="100%" frameborder="0" width="100%" name="htmlframe" id="htmlframe" class="htmlframe" scrolling="yes" style="" htmlframe="iframe" height="100%" ></iframe>
    </div>
    <!--/span-->
    <div id="download-layout">
      <div class="container-fluid"></div>
    </div>
  </div>
  <!--/row-->
</div>
<!--/.fluid-container-->
<div class="modal hide fade" role="dialog" id="editorModal">

  <div class="modal-header"> <a class="close" data-dismiss="modal">×</a>
    <h3>编辑</h3>
  </div>
  <div class="modal-body">
    <p>
      <textarea id="contenteditor"></textarea>
    </p>
  </div>
  <div class="modal-footer"> <a id="savecontent" class="btn btn-primary" data-dismiss="modal">保存</a> <a class="btn" data-dismiss="modal">关闭</a> </div>
</div>
<div class="modal hide fade" role="dialog" id="downloadModal">
  <div class="modal-header"> <a class="close" data-dismiss="modal">×</a>
    <h3>下载</h3>
  </div>
  <div class="modal-body">
    <p>已在下面生成干净的HTML, 可以复制粘贴代码到你的项目.</p>
    <div class="btn-group">
      <button type="button" id="fluidPage" class="active btn btn-info"><i class="icon-fullscreen icon-white"></i> 自适应宽度</button>
      <button type="button" class="btn btn-info" id="fixedPage"><i class="icon-screenshot icon-white"></i> 固定宽度</button>
    </div>
    <br>
    <br>
    <p>
      <textarea></textarea>
    </p>
  </div>
  <div class="modal-footer"> <a class="btn" data-dismiss="modal">关闭</a> </div>
</div>
<div class="modal hide fade" role="dialog" id="shareModal">
  <div class="modal-header"> <a class="close" data-dismiss="modal">×</a>
    <h3>保存</h3>
  </div>
  <div class="modal-body">保存成功</div>
  <div class="modal-footer"> <a class="btn" data-dismiss="modal">Close</a> </div>
</div>

<div style="display: none;">
</div>
<!-- Analytics
    ================================================== -->
<script type="text/javascript">

  $(function(){
    $("#markHtml").click(function(){
     var border =  $(window.frames["htmlframe"].document).find(".hframe-component").css("border");
      if(border == "2px solid rgb(255, 0, 0)"){
        $(window.frames["htmlframe"].document).find(".hframe-component").css("border","");
      }else {
        $(window.frames["htmlframe"].document).find(".hframe-component").css("border","2px solid rgb(255, 0, 0)");
      }

    });
  });

  function loadfile(url){
    $("#htmlframe").attr("src",'<%=request.getContextPath() %>/template/editor/pageload.jsp?url=' + url);
    setTimeout("markComponent()",500);
  }
setTimeout("markComponent()",500);

function markComponent(){
  var url = $("#htmlframe").attr("src");
  url = url.substring(url.indexOf("url") + 4);
  $("#componentframe").attr("src",'<%=request.getContextPath()  %>/template/editor/componentlist.jsp?url=' +url);

  $(window.frames["htmlframe"].document).find(".hframe-component").css("border","2px solid red");
//  $(window.frames["htmlframe"].document).find(".hframe-component").css("background-color","rgba(0,0,212,0.3)");
//  $(window.frames["htmlframe"].document).find(".hframe-component *").css("background-color","rgba(0,0,212,0.3)");
}

</script>
<style type="text/css">

  .hframe-component {
    border:2px solid red;
    /*background: url(/image/zhanwei.jpg);*/
    /*filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";*/
    /*-moz-background-size:100% 100%;*/
    /*background-size:100% 100%;*/
    /*opacity: 0.1;*/
    /*background-color:#000000;*/
    background-color:rgba(0,0,212,0.3);

  }

  .hframe-component *{
    /*background: url(/image/zhanwei.jpg);*/
    /*filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";*/
    /*-moz-background-size:100% 100%;*/
    /*background-size:100% 100%;*/
    /*opacity: 0.1;*/
    /*background-color:#000000;*/
    background-color:rgba(0,0,212,0.3);

  }

</style>
</body>
</html>
