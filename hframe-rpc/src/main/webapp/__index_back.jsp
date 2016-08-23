<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframework.common.util.VelocityUtil"%>
<%@page import="com.hframework.common.bean.Column"%>
<%@page import="com.hframework.common.bean.ShowType"%>
<%@page import="com.hframework.common.bean.Option"%>
<%@page import="com.hframework.common.bean.User"%>
<%@page import="com.hframework.common.ssh.service.CommonServ"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	
	
	
	</script>
  </head>
  
    
  <body>
  	
  	<input type="checkbox" onclick="allSelect_Sys(this)" >

	(<hr>
		<a href="form.jsp" target="_blank">表格----form.jsp</a>
		<a href="frame.jsp" target="_blank">框架----frame.jsp</a>
		<a href=contextmenu.jsp target="_blank">右键菜单----contextmenu.jsp</a>
		<a href=menubar.jsp target="_blank">级联菜单----menubar.jsp</a>
		<a href=tab2.jsp target="_blank">标签页----tab2.jsp</a>
		<a href=tipInput.jsp target="_blank">动态输入框----tipInput.jsp</a>
		<a href=tree.jsp target="_blank">树----tree.jsp</a>
	
	
	<hr> 
  

		<input type="radio">
		<%
			User user=new User();
			user.setId("111");
			user.setAddr("四川。成都");
			user.setGender("1");
			user.setName("张三");
			user.setBirthdate("1988,2,2");
			user.setRank("7");
			user.setHob("2,3");
			
			
			user.setDeveloper("鸿哥");
			User user2=new User();
			user2.setId("222");
			user2.setAddr("四川。南充 ");
			user2.setGender("0");
			user2.setName("王五");
			user2.setBirthdate("1988,9,25");
			user2.setRank("2");
			user2.setHob("1,2,3");
			
			
			List userList=new ArrayList();
			userList.add(user);
			userList.add(user2);
		%>
		 <%=User.class%>
		 <center>
		<%//VelocityUtil.produceTemplateContent("com/hframe/tag/vm/autoformtemplate.vm",map)
		 %>
		</center>
		<hr>
		 <center>
		<%//VelocityUtil.produceTemplateContent("com/hframe/tag/vm/autogridtemplate.vm",map)
		 %>
	
	<div>最终组件代码：
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'openwin-update','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">修改</button>
	
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'openwin-create','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">创建</button>
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'action-delete','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">删除</button>
	<button class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  title=""
	 id="submitBtn" name="submitBtn" onclick="btnClk_Sys(this,'action-save','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')" class="icon-sub">保存</button>
	
	</div>
	<zqh:button src="" model="openwin-update" buttonId="111" icon="" buttonName="111" title="修改"></zqh:button>
	<zqh:button src="" model="openwin-create" buttonId="111" icon="" buttonName="111" title="创建"></zqh:button>
	<zqh:button src="" model="action-delete" buttonId="111" icon="" buttonName="111" title="删除"></zqh:button>
	<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="保存"></zqh:button>
	
	<hr/>
	<zqh:myform formid="full1_form_test" view="full_form_test" colNum="4" title="测试" object="<%=null %>" editable="true" width="1800" defaultBtn="save,reset,back" isAjax="">
	<center>
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="保存"></zqh:button>
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="重置"></zqh:button>
	</center>
	</zqh:myform>
	<zqh:myform formid="full_form_test" view="full_form_test" colNum="4" title="测试" object="<%=null %>"  editable="true" width="1800" defaultBtn="save,reset,back" isAjax="" objectId="660813252612227">
	</zqh:myform>
	

	<zqh:myform formid="sys_user_role" view="comment" colNum="3" title=" " object="<%=user %>" editable="text" width="800" defaultBtn="back"></zqh:myform>
 	<zqh:myform formid="diarygroup" view="diary_group" colNum="3" title=" " object="<%=user %>" editable="true" url="" defaultBtn="save,reset,back"></zqh:myform>
	<zqh:myform formid="sys_org" view="sys_org" colNum="3" title=" " object="<%=user %>" editable="true" width="800" defaultBtn="save,reset,back"></zqh:myform>

 	<div id="userGridDiv2"  style="overflow: scroll;width: 1200px;">
	 	<zqh:mygrid gridId="userGrid2" view="full_form_test" width="800"  objects="<%=userList %>" title="列表" editable="true" defaultBtn="save,reset,back"></zqh:mygrid>
	  </div>
	 <div id="userGridDiv2"  style="overflow: scroll;width: 1200px;">
	 	<zqh:mygrid gridId="userGrid2" view="sys_user_role" width="800"  objects="<%=userList %>" title="列表" editable="text" defaultBtn="save,reset,back"></zqh:mygrid>
	  </div>
	  <div id="userGridDiv"  style="overflow: scroll;width: 1200px;">
	 	<zqh:mygrid gridId="diarygroup1" view="diary_group" width="800"  objects="<%=userList %>" title="列表" editable="true" defaultBtn="save,reset,back"></zqh:mygrid>
	  </div>
  		</center>
  		
  	 <zqh:mylist listId="222" view="DiaryGroupList" condition="1=1" objects="<%=userList %>" title="DiaryGroupList" defaultBtn="save,reset,back"></zqh:mylist>
  	<a href="javascript:refreshList(222,2,10);">下一个</a>
  	 <zqh:dtree treeId="munuTree" view="treeDemo" objects="<%=new CommonServ().getTreeElement()%>" condition="1=1"></zqh:dtree>
  	  	 <zqh:dtree treeId="munuTreedf" view="sys_org" objects="<%=null %>" condition="1=1"></zqh:dtree>
  	
  	<br><br>
  	<br><br>
  	<input name='fullFormTest.orgId'  style='' /><a href="javascript:void(0);" onclick="magnifier_Sys(this,'myDialog.jsp?VIEW=DiaryGroupList&TYPE=LIST&CONDITION=&TITLE=我日','{user_id=100&user_name=zhangsan}',400,500)"><img src="test/fangdajing.jpg" width="20" height="20" class="magnifier_img_sys"></a>
  	          
<input id="calendar-inputField" /><button id="calendar-trigger">...</button>             
        
              <div id="cont"></div>
       <script type="text/javascript">
            var LEFT_CAL = Calendar.setup({
                    cont: "cont",
                    weekNumbers: true,
                    trigger    : "calendar-trigger",
        			inputField : "calendar-inputField",
                    selectionType: Calendar.SEL_MULTIPLE,
                    selection     : Calendar.dateToInt(new Date()),
                    dateFormat:"%Y-%m-%d %H:%M:%S",
                    showTime: 12,
                      fdow     : 1,
                      onSelect      : function() {
				        var count = this.selection.countDays();
				        if (count == 1) {
				            var date = this.selection.get()[0];
				            date = Calendar.intToDate(date);
				            date = Calendar.printDate(date, "%A, %B %d, %Y");
				            this.hide();
				        }
				    }
            })
            LEFT_CAL.hide();
             LEFT_CAL.setLanguage("cn");
            
                  
       </script>
              <div id="calendar-info" style="text-align: center; margin-top: 0.3em"></div>
              <input type="radio">
  </body>
</html>
