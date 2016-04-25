<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="com.hframe.demo.vo.MenuVO"%>
<%@page import="com.hframework.common.ssh.service.CommonServ"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ include file="/webframe/mycommonhead.jsp" %>
    
	<%@ include file="/webframe/commonhead.jsp" %>

	

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	
	-->

	<style type="text/css">
	
	input{
			border:1px solid #888;
	
	}
	
	</style>
	
	<title>css+js无限级菜单-w3css.com</title>
<script type="text/javascript">document.execCommand("BackgroundImageCache", false, true);</script><!-- IE6背景图片闪烁问题  -->
<script type="text/javascript">


</script>
	<script type="text/javascript" src="<%=path %>/js/common/openTip.js"></script>
</head>
<body style="text-align: center;">

 <h1>1、日期组件</h1>
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
       <input id="calendar1-inputField" /><button id="calendar1-trigger">...</button>             
              <div id="cont1"></div>
       <script type="text/javascript">
            var LEFT_CAL = Calendar.setup({
                    cont: "cont1",
                    weekNumbers: true,
                    trigger    : "calendar1-trigger",
        			inputField : "calendar1-inputField",
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
 <h1>2、弹出框组件</h1>
<input name='fullFormTest.orgId'  style='' /><input name='fullFormTest.orgName'  style='' /><a href="javascript:void(0);" onclick="magnifier_Sys(this,'myDialog.jsp?VIEW=DiaryGroupList&TYPE=LIST&CONDITION=&TITLE=我日','{user_id=100&user_name=zhangsan}',400,500)"><img src="test/fangdajing.jpg" width="20" height="20" class="magnifier_img_sys"></a>
<input name='fullFormTest.orgId1'  style='' /><input name='fullFormTest.orgName1'  style='' /><a href="javascript:void(0);" onclick="magnifier_Sys(this,'myDialog.jsp?VIEW=sys_org&TYPE=DTREE&CONDITION=&TITLE=组织结构','{user_id=100&user_name=zhangsan}',400,500)"><img src="test/fangdajing.jpg" width="20" height="20" class="magnifier_img_sys"></a>
 
 <h1>3、提示框组件</h1>

<table>
	<tr>
		<td>
		    <input id="inputB1ar" ><input id="inputBar" onfocus="showTip_Sys(this,'{sql:111}',0)" onblur="hiddenTip_Sys()" onkeyup="tip_Sys(this,'{sql:111}')" value=""><br>
		 	<ul style="border:1px solid #666666;background:#eeeeee;padding:5px;position:absolute;display:none ;">
		 	</ul>
		</td>
</tr>
</table>
fdsafdsafdsafdsafdsafdsafsd

<input id="inputB11ar" ><input id="inputBar" onfocus="showTip_Sys(this,'{sql:222}',1)" onblur="hiddenTip_Sys()" onkeyup="tip_Sys(this,'{sql:222}')" value=""><br>
 <ul style="border:1px solid #666666;background:#eeeeee;padding:5px;position:absolute;display:none ;">
 <li><a href="javascript:void(0);" onclick="checkTip_Sys(this,'<%=1 %>')"><%=new CommonServ().getEnums().get(1)%>11111111111111111111111</a></li>
 </ul>
 
<input id="inputB12112ar" ><input id="inputBa212r" onfocus="showTip_Sys(this,'{sql:333}',1)" onblur="hiddenTip_Sys()" onkeyup="tip_Sys(this,'{sql:333}')" value=""><br>
<ul style="border:1px solid #666666;background:#eeeeee;padding:5px;position:absolute;display:none ;"></ul>

<input id="inputB121121ar" ><input id="inputBa1212r" onfocus="showTip_Sys(this,'{sql:444}',1)" onblur="hiddenTip_Sys()" onkeyup="tip_Sys(this,'{sql:444}')" value=""><br>
<ul style="border:1px solid #666666;background:#eeeeee;padding:5px;position:absolute;display:none ;"></ul>

<label id="aaa"></label>
fdsafds

 
 <h1>4、下拉框</h1>
<zqh:select selectId="hahah" visible="" view="db_ele" width="150px" onChange="refresh_Sys('','123456',this.value);" initial="true"></zqh:select>
<button onclick="select('hahah');"> 确定</button>
<script type="text/javascript">
function select(id){
	var value=document.getElementById(id).value;
	alert(value);
}

 <h1>5、复选，单选，上传组件</h1>
</script>
</body>

</html>
