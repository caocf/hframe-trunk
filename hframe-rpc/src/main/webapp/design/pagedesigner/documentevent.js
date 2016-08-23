var moveObj;//焦点对象

var moveObjPaddingLeft;
var moveObjPaddingRight;
var moveObjPaddingTop;
var moveObjPaddingBottom;
var moveObjLeft;
var moveObjTop;
var addBgImg=0;

var isMouseDown=0; //记录鼠标是否按下
var resizeObjType="none";  //记录是否元素调整大小
var borderBackUp="1px solid #ccc;";

function doDocMouseDown(){

		
	/*
	if(""==event.srcElement.id){
		return ;
	}
	*/
	
	/*
	if(moveObj!= undefined){
		if(undefined!=moveObj.id&&""!=moveObj.id&&"container"!=moveObj.id){
			moveObj.style.border=borderBackUp;
		}
	}
	*/
	
	isMouseDown=1;
	 var oEvt = event.srcElement;
	 
	 //在焦点对象上加上红框，这里有问题，有空再来看吧...
	 if(moveObj!= undefined){
	 	if(undefined!=moveObj.id&&""!=moveObj.id&&"container"!=moveObj.id){
			if(oEvt.id!=moveObj.id){
				moveObj.style.border=borderBackUp;
				borderBackUp=oEvt.style.border;
				oEvt.style.borderColor="red";
			}
			
		}
	 }
	 moveObj=oEvt;
	 
	 
	 
	 
	 var mouseX = (event.x + document.body.scrollLeft);
	 var mouseY = (event.y + document.body.scrollTop);
	
	 
	 moveObjPaddingLeft=parseInt(mouseX)-parseInt(moveObj.style.left);
	 moveObjPaddingRight=parseInt(moveObj.style.width)-moveObjPaddingLeft;
	 moveObjPaddingTop=parseInt(mouseY)-parseInt(moveObj.style.top);
	 moveObjPaddingBottom=parseInt(moveObj.style.height)-moveObjPaddingTop;
	 moveObjLeft=parseInt(moveObj.style.left);
	 moveObjTop=parseInt(moveObj.style.top);
	 
	 setFocusObjProperties(moveObj);
	 
	 
}

function toggleFocusObjRedRec(){
	if(document.getElementById("focusObjRedRec").style.display=="none"){
		if(moveObj==null)return;
		document.getElementById("focusObjRedRec").style.display="";
	 	document.getElementById("focusObjRedRec").style.left=moveObj.style.left;
	 	document.getElementById("focusObjRedRec").style.top=moveObj.style.top;
	 	document.getElementById("focusObjRedRec").style.width=moveObj.style.width;
	 	document.getElementById("focusObjRedRec").style.height=moveObj.style.height;
	}else{
		document.getElementById("focusObjRedRec").style.display="none";
	 	document.getElementById("focusObjRedRec").style.left=0;
	 	document.getElementById("focusObjRedRec").style.top=0;
	 	document.getElementById("focusObjRedRec").style.width=0;
	 	document.getElementById("focusObjRedRec").style.height=0;
	}
}

function getParentFocusObj(){
	if(moveObj==null) return ;
	
	moveObj=$(moveObj).parent();
}

function doDocMouseMove(){
	
	//添加到容器使用该程序段
	if(event.ctrlKey){
		return ;
	}
	
	mouseMove();
	if(isMouseDown==0&&moveObj!= undefined){
		 var mouseX = (event.x + document.body.scrollLeft);
		 var mouseY = (event.y + document.body.scrollTop);
		
		 var oEvt = event.srcElement;
		 moveObj=oEvt;
		 
		 paddingleft=parseInt(mouseX)-parseInt(moveObj.style.left);
		 moveObjPaddingRight=parseInt(moveObj.style.width)-paddingleft;
		 paddingtop=parseInt(mouseY)-parseInt(moveObj.style.top);
		 moveObjPaddingBottom=parseInt(moveObj.style.height)-paddingtop;
		 
		 
		 if(moveObjPaddingRight<2&&moveObjPaddingBottom<2){
			 resizeObjType="resize-xy";
			 moveObj.style.cursor = "NW-resize";
		 }else if(moveObjPaddingRight<2){
			 resizeObjType="resize-x"; 

			 moveObj.style.cursor = "E-resize";

		 }else if(moveObjPaddingBottom<2){
			 resizeObjType="resize-y"; 
			 moveObj.style.cursor = "N-resize";
		 }else{
			 resizeObjType="none";
			 moveObj.style.cursor = "hand";
		 }
	}
	
	if(isMouseDown==1){
		
		var mouseX = (event.x + document.body.scrollLeft);
		var mouseY = (event.y + document.body.scrollTop);
		
		if(resizeObjType=="none"){//如果不是改变大小，那么就移动
			moveObj.style.left=parseInt(mouseX)-parseInt(moveObjPaddingLeft);
			moveObj.style.top=parseInt(mouseY)-parseInt(moveObjPaddingTop);
		}else if(resizeObjType=="resize-x"){
			moveObj.style.width=parseInt(mouseX)-moveObjLeft;
		}else if(resizeObjType=="resize-y"){
			moveObj.style.heght=parseInt(mouseY)-moveObjTop;
		}else if(resizeObjType=="resize-xy"){
			moveObj.style.width=parseInt(mouseX)-moveObjLeft;
			moveObj.style.height=parseInt(mouseY)-parseInt(moveObj.style.top);
		}
	}
}

function doDocMouseUp(){
	isMouseDown=0;
	resizeObjType="none";
	
	//添加到容器使用该程序段
	if(event.ctrlKey){
		
		
		destObj = event.srcElement;
		
		if(destObj !=moveObj){
			
			if(destObj!=undefined &&destObj.id!=undefined &&destObj.id!="container"&&destObj.innerHTML!=undefined){
			
				
				//moveObj.style.position="relative";
				moveObj.style.position="";
				
				destObj.innerHTML+=moveObj.outerHTML;
				moveObj.outerHTML="";
				
			}
			
			if(destObj!=undefined &&destObj.id!=undefined &&destObj.id=="container"){
				moveObj.style.position="absolute";
				var moveObjContent=moveObj.outerHTML;
				moveObj.outerHTML="";
				 document.getElementById("container").innerHTML+=moveObjContent;
				 
			
			}
		}
		
	}
	
	
	
}

function doDocKeyDown(){
  switch(event.keyCode){
    case 27: //ＥＳＣ
      if(event.keyCode) NavBrush_onClick(document.getElementsByName("tbxToolbox_btn")[0]);
     break;
    case 46: //删除
     	 //var oEvt = event.srcElement;
		 //moveObj=oEvt;
		 
		 if("container"!=moveObj.id){
		 	 moveObj.outerHTML="";
		 }
		 
		// moveObj.style.display="none";
		 
      break;
	case 83: //保存
     // if(event.ctrlKey) saveFlow();
    //  if(event.ctrlKey) alert("haha");
      break;
    case 89: //撤消
      if(event.ctrlKey) redoLog();
      break;
    case 90: //恢复
      if(event.ctrlKey) undoLog();
      break;
  

	}
}

function doDocMouseOut(){
	if(resizeObjType!="none"&&isMouseDown==1){
		
		var mouseX = (event.x + document.body.scrollLeft);
		var mouseY = (event.y + document.body.scrollTop);
		
		if(resizeObjType=="resize-x"){
			moveObj.style.width=parseInt(mouseX)-moveObjLeft+5;
		}else if(resizeObjType=="resize-y"){
			moveObj.style.heght=parseInt(mouseY)-moveObjTop+5;
		}else if(resizeObjType=="resize-xy"){
			moveObj.style.width=parseInt(mouseX)-moveObjLeft+5;
			moveObj.style.height=parseInt(mouseY)-parseInt(moveObj.style.top)+5;
		}
	}
	
	
}

/*
function clearMouseDownFlag(){
	isMouseDown=0;
}
*/

//--------屏幕滚动事件-------------
window.onscroll = function() {
	document.getElementById("toolsDiv").style.top=(document.body.scrollTop+10)+"px";
	//document.getElementById("toolsDiv").style.left=(document.body.offsetWidth+document.body.scrollLeft-200)+"px";
	document.getElementById("screenY").value=document.body.scrollTop;
	document.getElementById("screenX").value=document.body.scrollLeft;
}


//--------鼠标滑动事件-------------
  function mouseMove(ev) {  
     var mousePos = mouseCoords(ev);  
     // alert(ev.pageX);  
     document.getElementById("mouseX").value = mousePos.x;  
     document.getElementById("mouseY").value = mousePos.y;  
 }  
   
 function mouseCoords(ev) {  
     evev = ev || window.event;  
    	 /* 
     if (ev.pageX || ev.pageY) { 
         return {  
             x : ev.pageX,  
             y : ev.pageY  
         };

     }  
    	  */ 
     return {  
         x : evev.clientX + document.body.scrollLeft - document.body.clientLeft,  
         y : evev.clientY + document.body.scrollTop - document.body.clientTop  
     };  
 }  
   


//document.onmousedown =doDocMouseDown;
document.onmousemove = doDocMouseMove;
document.onmouseup = doDocMouseUp;
document.onkeydown = doDocKeyDown;

/*
document.onselectstart = doDocSelectStart;
document.onerror = new Function("return false;");
*/



/*
这些都是点击右键触发的事件
*/

var copyContent="";
var objIdCounter="";

function copy_()
{
	 if("container"==moveObj.id){
	 	return false;
	 }
	
	 copyContent=moveObj.outerHTML;
	 
	 var objIdCounter=document.getElementById("objIdCounter").value;
	 objIdCounter++;
	 document.getElementById("objIdCounter").value=objIdCounter;
	 moveObj.id="cp"+objIdCounter;
	 
	 document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+copyContent;
	 
}




function parse_()
{
}




function delete_()
{
	 if("container"!=moveObj.id){
		 	 moveObj.outerHTML="";
	}
}

function up_()
{
	
	 if("container"==moveObj.id){
	 	return false;
	 }
	
	 moveObj.style.zIndex= moveObj.style.zIndex+1;
	 if(zindexMax<moveObj.style.zIndex){
	 	zindexMax=moveObj.style.zIndex;
	 }
}
function down_()
{
	 if("container"==moveObj.id){
	 	return false;
	 }
	
	 moveObj.style.zIndex= moveObj.style.zIndex-1;
	 if(zindexMin>moveObj.style.zIndex){
	 	zindexMin=moveObj.style.zIndex;
	 }
}
function bottom_()
{
	 if("container"==moveObj.id){
	 	return false;
	 }
	 zindexMin-=1;
	 moveObj.style.zIndex=zindexMin;
	 

}
function top_()
{
	
	 if("container"==moveObj.id){
	 	return false;
	 }
	
	 zindexMax+=1;
	 moveObj.style.zIndex=zindexMax;

}

function script_()
{
	 alert(moveObj.outerHTML);

}

function bgimg_(){

	tabsClick('psdTabs');
	addBgImg=1;
	addBgImgFun(moveObj);

}

function addhref_(){

	var url=window.showModalDialog("pagenameopration.jsp","dialogHeight:630px;dialogWidth:1000px;center:yes;");

		if(undefined==url){
			return ;
		}
		while(url.indexOf("package=")!=-1){
			
			url=window.showModalDialog("pagenameopration.jsp?"+url,"dialogHeight:630px;dialogWidth:1000px;center:yes;");
		}
	

	var innerStr="<a href=\"javascript:IWIILGO('"+url+"')\">"+moveObj.innerHTML+"</a>";
	
	moveObj.innerHTML=innerStr;
	alert(innerStr);
}
