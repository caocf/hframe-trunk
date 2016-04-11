<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=request.getParameter("type")==null?"":request.getParameter("type");
String src=request.getParameter("src")==null?"":request.getParameter("src");
	
pageContext.setAttribute("type"	,type);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>页面设计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./pagedesigner.css">
	<script type="text/javascript" src="./pagedesigner.js"></script>
	<script type="text/javascript" src="./colorchoose.js"></script>
	<script type="text/javascript" src="./documentevent.js"></script>
	<script type="text/javascript" src="./backfetch.js"></script>
	<script type="text/javascript" src="./datatabledell.js"></script>
	<script type="text/javascript" src="./contentmenu.js"></script>
	<script type="text/javascript" src="<%=path %>/third/dtree/dtree.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	
	<%@ include file="/webframe/mycommonhead.jsp" %>	
	<base href="<%=basePath%>">
	<script type="text/javascript">
	
		var type='<%=type %>';
		$(function(){
		
			if("<%=src%>"!=""){
				loadPage("<%=src%>");
			}
			
			var content="";
			$.ajax({
				type:"post",
				url:"<%=path%>/core/core_page_ajaxData?type=dbList",
				success:function(msg){
					var rows=msg.split(";");
					for(var i=0;i<rows.length;i++){
						
						if(rows[i]!=""){
							var cols=rows[i].split(":");
							content+="<option value='"+cols[0]+"'>"+cols[1]+"("+cols[2]+")</option>";
						}
					}
					$("#currentDb").html(content);
					getTables();
				}
			});
			
			$(document).keydown(function(e){
				if(e.ctrlKey&&e.shiftKey&&e.keyCode==82){//Ctrl+Shift+R 打开资源面板
					$("#toolsDiv").toggle();
				}else if(e.ctrlKey&&e.keyCode==88){
					toggleFocusObjRedRec();
				}else if(e.ctrlKey&&e.keyCode==38){
					//getParentFocusObj();
				}else if(e.ctrlKey&&e.keyCode==83){
					exportPage();
				}else if(e.ctrlKey&&e.shiftKey&&e.keyCode==84){//Ctrl+Shift+T 打开文件系统
					parent.toolDivToggle();
				}
			});
			
			
			if("logindesigner"==type){
				$("input[name=methodSelect][value=create1]").attr("checked","checked");
				
				var tableId=$("select[name=currentTable]").find("option:selected").val();
				$.ajax({
					type:"post",
					url:"<%=path%>/core/core_page_loadloginPage",
					success:function(msg){
					
						var src="<table bordercolor='black' onmouseover='showControlTd(this)' onmouseout='hiddenControlTd(this)' border='1'  style='position:absolute;left:400px;top:200px;z-index: 5;width:300px;height:200px;border:1px solid #ccc;'>"+msg+"</table>";
						document.getElementById("container").innerHTML=src;
					}
				});
			}
		
		$(".tag_OK").click(function(){
			
				var param="";
			
				var tableContent=$(this).parent().parent().parent();
				tableContent.find("input[class!=tag_OK][class!=tag_Refresh] , select").each(function(){
					param+=$(this).attr("class")+"="+$(this).val()+"&";
				});
				tableContent.find("input[class=tag_Refresh]").show();
				$(this).hide();
				
				var _TagObj=new TagObj();//创建对应的标签js对象
				tableContent.find("input[class!=tag_OK][class!=tag_Refresh] , select").each(function(){
				
					var setMethodName="set"+$(this).attr("class").substring(4,$(this).attr("class").length);
					//alert(setMethodName);
					
					for(var p in _TagObj){
						if(setMethodName==p){
							_TagObj[setMethodName]($(this).val());	
						}
					}
				});
				_layOut.addTagObject(_TagObj);
				
				$("#tagObjContent").html(_layOut.toString());
				
				//alert(_layOut.toString());
				param+="AA123=123";
				//alert(param);
				$.ajax({
					type:"post",
					url:"<%=path%>/core/core_page_getTagContent?"+param,
					success:function(msg){
						alert(msg);
						clickTool("background_tag","",msg);
						//alert($(this).parent().parent().parent().find("[id=tag_Id]").val());
						//injectMenuEvent($(this).parent().parent().parent().find("[id=tag_Id]").val());
					}
					
				});
				
			
			});
			
			$(".tag_Refresh").click(function(){
			
				var param="";
			
				var tableContent=$(this).parent().parent().parent();
				tableContent.find("input[class!=tag_OK][class!=tag_Refresh] , select").each(function(){
					param+=$(this).attr("class")+"="+$(this).val()+"&";
				});
				
				var _TagObj=_layOut.getCurTagObject();//获取当前标签对应的js对象
				tableContent.find("input[class!=tag_OK][class!=tag_Refresh] , select").each(function(){
				
					var setMethodName="set"+$(this).attr("class").substring(4,$(this).attr("class").length);
					//alert(setMethodName);
					
					for(var p in _TagObj){
						if(setMethodName==p){
							_TagObj[setMethodName]($(this).val());	
						}
					}
				});
				//_layOut.addTagObject(_TagObj);
				
				$("#tagObjContent").html(_layOut.toString());
				
				param+="AA123=123";
				//alert(param);
				$.ajax({
					type:"post",
					url:"<%=path%>/core/core_page_getTagContent?"+param,
					success:function(msg){
						//alert(msg);
						clickTool("background_tag_refresh","",msg);
					}
				});
			});
	});
	
	var bgContent="";
	function getTables(){
		var dbId=$("select[name=currentDb]").find("option:selected").val();
		var content="";
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_ajaxData?type=tableList",
			data:"dbId="+dbId,
			success:function(msg){
				var rows=msg.split(";");
				for(var i=0;i<rows.length;i++){
					if(rows[i]!=""){
						var cols=rows[i].split(":");
						content+="<option value='"+cols[0]+"'>"+cols[1]+"("+cols[2]+")</option>";
					}
				}
				$("#currentTable").html(content);
			}
		});
	}
	
	function searchfile(){
		var fileName=window.showModalDialog("chooseFile.jsp","dialogHeight:400px;dialogWidth:600px;center:yes;");
		if(fileName!=null){
			document.getElementById("tag_Src").value=fileName;
		}
	}
	
	function getTableContent(){
		var tableId=$("select[name=currentTable]").find("option:selected").val();
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_ajaxData?type=tableData",
			data:"tableId="+tableId+"&method="+$("input[name=methodSelect][checked]").val(),
			success:function(msg){
				clickTool("background","",msg);
			}
		});
		
	}
	
	function exportPage(){
		//将页面的tag对象转化为xml格式字符串放在containerXMLdiv中
		document.getElementById("containerXML").innerHTML=_layOut.toXmlString();
		_layOut.transHtmlContentToTag();
		
		var content=document.getElementById("container").outerHTML;
		//alert(content);
		
		//通过ajax传值到后台的时候&会被系统当着关键词使用
		content=content.replace(new RegExp("&","g"),"zqhReg");
		
		/*var pageName=null;
		while(pageName==null&&!pageName.endWith(".jsp")&&!pageName.endWith(".html")&&!pageName.endWith(".htm"){
		}
		*/
		var pageName = pageName=window.showModalDialog("pagenameopration.jsp",_layOut.getDynObj(),"dialogHeight:400px;dialogWidth:600px;center:yes;");

		if(pageName==null){//点击取消的时候，返回
			_layOut.transTagToHtmlContent();
			return ;
		}
		while(pageName.indexOf("package=")!=-1){
			pageName=window.showModalDialog("pagenameopration.jsp?"+pageName,_layOut.getDynObj(),"dialogHeight:400px;dialogWidth:600px;center:yes;");
		}
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_ajaxData?type=exportpage",
			data:"pageName="+pageName+"&content="+content,
			success:function(msg){
				alert(msg);
			}
		});
		
		_layOut.transTagToHtmlContent();
	}
	
	function reviewPage(){
		var content=document.getElementById("container").outerHTML;
		content=content.replace(new RegExp("&","g"),"zqhReg");
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_ajaxData?type=reviewPage",
			data:"content="+content,
			success:function(msg){
				window.open("./pagedesignerview.jsp");
			}
		});
	}
	
	function loadPage(src){
		$.ajax({
			type:"post",
			url:"<%=path%>/core/core_page_ajaxData?type=loadPage&src="+src,
			success:function(msg){
				if(msg != null && "" != msg){
					$("#container").html(msg);
					loadFromXml();
				}
			}
		});
	}
	
	function loadPageCont(){
	
		var pageCont = '<DIV style="POSITION: absolute; DISPLAY: none">'+
					'<xml id=containerXML>'+
					'<LayOut idCount="1"><TagObj type="myform" id="" width="400" height="200" title="默认标题1" view="core_component" editable="0" colNum="3" condition=""/></LayOut>'+
					'</xml>'+
					'</DIV>'+
					'<DIV style="Z-INDEX: 5; BORDER-BOTTOM: #ccc 1px solid; POSITION: absolute; BORDER-LEFT: #ccc 1px solid; WIDTH: 200px; HEIGHT: 20px; BORDER-TOP: #ccc 1px solid; TOP: 501px; CURSOR: hand; BORDER-RIGHT: #ccc 1px solid; LEFT: 432px" id=div1>点击修改div内容</DIV>'+
					'<DIV style="Z-INDEX: 5; BORDER-BOTTOM: #ccc 1px solid; POSITION: absolute; BORDER-LEFT: #ccc 1px solid; WIDTH: 400px; HEIGHT: 200px; BORDER-TOP: #ccc 1px solid; TOP: 624px; CURSOR: hand; BORDER-RIGHT: #ccc 1px solid; LEFT: 410px" id=TAGDIV_>'+
					'<zqh:myform condition="" colNum="3" editable="0" view="core_component" title="默认标题1" height="200" width="400" formid="" defaultBtn=""></zqh:myform>'+
					'</DIV>';
		if(pageCont != null){
			$("#container").html(pageCont);
			loadFromXml();
		}
	
	}
	
	function bgDell(type){
		$(".tagAttrDiv").hide();
		$("#tagAttrDiv #"+type+"Div").show();
		var id=_layOut.getIdCount();
		$("#tagAttrDiv #"+type+"Div .tag_Id").val(type+""+id);
		$("#tagAttrDiv #"+type+"Div .tag_Title").val("默认标题"+id);
		$("#tagAttrDiv #"+type+"Div .tag_Refresh").hide();
		$("#tagAttrDiv #"+type+"Div .tag_OK").show();
		return;
	}
	
	
	function loadFromXml(xmlStr){
		if(xmlStr == null ){
			xmlStr=document.getElementById("containerXML").innerHTML;
		}
		if(xmlStr!=null&&xmlStr!=""){
			//alert(xmlStr+"1111111111111111");
			_layOut.loadXmlToObj(xmlStr);
			$("#tagObjContent").html(_layOut.toString());
			
		}
	}
	</script>
  </head>
  
  <body onload="">
	<div id="container"  onclick="doClick()" oncontextmenu= showMenu('') onmousedown="doDocMouseDown()">
		<div  style="position: absolute;display: none;">
			<xml id='containerXML'></xml>
		</div>
		
	</div>
	
	<jsp:include page="pagedesignertools.jsp"></jsp:include>
	<jsp:include page="colorchoose.jsp"></jsp:include>
  </body>
  <img id="expendImg" style="display: none;position: absolute;width: 200px;height: 200px;"/>
   <!-- 右键菜单 -->
  <div id="itemMenu" style="display:none">
           <table border="1" width="100%" height="100%" bgcolor="#cccccc" style="border:thin" cellspacing="0">
          <tr>
               <td style="cursor:default;border:outset 1;" align="center" onclick="parent.copy_()">
               复制一份
               </td>
           </tr>
<!-- 
             <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.parse_();">
                 粘贴
                 </td>
             </tr>
-->
             <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.delete_()">
                 删除
                 </td>
             </tr>
              <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.down_()">
                向下一层
                 </td>
             </tr>
              <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.up_()">
                向上一层
                 </td>
             </tr>
              <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.bottom_()">
               置底
                 </td>
             </tr>
             <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.top_()">
               置顶
                 </td>
             </tr>
             <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.bgimg_()">
               添加背景图
                 </td>
             </tr>
               <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.addhref_()">
               添加链接
                 </td>
             </tr>
             <tr>
                 <td style="cursor:default;border:outset 1;" align="center" onclick="parent.script_()">
               脚本
                 </td>
             </tr>
           </table>
    </div> 
    <div id="focusObjRedRec" style="border: 1px solid red;position: absolute;width: 0;height: 0px;display: none;z-index: 1000"></div>
  <div id="hiddenFrameDiv">
  	<iframe id="hiddenFrame" src="design/pagedesigner/hidden.jsp"></iframe>
  </div>
</html>
