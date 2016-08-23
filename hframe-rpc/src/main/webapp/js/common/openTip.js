

function tip_Sys(thisObj,param){

	var inputVal=thisObj.value;
	
	var ulObj=thisObj.nextSibling.nextSibling;
		
	
	$.ajax({
	   type: "POST",
	   url:"core/core_page_getTipContent",
	   data: "param="+param+"&inputVal="+inputVal,
	   success: function(msg){
	    ulObj.innerHTML=msg;
	   }
	}); 

}

function showTip_Sys(thisObj,param,userMouse){

	/*
	var tipCont="<ul style=\"border:1px solid #666666;background:#eeeeee;padding:5px;position:absolute;display:;\">";
	tipCont=tipCont+"<li><a href=\"javascript:void(0);\" onclick=\"checkTip_Sys(this,'<%=1%>')\"><%=new CommonServ().getEnums().get(1) %></a></li>";
	tipCont=tipCont+"</ul>";
	thisObj.outerHTML=thisObj.outerHTML+"<br/>"+tipCont;	
	*/


	var ulObj=thisObj.nextSibling.nextSibling;
	ulObj.style.display="";
	if(userMouse==1){
		ulObj.style.left=event.x;
	}
	tip_Sys(thisObj,param);
	//ulObj.innerHTML="<li><a href=\"javascript:void(0);\" onclick=\"checkTip_Sys(this,'<%=1%>')\"><%=new CommonServ().getEnums().get(1) %>111111211111111111111111</a></li>";
	
	
}

var evtTempObj;
function hiddenTip_Sys(){
	 var oEvt = event.srcElement;
	// alert( oEvt.nextSibling.nextSibling.outerHTML);
	setTimeout(function(){
		$(oEvt.nextSibling.nextSibling).css("display","none");
	},200);
	 
	
}
function disp(){
	alert(evtTempObj);
	$(evtTempObj.nextSibling.nextSibling).css("display","none");
}

function checkTip_Sys(thisObj,val,text){
	var targetObj=thisObj.parentNode.parentNode.previousSibling.previousSibling;
	targetObj.value=text;
	targetObj.previousSibling.value=val;
	thisObj.parentNode.parentNode.style.display="none";
}