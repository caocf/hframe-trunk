
var toolType;//表明选择的工具类型(choose,h1,h2,h3,h4,h5,)
var toolStr;//表明新建对象时候的零时字符串
var focusObj;//表明正处于焦点的对象
var objIdCounter;//id计数器，每创建一个对象计数器加一

var backgroundData;

var addBgImgFlag=0;
var addBgImgObj;


var materialSrc="";

function clickTool(type ,src,msg){
	toolType=type;
	document.getElementById("toolType").value=toolType;
	
	if("img"==type){
		materialSrc=src;
	}
	
	if("background"==type){
		backgroundData=msg;
	}
	if("background_tag"==type){
		backgroundData=msg;
	}
	if("background_tag_refresh"==type){
		backgroundData=msg;
	}
	
	
	if(addBgImgFlag==1){
		addBgImgObj.style.backgroundImage="url("+src+")";
	}
	
	
}

function tabsClick(type){

	//隐藏该工具栏
	if(document.getElementById(type+"Content").style.display==''){
		document.getElementById(type+"Content").style.display='none';
		document.getElementById("objAttrContent").style.display='none';
		
		return;
	}
	
	document.getElementById("objAttrContent").style.display='';

	
	document.getElementById("baseTabs").style.backgroundColor='#eee';
	document.getElementById("psdTabs").style.backgroundColor='#eee';
	document.getElementById("bgTabs").style.backgroundColor='#eee';
	document.getElementById("baseTabs").style.fontSize='15px';
	document.getElementById("psdTabs").style.fontSize='15px';
	document.getElementById("bgTabs").style.fontSize='15px';
	document.getElementById("baseTabs").style.borderWidth='0px';
	//document.getElementById("psdTabs").style.borderWidth='0px';
	//document.getElementById("bgTabs").style.borderWidth='0px';
	
	
	
	document.getElementById("baseTabsContent").style.display='none';
	document.getElementById("psdTabsContent").style.display='none';
	document.getElementById("bgTabsContent").style.display='none';
	
	document.getElementById(type).style.fontSize='15px';
	document.getElementById(type).style.backgroundColor='#aaa';
	//document.getElementById(type).style.border='1px solid black';
	
	
	document.getElementById(type+"Content").style.display='';
	
}


 var moreAttrFlag=0;
function moreAtrr(){



		if(moreAttrFlag==0){
			moreAttrFlag=1;
			document.getElementById("moreAttr").innerHTML="返回<<<";
			document.getElementById("baseTabsContent").style.display='none';
			
		}else{
			moreAttrFlag=0;
			document.getElementById("moreAttr").innerHTML="更多>>";
			document.getElementById("baseTabsContent").style.display='';
			
			
			
		}
	}
	

function doClick(){
	//如果是添加背景图片，则不使用该方法
	if(addBgImgFlag==1){
		addBgImgFlag=0;
		toolType="choose";
		return;
	}

	
	var mouseX=document.getElementById("mouseX").value;
	var mouseY=document.getElementById("mouseY").value;
	objIdCounter=document.getElementById("objIdCounter").value;

	var rows=document.getElementById("rows").value;
	var cols=document.getElementById("cols").value;

	var tablecontent="";
	for(var i=1;i<=rows;i++){
		tablecontent+="<tr>";
		for(var j=1;j<=cols;j++){
			tablecontent+="<td onclick='innerSource(this)'>1</td>";
		}
		tablecontent+="</tr>";
	}

	var h1Str="<h1 id='h1"+objIdCounter+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:200px;height:20px;border:1px solid #ccc;'  >点击修改内容</h1>";
	var h2Str="<h2 id='h2"+objIdCounter+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:150px;height:20px;border:1px solid #ccc;' >点击修改内容</h2>";
	var h3Str="<h3 id='h3"+objIdCounter+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:120px;height:20px;border:1px solid #ccc;' >点击修改内容</h3>";
	var h4Str="<h4 id='h4"+objIdCounter+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:105px;height:20px;border:1px solid #ccc;' >点击修改内容</h4>";
	var h5Str="<h5 id='h5"+objIdCounter+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:100px;height:20px;border:1px solid #ccc;' >点击修改内容</h5>";
	var backgroundStr="<table bordercolor='black' onmouseover='showControlTd(this)' onmouseout='hiddenControlTd(this)' border='1' id='table"+objIdCounter+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:300px;height:200px;border:1px solid #ccc;' >"+backgroundData+"</table>";
	
	var imgStr="<img id='img"+objIdCounter+"' src='"+materialSrc+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:200px;height:200px;border:1px solid #ccc;' />";
	
	var tableStr="<table bordercolor='black' border='1' id='table"+objIdCounter+"' style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:300px;height:200px;border:1px solid #ccc;' >"+tablecontent+"</table>";
	
	var divStr="<div id='div"+objIdCounter+"'  style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:200px;height:20px;border:1px solid #ccc;'  >点击修改div内容</div>";
	
	var marqueeStr="<marquee  id='marquee"+objIdCounter+"'   style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:200px;height:20px;border:1px solid #ccc;'  >你可以修改里面的内容</marquee> ";

	var ul1Str="<ul id='ulc"+objIdCounter+"' style=\"position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:105px;height:20px;border:1px solid #ccc;'\"><li style=\"padding: 5px;\">列表1</li><li style=\"padding: 5px;\">列表2</li><li style=\"padding: 5px;\">列表3</li></ul>";
	var ul2Str="<ul id='ulr"+objIdCounter+"' style=\"position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:105px;height:20px;border:1px solid #ccc;'\"><li style=\"float:left;padding: 5px;\">列表1</li><li style=\"float:left;padding: 5px;\">列表2</li><li style=\"float:left;padding: 5px;\">列表3</li></ul>";
	
	var ol1Str="<ol id='olc"+objIdCounter+"' style=\"position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:105px;height:20px;border:1px solid #ccc;'\"><li style=\"padding: 5px;\">列表1</li><li style=\"padding: 5px;\">列表2</li><li style=\"padding: 5px;\">列表3</li></ol>";
	var ol2Str="<ol id='olr"+objIdCounter+"' style=\"position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:105px;height:20px;border:1px solid #ccc;'\"><li style=\"float:left;padding: 5px;\">列表1</li><li style=\"float:left;padding: 5px;\">列表2</li><li style=\"float:left;padding: 5px;\">列表3</li></ol>";
	
	
	
	
	if(toolType=="h1"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=h1Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("h1"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	
	}else if(toolType=="h2"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=h2Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("h2"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
		
	}else if(toolType=="h3"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=h3Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("h3"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
		
	}else if(toolType=="h4"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=h4Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("h4"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
		
	}else if(toolType=="h5"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=h5Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("h5"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="table"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=tableStr;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("table"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="img"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=imgStr;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("table"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="background"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=backgroundStr;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("table"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="background_tag"){
	
		var _tagId= _layOut.getCurTagObject().getId();
		var _width= _layOut.getCurTagObject().getWidth();
		var _height= _layOut.getCurTagObject().getHeight();
		var _type = _layOut.getCurTagObject().getType();
		
		var _jsCode="";
	
		//objIdCounter++;
		//document.getElementById("objIdCounter").value=objIdCounter;
		if(_type=="dtree"){
			var jsCode=backgroundData.substring(backgroundData.indexOf("<script")+31,backgroundData.indexOf("</script>"));
				//alert(jsCode);
				//document.getElementById("container").write("dsfdsafdsfdsafdsafdsa").append(eval(jsCode));
				//eval("alert('abc');");
				//eval(jsCode);
				//document.getElementById("hiddenFrame")
			//document.frames("hiddenFrame").writeHTML(jsCode);
			
			document.frames('hiddenFrame').document.write("<div id='CONTENT'>");
			jsCode=jsCode.replace("document.write","document.frames('hiddenFrame').document.write");
			eval(jsCode);
			document.frames('hiddenFrame').document.write("</div>");
			var treeCont=document.frames('hiddenFrame').document.getElementById("CONTENT").innerHTML;
			
			toolStr="<div id='TAGDIV_"+_tagId+"'  style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:"+_width+"px;height:"+_height+"px;border:1px solid #ccc;'  >"+treeCont+"</div>";
			
			_jsCode=jsCode.substring(0,jsCode.indexOf("document.frames('hiddenFrame').document.write"));
			alert(_jsCode);
			
			eval(_jsCode);
			
			eval("var  i=10;");
			
			alert(i);
			
			//toolStr=document.all("hiddenFrame").getHTML();
			
		}else{
			toolStr="<div id='TAGDIV_"+_tagId+"'  style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:"+_width+"px;height:"+_height+"px;border:1px solid #ccc;'  >"+backgroundData+"</div>";
		}
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		
		
		
		if(_type=="menu"){
			injectMenuEvent(_tagId);
		}
		
		
		callBackJs(_type);
		//focusObj=document.getElementById("background_tag"+(objIdCounter-1));
		//setFocusObjProperties(focusObj);
	}else if(toolType=="background_tag_refresh"){
	
		var _tagId= _layOut.getCurTagObject().getId();
		var _width= _layOut.getCurTagObject().getWidth();
		var _height= _layOut.getCurTagObject().getHeight();
		
		//objIdCounter++;
		//document.getElementById("objIdCounter").value=objIdCounter;
		toolStr="<div id='TAGDIV_"+_tagId+"'  style='position:absolute;left:"+mouseX+"px;top:"+mouseY+"px;z-index: 5;width:"+_width+"px;height:"+_height+"px;border:1px solid #ccc;'  >"+backgroundData+"</div>";
		document.getElementById("TAGDIV_"+_tagId+"").outerHTML=toolStr;
		//document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		//focusObj=document.getElementById("background_tag"+(objIdCounter-1));
		//setFocusObjProperties(focusObj);
	}else if(toolType=="div"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=divStr;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("div"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="marquee"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=marqueeStr;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("marquee"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="ul1"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=ul1Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("ulc"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="ul2"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=ul2Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("ulr"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="ol1"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=ol1Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("olc"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}else if(toolType=="ol2"){
		objIdCounter++;
		document.getElementById("objIdCounter").value=objIdCounter;
		toolStr=ol2Str;
		document.getElementById("container").innerHTML=document.getElementById("container").innerHTML+toolStr;
		toolType="choose";
		focusObj=document.getElementById("olr"+(objIdCounter-1));
		setFocusObjProperties(focusObj);
	}
	
	
	
	
	



	
}

function setFocusObjProperties(focusObject){

	document.getElementById("focusObjId").innerHTML=focusObject.id;
	//document.getElementById("focusObjName").innerHTML=focusObj.name;
	document.getElementById("focusObjText").value=focusObject.innerHTML;
	document.getElementById("focusObjWidth").value=focusObject.style.width;
	document.getElementById("focusObjHeight").value=focusObject.style.height;

	focusObj=focusObject;
	
}

var tdFocus;

function innerSource(x){
	document.getElementById("sourceDiv").style.display="";
	tdFocus=x;
}

function returnValue(x){

	tdFocus.innerHTML="<img src='"+x.src+"' width='45' height='45'/>";
}
 

function changeAttributeValue(type){
	
	var val=event.srcElement.value;
	
	if(type=='text'){
		
		focusObj.innerHTML=val;
	}else if(type=='width'){
		focusObj.style.width=val;
	}else if(type=='height'){
		focusObj.style.height=val;
	}else if(type=='bg-color'){
		focusObj.style.backgroundColor=val;
	}else if(type=='font-color'){
		focusObj.style.color=val;
	}else if(type=='border-color'){
		focusObj.style.borderColor=val;
	}else if(type=='border-size'){
		focusObj.style.borderWidth=val;
	}else if(type=='font-size'){
		focusObj.style.fontSize=val;
	}else if(type=='padding'){
		focusObj.style.padding=val; 	
	}else if(type=='margin'){
		focusObj.style.margin=val; 	
	}else{
		$(focusObj).css(""+type+"",val);
	}
	
}


function expendImg(src){
	
	var mouseX=document.getElementById("mouseX").value;
	var mouseY=document.getElementById("mouseY").value;
	
	document.getElementById("expendImg").style.display='';
	document.getElementById("expendImg").src=src;
	document.getElementById("expendImg").style.left=mouseX;
	document.getElementById("expendImg").style.top=mouseY;
	


}

function unexpendImg(){
 	document.getElementById("expendImg").style.display='none';
 }
 
function addBgImgFun(Obj){
	addBgImgFlag=1;
	addBgImgObj=Obj;
}


function IWIILGO(url){
	
	if(confirm("是否查查看链接地址")){
	
		window.open(url);
	
	}

}