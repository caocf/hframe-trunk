
var _layOut=new LayOut();

function changeCurTagObj(id){

	_layOut.changeCurTagObjByTagId(id);
	refreshCurTagObj();
	
}

function deleteTagObj(id){
	document.getElementById("TAGDIV_"+id).outerHTML="";
	_layOut.deleteTagObj(id);
	
	refreshCurTagObj();
	refreshTagObjContent();

}

function refreshTagObjContent(){
	document.getElementById("tagObjContent").innerHTML=_layOut.toString();

}

function refreshCurTagObj(){

	var tagObj=_layOut.getCurTagObject();
	if(tagObj==null){
		$(".tagAttrDiv").hide();
		return;
	}
	var type=tagObj.getType();
	var id=tagObj.getId();
	var title=tagObj.getTitle();
	$(".tagAttrDiv").hide();
	$("#tagAttrDiv #"+type+"Div").show();
	$("#tagAttrDiv #"+type+"Div .tag_Id").val(id);
	$("#tagAttrDiv #"+type+"Div .tag_Title").val(title);
	alert($("#tagAttrDiv #"+type+"Div .tag_View").val());
	$("#tagAttrDiv #"+type+"Div .tag_View").val("diary");
	alert($("#tagAttrDiv #"+type+"Div .tag_View").val());
	alert("ok");
	//return;
//	alert($("#tagAttrDiv #"+type+"Div #tag_Id"));
//	alert($("#tagAttrDiv #"+type+"Div #tag_Id").val());
//	alert(2);
	for(var p in tagObj){
		 if(typeof(tagObj[p])!="function"){
		 		var tagId="tag_"+p.substring(0,1).toUpperCase()+p.substring(1);
		 		if(tagId == "tag_View") {
		 		 	$("#tagAttrDiv #"+type+"Div ."+tagId).val(tagObj[p]);
		 		}else{
		 			$("#tagAttrDiv #"+type+"Div ."+tagId).val(tagObj[p]);
		 		
		 		}
		 		
				//alert(p+"--------"+tagObj[p]);				    
	      }
	}
//	alert(3);
	if(document.getElementById("TAGDIV_"+id) != null) {
		setFocusObjProperties(document.getElementById("TAGDIV_"+id));
	}
//	alert(4);
}

function  LayOut(){

	this.idCount=0;
	this.curTagObject;

	this.tagObjects=new Array();
	
	this.getTagObjects=function(){
		return this.tagObjects;
	}
	
	this.getCurTagObject=function(){
		return this.curTagObject;
	}
	
	this.setCurTagObject=function(obj){
		this.curTagObject=obj;
	}
	
	
	this.addTagObject=function(obj){
		this.tagObjects.push(obj);
		this.setCurTagObject(obj);
	}
	
	this.getIdCount=function(){
		return ++this.idCount;
	}
	
	this.changeCurTagObjByTagId=function(id){
		for(var j=0;j<this.tagObjects.length;j++){
				var tagObject=this.tagObjects[j];
				if(tagObject.getId()==id){
					this.setCurTagObject(tagObject);
					return;
				}
		}		
	}
	
	this.deleteTagObj=function(id){
		for(var j=0;j<this.tagObjects.length;j++){
				var tagObject=this.tagObjects[j];
				if(tagObject.getId()==id){
					this.tagObjects.splice(j,1);
					break;
				}
		}
		
		if(this.tagObjects.length>0){
			this.setCurTagObject(this.tagObjects[0]);
		}else{
			this.setCurTagObject(null);
		
		}
	}
	
	this.toString=function(){
		var result="";	
		for(var i=0;i<this.tagObjects.length;i++){
			var tObject=this.tagObjects[i];
			result+=tObject.toString()+"<br/>";
		}
		
		return result;
	}

	this.toXmlString=function(){
		
		this.xmlObj=new XMLObj();
		var xmlDoc=this.xmlObj.createXMLDoc();
		
		var rootNode=this.xmlObj.createNode(xmlDoc,xmlDoc,"LayOut");
		this.xmlObj.createAttr(xmlDoc,rootNode,"idCount",this.idCount);
		
		if(this.tagObjects.length<=0){
			return "";
		}
		
		var tagObj;
		var tagObjNode;
		for(var i=0;i<this.tagObjects.length;i++){
			tagObj=this.tagObjects[i];
		
			tagObjNode=this.xmlObj.createNode(xmlDoc,rootNode,"TagObj");
			for(var p in tagObj){
				 	if(typeof(tagObj[p])!="function"){
				 		this.xmlObj.createAttr(xmlDoc,tagObjNode,p,tagObj[p]);
			      }
			}
		}
		alert(xmlDoc.xml);
		
		return xmlDoc.xml;
	}

	this.loadXmlToObj=function(xmlStr){
		this.tagObjects=new Array();
		
		this.xmlObj=new XMLObj();
		var xmlDoc=this.xmlObj.loadXMLFromStr(xmlStr);
		
		var rootNode=xmlDoc.getElementsByTagName("LayOut")[0];
		this.idCount=rootNode.getAttribute('idCount');
		//alert(xmlDoc.xml+"!"+rootNode+"!"+this.idCount);
		
		var TagObjNodes=xmlDoc.getElementsByTagName("LayOut/TagObj");
		
		for(var i=0;i<TagObjNodes.length;i++){
			
			var newObj=new TagObj();
			for(var j=0;j<TagObjNodes[i].attributes.length;j++){
//				alert(TagObjNodes[i].attributes[j].name+TagObjNodes[i].attributes[j].value);
				newObj[TagObjNodes[i].attributes[j].name]=TagObjNodes[i].attributes[j].value;
			}
			this.addTagObject(newObj);
		}
		
		this.transTagToHtmlContent();
	}
	
	this.transHtmlContentToTag=function(){
		
		for(var i=0;i<this.tagObjects.length;i++){
			var tagStr=this.tagObjects[i].transHtmlContentToTag();
			var id="TAGDIV_"+this.tagObjects[i].getId();
			
			//alert(tagStr);
			//alert(id);
			//alert(document.getElementById(id).outerHTML);
			document.getElementById(id).innerHTML=tagStr+"<!--"+document.getElementById(id).innerHTML+"-->";
			//var outStr=document.getElementById(id).outerHTML;
			//document.getElementById(id).outerHTML=outStr.substring(0,outStr.indexOf("<?xml:namespace"))+outStr.substring(outStr.indexOf("<?xml:namespace")+30);
			//alert(outStr.substring(outStr.indexOf("<?xml:namespace")+30));
			alert(document.getElementById(id).innerHTML);
			
			
		}
	}
	
	this.transTagToHtmlContent=function(){
		//alert(1);
		for(var i=0;i<this.tagObjects.length;i++){
			//var tagStr=this.tagObjects[i].transHtmlContentToTag();
			var id="TAGDIV_"+this.tagObjects[i].getId();
			alert("??"+id);
			alert("-->"+document.getElementById(id).outerHTML)
			debugger;
//			if(document.getElementById(id) != null) {
//				var str=document.getElementById(id).innerHTML;
//				str=str.substring(str.indexOf("<!--")+4,str.indexOf("-->"));
//				document.getElementById(id).innerHTML=str;
//			}else{
				_layOut.changeCurTagObjByTagId(this.tagObjects[i].getId());
				var type = this.tagObjects[i].getType();
				//alert("bigen....");
					$(".tagAttrDiv").hide();
					$("#tagAttrDiv #"+type+"Div").show();
					//$("#myformDiv").show();
					//alert(document.getElementById("myformDiv").style.display);
					alert(document.getElementById("myformDiv").innerHTML);
					//document.getElementById("myformDiv").style.display = '';
				//alert("secend...");
				refreshCurTagObj();
				//$(".tag_OK").click();
				var param="";
				var tableContent=$(".tagAttrDiv").each(function(){
					if($(this).css("display") != "none"){
						var tableContent = $(this);
						tableContent.find("input[class!=tag_OK][class!=tag_Refresh] , select").each(function(){
							param+=$(this).attr("class")+"="+$(this).val()+"&";
						});
						tableContent.find("input[class=tag_Refresh]").show();
						$("#tagObjContent").html(_layOut.toString());
						alert(id+"1111"+param);
						alert(tableContent.find("input[class=tag_Id]").val());
						$.ajax({
							type:"post",
							url:getSYSTEMPATH()+"/core/core_page_getTagContent?"+param,
							success:function(msg){
							//	alert(msg);
								alert("--<"+document.getElementById(id).outerHTML);
								document.getElementById(id).innerHTML=msg;
								//clickTool("background_tag_refresh","",msg);
							}
						});
					}
				});
	//		}
			
		}
	}
	
	this.getDynObj=function(){
	
		if(this.tagObjects==null){
			return new Array();
		}
	
		for(var i=0;i<this.tagObjects.length;i++){
		
			var type=this.tagObjects[i].getType();
			
			var arr=new Array();
			
			if(type=="myform"){
				var view =this.tagObjects[i].getView();
				
				var dynObj=new DynObj();
				dynObj.setView(view);
				arr.push(dynObj);
			
			}else if(type=="mygrid"){
			
				var view =this.tagObjects[i].getView();
				
				var dynObj=new DynObj();
				dynObj.setView(view);
				arr.push(dynObj);
				
			
			}else if(type=="mylist"){
				var view =this.tagObjects[i].getView();
				
				var dynObj=new DynObj();
				dynObj.setView(view);
				dynObj.setMethod("list");
				arr.push(dynObj);
			
			}
			
		}
		
		return arr;
	
	}
}

function TagObj(){
	/*
	通用属性
	*/
	this.id;
	this.type;//frame
	this.width;
	this.height;
	this.title;
	this.view;

	this.condition;
	this.objects;//暂缺
	this.checkbox;
	this.editable;
	this.initial;
	
	
 	/*
 	FrameTag的属性
 	*/
	this.src;
	this.style;
	this.isframe;
	
	/*
 	MenuTag的属性
 	*/
	this.canClick;
	this.direction;

	/*
 	TabTag的属性
 	*/	
	this.tabGroup;
	
	/*
 	DTreeTag的属性
 	*/
	this.rootId;
	
	/*
 	AutoFormTag的属性
 	*/
 	this.colNum;
 	
 	this.getId=function(){
 		return this.id;
 	}
 	
 	this.setId=function(id){
 		this.id=id;
 	}
 	
 	this.getType=function(){
 		return this.type;
 	}
 	
 	this.setType=function(type){
 		this.type=type;
 	}
 	
 	this.getWidth=function(){
 		return this.width;
 	}
 	
 	this.setWidth=function(width){
 		this.width=width;
 	}
 	
 	this.getHeight=function(){
 		return this.height;
 	}
 	
 	this.setHeight=function(height){
 		this.height=height;
 	}
 	
 	this.getTitle=function(){
 		return this.title;
 	}
 	
 	this.setTitle=function(title){
 		this.title=title;
 	}
 	
 	this.getView=function(){
 		return this.view;
 	}
 	
 	this.setView=function(view){
 		this.view=view;
 	}
	
	this.getCondition=function(){
		return this.condition;
	} 	
	
	this.setCondition=function(condition){
		this.condition=condition;
	}
	
	this.getObjects=function(){
		return this.objects;
	}
	
	this.setObjects=function(objects){
		this.objects=objects;
	}
	
	this.getCheckbox=function(){
		return this.checkbox;
	}
	
	this.setCheckbox=function(checkbox){
		this.checkbox=checkbox;
	}
	
	this.getEditable=function(){
		return this.editable;
	}
	
	this.setEditable=function(editable){
		this.editable=editable;
	}
	
	this.getInitial=function(){
		return this.initial;
	}
	
	this.setInitial=function(initial){
		this.initial=initial;
	}
	
	this.getSrc=function(){
		return this.src;
	}
	
	this.setSrc=function(src){
		this.src=src;
	}
	
	this.getStyle=function(){
		return this.style;
	}
	
	this.setStyle=function(style){
		this.style=style;
	}
	
	this.getIsframe=function(){
		return this.isframe;
	}
	
	this.setIsframe=function(isframe){
		this.isframe=isframe;
	}
	
	this.getCanClick=function(){
		return this.canClick;
	}
	
	this.setCanClick=function(canClick){
		this.canClick=canClick;
	}
	
	this.getDirection=function(){
		return this.direction;
	}
	
	this.setDirection=function(direction){
		this.direction=direction;
	}
	
	this.getTabGroup=function(){
		return this.tabGroup;
	}
	
	this.setTabGroup=function(tabGroup){
		this.tabGroup=tabGroup;
	}
	
	this.getRootId=function(){
		return this.rootId;
	}
	
	this.setRootId=function(rootId){
		this.rootId=rootId;
	}
	
	this.getColNum=function(){
		return this.colNum;
	}
	
	this.setColNum=function(colNum){
		this.colNum=colNum;
	}
	
	
	this.toString=function(){
		return this.id+"<a href=javascript:changeCurTagObj('"+this.id+"');>"+this.type+"  "+this.title +"</a>&nbsp;<a href=javascript:deleteTagObj('"+this.id+"');><font color=red>X</font></a>";
		
		/*
		for(var p in this){
					 if(typeof(this[p]=="function"){
				             this[p]();
				      }else{
				             alert(this[p]);
				      }
		}
		
		*/
	}
	
	this.transHtmlContentToTag=function(){
	
		var tagStr="<zqh:"+this.type;

		for(var p in this){
			 if(typeof(this[p])!="function"&&this[p]!=null){
			 
			 	if(p=='type'){
					continue;
			 	}
			 	if(p=='id'){
			 	
			 		if(this.type=='dtree'){
			 			tagStr=tagStr+" treeId=\""+this[p]+"\"";			 	
			 		}else if(this.type=='myform'){
			 			tagStr=tagStr+" formid=\""+this[p]+"\"";			 	
			 		}else if(this.type=='mygrid'){
			 			tagStr=tagStr+" gridId=\""+this[p]+"\"";			 	
			 		}else if(this.type=='mylist'){
			 			tagStr=tagStr+" listId=\""+this[p]+"\"";			 	
			 		}else{
				 		tagStr=tagStr+" "+this.type+"Id=\""+this[p]+"\"";			 	
			 		}
			 		
			 		
			 	
			 	}else if(p=='title'){
			 		tagStr=tagStr+" title|ZQHFLAG=\""+this[p]+"\"";			 	
			 	
			 	}else if(p=='style'){
			 		tagStr=tagStr+" style|ZQHFLAG=\""+this[p]+"\"";			 	
			 	
			 	}else{
			 		tagStr=tagStr+" "+p+"=\""+this[p]+"\"";			 	
			 	}
			 	
		      }
		}
		
		 
		
		tagStr+=" ></zqh:"+this.type+">";
	
		return tagStr;
	}
	
}


function XMLObj(){
	var xmlDoc;

	this.createXMLDoc=function(){
		
		if(window.ActiveXObject){
			this.xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		}else if (document.implementation&&document.implementation.createDocument)
	    {
	        this.xmlDoc= document.implementation.createDocument('', '', null);
	    }
	    else
	    {
	        return null;
	    }
	    return this.xmlDoc;
	
	},
	
	this.loadXMLFromStr=function(_string){
			
		
		if(window.DOMParser){
			var p=new DOMParser();
			 this.xmlDoc=p.parseFromString(_string,'text/xml');
			 return this.xmlDoc;
		
		}else if(window.ActiveXObject){
			//this.xmlDoc=new ActiveXObject("Msxml2.DOMDocument");
			this.xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
			this.xmlDoc.loadXML(_string);
			//this.xmlDoc.loadXML("<LAYOUT idCount=\"1\"><TAGOBJ id='frame1' title='默认标题1' isframe=\"false\" src=\"fdasfd\" height=\"200\" width=\"400\" type=\"frame\" /></LAYOUT>");
			return  this.xmlDoc;
		}else{
			alert("you browser don't support this xml read!");
			return false;
		}
	},
	
	

	this.loadXMLFromFile=function(xmlFile){
		if(window.ActiveXObject){
			 this.xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
			 this.xmlDoc.async= false;
	         this.xmlDoc.load(xmlFile);
	         return  this.xmlDoc;
		}else if (document.implementation&&document.implementation.createDocument)
	    {
	         this.xmlDoc= document.implementation.createDocument('', '', null);
	         this.xmlDocxmlDoc.load(xmlFile);
	         return   this.xmlDoc;
	    }
	    else
	    {
	    	alert("you browser don't support this xml read!");
	        return null;
	    }
	    
	},
	
	this.createNode=function(_xmlDoc,_curNode,_name,_text){
	
	
	
		//创建根节点
		var newNode=_xmlDoc.createNode(1,_name,"");
		_curNode.appendChild(newNode);
		
		if(_text!=null){
			newNode.text=_text;
		}
		
		return newNode;
	},
	
	this.createAttr=function(_xmlDoc,_curNode,_attName,_attValue){
		
	
		var	r=_xmlDoc.createAttribute(_attName);
		r.value=_attValue;
		_curNode.setAttributeNode(r);
	}
}

function DynObj(){

	this.view;
	this.method;
	
	
	this.getView=function(){
		return this.view;
	}
	this.setView=function(view){
		this.view=view;
	}
	
	this.getMethod=function(){
		return this.method;
	}
	
	this.setMethod=function(method){
		this.method=method;
	}
}