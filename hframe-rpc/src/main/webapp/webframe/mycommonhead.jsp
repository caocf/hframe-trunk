		
		<!--
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.3.min.js"></script>
		-->

		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/form.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/grid.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/list.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/tab.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/cascademenu.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/button.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/common.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/default/css/tabframe.css">
		
		
		
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/openTip.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/openDialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/contextmenu.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/ajax.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/table.js"></script>
		
		<!-- import bootstrap -->
		<link href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<!--
		-->
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>

			
			<%@include file="../third/JSCal2-1.9/includeJsCalender.jsp" %>
		<style>
	input {
		border:1px solid #888;
	}
</style>
		
	<script type="text/javascript" src="<%=request.getContextPath() %>/third/dtree/dtree.js"></script>
	
	<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath() %>/third/dhtmlxTree/css/dhtmlXTree.css">
	<script  src="<%=request.getContextPath() %>/third/dhtmlxTree/js/dhtmlXCommon.js"></script>
	<script  src="<%=request.getContextPath() %>/third/dhtmlxTree/js/dhtmlXTree.js"></script>
	<script  src="<%=request.getContextPath() %>/third/dhtmlxTree/js/dhtmlXTree_start.js"></script>
	   <script  src="<%=request.getContextPath() %>/third/dhtmlxTree/js/dhtmlXTree_dragIn.js"></script>
	
	
	<!-- 
	<link rel="stylesheet" href="<%=request.getContextPath() %>/third/protomenu/proto.menu.0.6.css" type="text/css" media="screen" />
	
    <script type="text/javascript" src="<%=request.getContextPath() %>/third/protomenu/prototype_trunk.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/third/protomenu/proto.menu.0.6.js"></script>
     -->
    
	<script type="text/javascript">
	
	
		function getSYSTEMPATH(){
			return "<%=request.getContextPath() %>";
		}
	
		$(function(){
			autoResizeFrame();
		});
		$(function(){
			$(".tab_content_current").attr("class","tab_content");
			$(".tab_content:first").attr("class","tab_content_current");
			
			
			//$(".tab_title:first").attr("class","tab_title_current");
			
			$(".tab_title").click(function(){
				$(".tab_title_current").attr("class","tab_title");
				$(this).addClass("tab_title_current");
				
				var tabTitle=$(this).attr("id");
				var number=tabTitle.substring(tabTitle.indexOf("tab_title_")+10);
				
				$(".tab_content_current").attr("class","tab_content");
				$("#tab_content_"+number).attr("class","tab_content_current");


			});
		
		});	
		
		function callBackJs(type){
		
			if(type=="tab"){
			
				$(".tab_content_current").attr("class","tab_content");
				$(".tab_content:first").attr("class","tab_content_current");
			
				
				//$(".tab_title:first").attr("class","tab_title_current");
				
				$(".tab_title").click(function(){
					$(".tab_title_current").attr("class","tab_title");
					$(this).addClass("tab_title_current");
					
					var tabTitle=$(this).attr("id");
					var number=tabTitle.substring(tabTitle.indexOf("tab_title_")+10);
					
					$(".tab_content_current").attr("class","tab_content");
					$("#tab_content_"+number).attr("class","tab_content_current");
	
	
				});
		
			}
		
		}
		
		function autoResizeFrame(){
			var _FRAMEID='<%=request.getParameter("_FRAMEID")==null?"":request.getParameter("_FRAMEID") %>';
			if(parent.setFrameHeight) {
				parent.setFrameHeight(_FRAMEID,document.body.scrollHeight);
			}
			if(parent.setFrameWidth) {
				parent.setFrameWidth(_FRAMEID,document.body.scrollWidth);
			}
		}
		
		function setFrameHeight(frameName,height){
	    	var frame = document.getElementById(frameName);
	    	if(frame!=null){
	        	frame.height= height;
	    	}
        	
		}
		function setFrameWidth(frameName,width){
	    	var frame = document.getElementById(frameName);
	    	if(frame!=null)
	        	frame.width= width+5;
		}
		
		function getFrameIds(){
			var iframes=document.getElementsByTagName("iframe");
			
			var ids="";
			for(var i=0;i<iframes.length;i++){
				ids+=iframes[i].id+";";
			}
			
			return ids;
		}
		
	function injectMenuEvent(menuIdStr){
		 for(var x = 1; x < 4; x++)
		{
		var menuid = document.getElementById(menuIdStr+"_menu"+x);
		menuid.num = x;
			menuInit();
		}
		function menuInit()
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
 	}
 	

	
	</script>
	<style type="text/css">
		*  {
			margin: 0px;
		}
		
	
	</style>