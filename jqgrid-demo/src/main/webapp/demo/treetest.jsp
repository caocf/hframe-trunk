<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframe.common.util.VelocityUtil"%>
<%@page import="com.hframe.common.bean.Column"%>
<%@page import="com.hframe.common.bean.ShowType"%>
<%@page import="com.hframe.common.bean.Option"%>
<%@page import="com.hframe.common.bean.User"%>
<%@page import="com.hframe.common.ssh.service.CommonServ"%>
<%@page import="com.zqh.aaa.fullformtest.po.FullFormTest"%>
<%@page import="java.io.File"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml-strict.dtd">

<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
	#userGridDiv{
		overflow: scroll;
		width: 1000px;
	}
	
	
	.icon-sub{
		padding:10px;
		width: 100px;
		height: 40px;
		
	}
	
	#munuTreedf12{
		  position:absolute;
		  z-index:99;
		  background-color:#eee;
	}
	</style>
	
	<script type="text/javascript">
		function refreshList(id,pageIndex,pageSize){
			var taginfo=document.getElementById(id+"_TAGINFO").value;
			taginfo=taginfo+"&pageSize="+pageSize+"&pageIndex="+pageIndex;
			$.ajax({
				type:"post",
				url:"<%=path%>/core/core_page_getListContentByAjax?"+taginfo,
				success:function(msg){
					$("#"+id+" tbody").html(msg);
				}
			});
		}
		function refresh_Sys(type,id,param){
			var taginfo=document.getElementById(id+"_TAGINFO").value;
			//alert("<%=path%>/core/core_page_refreshTagContent?"+taginfo+"&"+param);
			
			var startpart=taginfo.substring(0,taginfo.indexOf("&tag_Title=")+11);
			var tempart=taginfo.substring(taginfo.indexOf("&tag_Title=")+11);
			var title=tempart.substring(0,tempart.indexOf("&"));
			var endpart=tempart.substring(tempart.indexOf("&"));
			
			taginfo=startpart+encodeURI(encodeURI(title))+endpart;
			if(param!=null&&param!=""){
				param=param+"&";
			}
			$.ajax({
				type:"post",
				url:"<%=path%>/core/core_page_refreshTagContent?"+param+taginfo,
				success:function(msg){
					
					if(taginfo.indexOf("tag_Type=dtree&")>-1){
						eval(msg);
					}else{
						document.getElementById(id).outerHTML=msg;
					}
				}
			});
		}
		
		function TreeClick_Sys4Other(id){
		
			var url = TreeClickGetUrl_Sys(id);
			var text = TreeClickGetText_Sys(id);
			url = url.substring(url.indexOf("AutoSystem")+11);
			addTab_Sys('aaatab',text,url);
		}
		
	</script>
	 
  </head>
  
    
  <body style="text-align: center;">
  	 <!--  <zqh:dtree treeId="munuTree" view="treeDemo" objects="<%=new CommonServ().getTreeElement()%>" condition="1=1"></zqh:dtree> -->
  	  <h3>一、选择<%=request.getRealPath("/design/")%></h3>
  	  	 
  	  	  <zqh:dtree treeId="munuTreedf12" view="SYS_FILE_TREE" objects="<%=new CommonServ().getFileList(request)%>" condition="1=1" title="当前页面" checkbox="false" defaultBtn="select"  onClick="TreeClick_Sys4Other">	
  	  	 </zqh:dtree> 
  	  	 
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  	<br/>
  	  
  	  	<zqh:frame height="500" style="float:left;clear:right;" width="1000" frameId="aaatab" isTab="true" src="demo/framedemo_left.jsp"></zqh:frame>
  	  	
  	  	
 </body>
</html>
