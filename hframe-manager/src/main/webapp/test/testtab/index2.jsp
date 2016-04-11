<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <link rel="stylesheet"  href="css/webwidget_scroller_tab.css" type="text/css" />
        <script type='text/javascript' src='js/jquery-1.3.2.min.js'></script>
        <script type='text/javascript' src='js/webwidget_scroller_tab.js'></script>
        <script language="javascript" type="text/javascript">

            $(function() {
            
            	$(".zqh_tab .tabContainer .tabHead li").live("click",function(){
            		if($(this).attr("class")!='currentBtn'){
	            		var curIndex=$(this).parent().children("li").index($(this));
	            		$(".zqh_tab .tabContainer .tabHead .currentBtn").removeClass("currentBtn");
	            		$(this).addClass("currentBtn");
	            	
	            		$(".zqh_tab .tabBody .currentTabCot").attr("class","tabCot");
	            		$(".zqh_tab .tabBody ul li").eq(curIndex).attr("class","currentTabCot");
	            		
	            		
	            		
            		}
            		
            	});
            	
            	$(".deleteTab").live("click",function(){
            		if($(this).parent().attr("class")=="currentBtn"){
            			//$(this).parent().prev().attr("class","currentBtn");
            			$(this).parent().prev().click();
            			
            		}
            		$(this).parent().remove();
            	});
            	$(".addTab").click(function(){
            		$(this).prev().append("<li><a href=\"javascript:void(0);\">资源管理</a><span class=\"deleteTab\">×</span></li>");
            		
            		$(this).prev().find("li").eq($(this).prev().find("li").size()-1).click();
            	});
            });
            
            
        </script>
  </head>
  
  <body>
 <div class="zqh_tab" id="tab1">
          <div class="tabContainer">
            <ul class="tabHead">
              <li class="currentBtn" ><a href="javascript:void(0);">首页</a></li>
              <li ><a href="javascript:void(0);">产品管理</a><span class="deleteTab">×</span></li>
              <li><a href="javascript:void(0);">资源管理</a><span class="deleteTab">×</span></li>
            </ul>
            <span class="addTab">+</span>
          </div>
          <div class="tabBody">
            <ul>
              <li class="currentTabCot">
                <iframe src="/index.jsp"></iframe>
              </li>
              <li class="tabCot">
                <iframe src="/Hframe/index.jsp"></iframe>
              </li>
              <li class="tabCot" >
                <iframe src="/index.jsp"></iframe>
              </li>
            </ul>
            <div style="clear:both"></div>
          </div>
          <div class="modBottom"> <span class="modABL">&nbsp;</span><span class="modABR">&nbsp;</span> </div>
        </div>
        
        

  </body>
</html>
