<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<%@include file="third/JSCal2-1.9/includeJsCalender.jsp" %>
	
	<style type="text/css">
	.magnifier_img_sys  {
		
		border: 1px solid #ddd;
		margin-left: 1px;
		margin-bottom: 0px;
		padding-bottom: 0px;
	}
	
	</style>
	 	<script type="text/javascript">
  		
  		function open1(thisObj){
	  		var transObj=new Object();
	  		transObj.name="张三";
	  		transObj.age=48;
	  		/*
	  		1、refreshFlag:是否改页面要刷新
				a.表示需要刷新
				b.不需要刷新	  		
 		
	  		*/
	  		var retObj=showMyDialog("myDialog.jsp",400,300,0,transObj);
	  		
	  		if(retObj!=null){
		  		alert(retObj[2]);
		  		alert(retObj[1][1]);
		  		
	  		}
  		}
  		
  		function showMyDialog(src,width,height,refreshFlag,obj){
  			
  				if(width==null){
  					width=300;
  				}
  				if(height==null){
  					height=400;
  				}
  				var ret = window.showModalDialog(src,obj,"scroll:no;help:no;resizable:no;status:no;dialogHeight:"+height+"px;dialogWidth:"+width+"px");
	  			if(refreshFlag==1){
	  				if(ret!=null){
	  					window.location.reload();
	  					return null;
	  				}
	  			}
  				return ret;
  		}
  	
  		/*
  		*thisObj:选择器本身,用于找出赋值的前一个对象
  		*param：入参
  		*targetObj:目标对象（如果缺省，则取thisObj对象的上一个元素）
  		*
  		*/
  		function magnifier_Sys(thisObj,src,param,targetObj,width,height,targetObj){
  			
  			var transObj=new Object();
  			if(param!=null){
  				
  				param=param.substring(1,param.length-1);
  				
  				var params=param.split(";");
  				for(var i=0;i<params.length;i++){
  					var key=params[i].split("=")[0];
  					var value=params[i].split("=")[1];
  					eval("transObj."+key+" = '"+value+"'");
  				}
  			}
  			showMyDialog(src,width,height,0,transObj);
  			
  			thisObj.previousSibling.value=val;
  			
  		
  		}
  	
  	</script>
  
  </head>
  	
 
  <zqh:button src="myDialog.jsp" model="dialog" text="创建" param="" onreturn="refresh" targetobject="form1" targettype="form" isajax="true"></zqh:button>
  
  <zqh:button src="myDialog.jsp" model="dialog" text="选择分公司" onreturn="setvalue" target="this.grid1"></zqh:button>
  <zqh:button src="myDialog.jsp" model="dialog" text="删除" onreturn="refresh" target="this.grid1"></zqh:button>
  
  <body>
    
    <a href="javascript:void(0);" onclick="open1(this)">选择组织</a>
    
    
    
    <input id="aaa" ><a href="javascript:void(0);" onclick="magnifier_Sys(this,'myDialog.jsp','{user_id=100;user_name=zhangsan}',400,500)"><img src="fangdajing.jpg" width="20" height="20" class="magnifier_img_sys"></a>
             
<input id="calendar-inputField" /><button id="calendar-trigger">...</button>             
        
              <div id="cont"></div>
       <script type="text/javascript">
       /*
           %a — abbreviated weekday name
    %A — full weekday name
    %b — abbreviated month name
    %B — full month name
    %C — the century number
    %d — the day of the month (range 01 to 31)
    %e — the day of the month (range 1 to 31)
    %H — hour, range 00 to 23 (24h format)
    %I — hour, range 01 to 12 (12h format)
    %j — day of the year (range 001 to 366)
    %k — hour, range 0 to 23 (24h format)
    %l — hour, range 1 to 12 (12h format)
    %m — month, range 01 to 12
    %o — month, range 1 to 12
    %M — minute, range 00 to 59
    %n — a newline character
    %p — PM or AM
    %P — pm or am
    %s — UNIX time (number of seconds since 1970-01-01)
    %S — seconds, range 00 to 59
    %t — a tab character
    %W — week number
    %u — the day of the week (range 1 to 7, 1 = MON)
    %w — the day of the week (range 0 to 6, 0 = SUN)
    %y — year without the century (range 00 to 99)
    %Y — year with the century
    %% — a literal '%' character
       */
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
                   // min: 10090408,
   	 				//max: 20091225,
                      onSelect      : function() {
				        var count = this.selection.countDays();
				        if (count == 1) {
				            var date = this.selection.get()[0];
				            date = Calendar.intToDate(date);
				            date = Calendar.printDate(date, "%A, %B %d, %Y");
				            //alert(date);
				           // document.getElementById("calendar-info").innerHTML = date;
				            this.hide();
				         //   $("calendar-info").innerHTML = date;
				        } else {
				        
				       
				         
				        }
				    },
				    onTimeChange  : function(cal) {
				        var h = cal.getHours(), m = cal.getMinutes();
				        // zero-pad them
				        if (h < 10) h = "0" + h;
				        if (m < 10) m = "0" + m;
				        $("calendar-info").innerHTML = Calendar.formatString("Time changed to ${hh}:${mm}", {
				            hh: h,
				            mm: m
				        });
				    }
                    //titleFormat: "%B %Y"
            })
            LEFT_CAL.hide();
             LEFT_CAL.setLanguage("cn");
          //  LEFT_CAL.manageFields("calendar-inputField", "calendar-trigger", "%Y-%m-%d %I:%M %p");
            
            
                   var links = document.getElementsByTagName("link");
                   var skins = {};
             for (var i = 0; i < links.length; i++) {
                     if (/^skin-(.*)/.test(links[i].id)) {
                             var id = RegExp.$1;
                             skins[id] = links[i];
                     }
             }
             function changeSkin(select) {
                          var skin = select;//skin-gold
                          for (var i in skins) {
                                  if (skins.hasOwnProperty(i))
                                          skins[i].disabled = true;
                          }
                          if (skins[skin])
                                  skins[skin].disabled = false;
              };
               //  changeSkin('matrix');//win2k steel gold  matrix
                  
       </script>
              <div id="calendar-info" style="text-align: center; margin-top: 0.3em"></div>
  </body>
</html>
