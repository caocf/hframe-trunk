<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    <base href="http://localhost:8080/Hframe/">
    
    <title>对话框</title>
	


<script language="JavaScript" src="/Hframe/secframe/common/common.js"/></script>
<script src="/Hframe/webframe/common/cookie.jsp"></script>
<link rel="stylesheet" href="/Hframe/webframe/common/main.css" type="text/css">

<script src="/Hframe/jsv2/i18n/AILocale.jsp"></script>
<script language="JavaScript" src="/Hframe/jsv2/i18n/appframe_js_resource_zh_CN.js"></script>
<script src="/Hframe/jsv2/Globe_v2.jsp"></script>
<script language="JavaScript" src="/Hframe/jsv2/i18n/secframe_js_resource_zh_CN.js"></script>

<link id="theme_css_id" rel="stylesheet" type="text/css">

<script language="javascript">
document.onload = setupFontSize();
document.onload = setup();
</script>
 
<script type="text/javascript" src="/Hframe/jsv2/AIEvent.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/CommUtil.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/AIWaitBanner.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/showTitle.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/Globe_v2.jsp"></script>
<script type="text/javascript" src="/Hframe/jsv2/DBListBox.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/UserData_v2.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/PopMenu_v2.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/HtmlParameter.js"></script>
<script type="text/javascript" src="/Hframe/jsv2/DBTree_new.js"></script>

	
			<script type="text/javascript" src="/Hframe/js/jquery-1.6.3.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/form.css">
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/grid.css">
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/list.css">
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/tab.css">
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/cascademenu.css">
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/button.css">
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/common.css">
		<link rel="stylesheet" type="text/css" href="/Hframe/theme/default/css/tabframe.css">
		
		
		
		<script type="text/javascript" src="/Hframe/js/common/base.js"></script>
		<script type="text/javascript" src="/Hframe/js/common/openTip.js"></script>
		<script type="text/javascript" src="/Hframe/js/common/openDialog.js"></script>
		<script type="text/javascript" src="/Hframe/js/common/contextmenu.js"></script>
		<script type="text/javascript" src="/Hframe/js/common/tree.js"></script>
		<script type="text/javascript" src="/Hframe/js/common/ajax.js"></script>
		<script type="text/javascript" src="/Hframe/js/common/tab.js"></script>
		<script type="text/javascript" src="/Hframe/js/common/table.js"></script>
		
		

			
			 


     <base href="http://localhost:8080/Hframe/">
 
  <link type="text/css" rel="stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/jscal2.css" />
    <link type="text/css" rel="stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/border-radius.css" />
    <!-- <link type="text/css" rel="stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/reduce-spacing.css" /> -->

    <link id="skin-win2k" title="Win 2K" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/win2k/win2k.css" />
    <link id="skin-steel" title="Steel" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/steel/steel.css" />
    <link id="skin-gold" title="Gold" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/gold/gold.css" />
    <link id="skin-matrix" title="Matrix" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/matrix/matrix.css" />

    <link id="skinhelper-compact" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/reduce-spacing.css" />

    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/jscal2.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/unicode-letter.js"></script>

    <!-- you actually only need to load one of these; we put them all here for demo purposes -->
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/ca.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/cn.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/cz.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/de.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/es.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/fr.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/hr.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/it.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/jp.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/nl.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/pl.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/pt.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/ro.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/ru.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/sk.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/sv.js"></script>

    <!-- this must stay last so that English is the default one -->
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/en.js"></script>

		<style>
	input {
		border:1px solid #888;
	}
</style>
		
	<script type="text/javascript" src="/Hframe/third/dtree/dtree.js"></script>
	
	<link rel="STYLESHEET" type="text/css" href="/Hframe/third/dhtmlxTree/css/dhtmlXTree.css">
	<script  src="/Hframe/third/dhtmlxTree/js/dhtmlXCommon.js"></script>
	<script  src="/Hframe/third/dhtmlxTree/js/dhtmlXTree.js"></script>
	<script  src="/Hframe/third/dhtmlxTree/js/dhtmlXTree_start.js"></script>
	   <script  src="/Hframe/third/dhtmlxTree/js/dhtmlXTree_dragIn.js"></script>
	
	
	<!-- 
	<link rel="stylesheet" href="/Hframe/third/protomenu/proto.menu.0.6.css" type="text/css" media="screen" />
	
    <script type="text/javascript" src="/Hframe/third/protomenu/prototype_trunk.js"></script>
    
    <script type="text/javascript" src="/Hframe/third/protomenu/proto.menu.0.6.js"></script>
     -->
    
	<script type="text/javascript">
	
	
		function getSYSTEMPATH(){
			return "/AutoSystem";
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
			var _FRAMEID='';
		
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
	
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="/Hframe/js/common/openDialog.js"></script>

	<script type="text/javascript">
		function ok(){
		
		}
		
	</script>
	
  </head>
  
  <body onload="load();" style="padding: 10px;text-align: center;" >
  
  
  	



<table class='zqh_form' id='test'>
<input id='test_TAGINFO'  name='test_TAGINFO' type='hidden' value='tag_Type=myform&tag_Id=test&tag_Width=null&tag_Height=null&tag_Title=数据库&tag_View=core_db&tag_ColNum=3&tag_Editablenull&initial=null&tag_ObjectId=&tag_Condition=null&tag_Url=Hframe/coredb/_coredb_create&tag_DefaultBtn=ajax'/>
 <form action='/Hframe/coredb/_coredb_create' method='post' id='test_FORM'>
			<thead>
				<tr>
										
										
										
									
					<td colspan=100% class='zqh_form_title'><center><h2>数据库</h2></center></td>
				</tr>
			</thead>
			<tbody>
				;
								
																<tr>
										
												
												<input name='coreDb.id' type='hidden' style='$widthStype' 
														/>
					
																				
				
								
															
												
												<input name='coreDb.createTime' type='hidden' style='$widthStype' 
														/>
					
																				
				
								
															
												
																	
																				
					
							
							<td  class='zqh_form_td_name' style='width:0'>
							
							
															描述：
							</td>
							
							
							<td colspan=1  class='zqh_form_td_value' style='width:0'>
							
																			 

	<input name='coreDb.descprition' style='' 
			/>


 


																		
																</td>
																				
				
								
															
												
																		</tr>
							<tr>
																			
																				
					
							
							<td  class='zqh_form_td_name' style='width:0'>
							
							
															数据库名称：
							</td>
							
							
							<td colspan=1  class='zqh_form_td_value' style='width:0'>
							
																			 

	<input name='coreDb.dbName' style='' 
			/>


 


																		
																</td>
																				
				
								
															
												
																		</tr>
							<tr>
																			
																				
					
							
							<td  class='zqh_form_td_name' style='width:0'>
							
							
															显示名称：
							</td>
							
							
							<td colspan=1  class='zqh_form_td_value' style='width:0'>
							
																			 

	<input name='coreDb.showName' style='' 
			/>


 


																		
																</td>
																					</tr>
										
				
							</tbody>		
		<tfoot>
				<tr>
					<td class='zqh_form_footer' colspan=100%> <button  id='1'  onclick="btnClk_Sys(this,'action-ajax','test/createDialog.jsp',0,'{param=null&isAjax=&priv=&targetObj=&targetType=}')"                  class="btn1_mouseout" onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'" style='width:;height:' title='保存' >保存</button>



			
				</td>
				</tr>
			</tfoot>
			</form>
		</table>
	
  
    <button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"   onclick="ok();">确认</button>
    <button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"   onclick="cancel_Sys();">取消</button>
  </body>
</html>

