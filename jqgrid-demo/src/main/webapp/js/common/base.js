


function allSelect_Sys(thisObj){

	var checked=$(thisObj).attr("checked");


	var tableBodyObj=$(thisObj).parent().parent().parent();

	if(checked==null||checked=="false"||checked==false){
	 tableBodyObj.find("input[type=checkbox]").removeAttr("checked");
	}else{
		tableBodyObj.find("input[type=checkbox]").attr("checked","true");	
	}
}

function getSelect_Sys(listId){


	var value;
	var text;
	//clearRetObj();
	retObj=new Array();

	if(document.all(listId+"_INPUT").checked){
		value=document.all(listId+"_INPUT").value;
		if(value.indexOf("|")!=-1){
			text=value.substring(value.indexOf("|")+1,value.length);
			value=value.substring(0,value.indexOf("|"));
		}
		if(value!=""){
			pushToReturnVal_Sys(value,text);
		}

	}

	for(i=0;i<document.all(listId+"_INPUT").length;i++){

		if(document.all(listId+"_INPUT")[i].checked){
			value=document.all(listId+"_INPUT")[i].value;
			if(value.indexOf("|")!=-1){
				text=value.substring(value.indexOf("|")+1,value.length);
				value=value.substring(0,value.indexOf("|"));
			}
			if(value!=""){
				pushToReturnVal_Sys(value,text);
			}

		}
	}


	return retObj;
}
function btnClk_Sys(thisObj,model,src,isAjax,param){
		var srcObj;
		var targetObj;
		var targetType;
		var width=400;
		var height=500;
		
		//解析param	
		if(param!=null&&""!=param){
			param=param.substring(1,param.length-1);
			var params=param.split("&");
 				for(var i=0;i<params.length;i++){
 					var key=params[i].split("=")[0];
 					var value=params[i].split("=")[1];
 					
 					 if("srcObj"==key){
 						srcObj=value;
 					}else if("targetObj"==key){
 						targetObj=value;
 					}else if("targetType"==key){
 						targetType=value;
 					}else if("param"==key){
					    var parameter=value;
					    var parameters=parameter.split(";");
					    for(var j=0;j<parameters.length;j++){
					  		var key=parameters[j].split(":")[0];
 							var value=parameters[j].split(":")[1];
 							if("width"==key){
		 						width=value;
		 					}else if("height"==key){
		 						height=value;
		 					}
					    }
					}
 				}
		}
		
		if(model=="action-save"){
			
			if(targetObj==null||targetObj==""){
				var thisformId=$(thisObj).parent().prev().find("form").attr("id");
				document.getElementById(thisformId).submit();
			}else{
				
				if(targetObj.indexOf(",")>0){
					var targetObjs=param.split(",");
					//TODO
				}else{
					document.forms[targetObj+"_FORM"].submit();
				}
				
				
			}
		}else if(model=="action-delete"){
			if(targetObj==null||targetObj==""){
			var selectObj=getSelect_Sys($(".zqh_list").parent().attr("id"));
					//alert(selectObj);
					//TODO
			}else{
				if(targetObj.indexOf(",")>0){
					var targetObjs=param.split(",");
					//TODO
				}else{
					var selectObj=getSelect_Sys(targetObj);
					//alert(selectObj);
				}
			}
			alert("TODO");
			
		}else if(model=="openwin-create"){
			retObj=new Array();
			if(src==null||src==""){
				src="test/createDialog.jsp?VIEW=diary_group&TYPE=FORM&CONDITION=&TITLE=我日";
			}
			magnifier_Sys(this,src,'{user_id=100&user_name=zhangsan&SCHEMA_MODE=1}','',width,height);
				
		}else if(model=="openmodel-create"){
			//alert(targetObj+":"+targetType);
			openModal(targetObj,thisObj);
		}else if(model=="openwin-update"){
			var selectObj;
			if(srcObj==null||srcObj==""){
			 selectObj=getSelect_Sys($(".zqh_list").parent().attr("id"));
					//TODO
			}else{
				if(srcObj.indexOf(",")>0){
					var srcObjs=param.split(",");
					selectObj=srcObjs[0];
				}else{
					 selectObj=getSelect_Sys(srcObj);
				}
			}
			if(src==null||src==""){
				src="test/createDialog.jsp?VIEW=diary_group&TYPE=FORM&CONDITION=&TITLE=我日";
			}
			
			if(selectObj!=null&&selectObj.length>0){
				
				magnifier_Sys(this,src+'&KEY='+selectObj[0][0],'{user_id=100&user_name=zhangsan&SCHEMA_MODE=1}','',width,height);
				
			}else{
				alert("please choose  record  ....");
			}
			
			
		}else if(model=="openmodel-update"){
			var selectObj;
			if(srcObj==null||srcObj==""){
			 selectObj=getSelect_Sys($(".zqh_list").parent().attr("id"));
					//TODO
			}else{
				if(srcObj.indexOf(",")>0){
					var srcObjs=param.split(",");
					selectObj=srcObjs[0];
				}else{
					 selectObj=getSelect_Sys(srcObj);
				}
			}
			if(src==null||src==""){
				src="test/createDialog.jsp?VIEW=diary_group&TYPE=FORM&CONDITION=&TITLE=我日";
			}
			
			if(selectObj!=null&&selectObj.length>0){
				var modelId=$("#"+targetObj).parents(".modal").attr("id");
				refresh_Sys(null,targetObj,'tag_ObjectId='+selectObj[0][0]);
				openModal(modelId,thisObj);
				//magnifier_Sys(this,src+'&KEY='+selectObj[0][0],'{user_id=100&user_name=zhangsan&SCHEMA_MODE=1}','',width,height);
				
			}else{
				alert("please choose  record  ....");
			}
			
			
		}else if(model=="openwin-select"){
			
			
			
		}else if(model=="current-back"){
			
			history.go(-1);
			
		}else if(model=="current-addOneRow"){
			var lastNode=$(thisObj).parents("table").find("tbody tr:last ");
			//lastNode.parent().append(lastNode.clone());
			lastNode.after(lastNode.clone());
			 lastNode=$(thisObj).parents("table").find("tbody tr:last ");
			 $(lastNode).find("input").each(function(){
				$(this).val(""); 		
			 });
		}else if(model=="current-copyOneRow"){
			var lastNode=$(thisObj).parents("table").find("tbody tr:last ");
			//lastNode.parent().append(lastNode.clone());
			lastNode.after(lastNode.clone());
			
		}else if(model=="current-delOneRow"){
			
			if($(thisObj).parent().parent().parent().children().size()<3){
				return ;
			}
			$(thisObj).parent().parent().remove();
			
			
			
		}else if(model=="tree-select"){
			var treeDivObj =FindPreVElement(thisObj, "DIV" ,"dhtmlxTree_zqh");
			 alert(getTreeSel_Sys(eval(treeDivObj.id)));
			return getTreeSel_Sys(eval(treeDivObj.id));
			
		}else if(model=="action-ajax"){
			var thisformId=$(thisObj).parent().prev().find("form").attr("id");
			//var thisform=FindParentElement(thisObj.parentElement,"FORM");
			//alert(thisform.id);
			//var thisformId=thisObj.parentElement.parentElement.parentElement.parentElement.parentElement.id;
			//var thisformId=thisform.id;
			var sendformId;
			if(parameter!=null&&parameter!="null"){
				if(parameter.indexOf("|")>0){
					thisformId=parameter.substring(0,parameter.indexOf("|"));
					sendformId=parameter.substring(parameter.indexOf("|")+1);
					thisformId=thisformId+"_FORM";
				sendformId=sendformId+"_GRID";
				}else{
					sendformId=parameter;
					sendformId=sendformId+"_GRID";
				}
				
				
			}
			ajaxsub_Sys(thisformId,sendformId);
			
		}
					
		
	}
	
	var modelStatCache = "";
	function openModal(id){
		modelStatCache = modelStatCache + "|" + id;
	    $('#'+id).modal({
	      /* keyboard: true */
	    })
	}
	
	function closeModal(id){
	
		if(modelStatCache.indexOf(id)>-1) {
			modelStatCache = modelStatCache.substring(0,modelStatCache.indexOf(id)) 
						   + modelStatCache.substring(modelStatCache.indexOf(id)+id.lenth);
		    $('#'+id).modal({
		      /* keyboard: true */
		    })
		}
		
	
	}
	
	function ajaxsub_Sys(targetObjId,subObjId){
		//alert(targetObjId+"!"+subObjId);
		//targetObjId=$("#"+targetObjId).find("form").attr("id");
		var targStr=$("#"+targetObjId).serialize();
		if(subObjId!=null){
			//subObjId=$("#"+subObjId).find("form").attr("id");
			var subStr=$("#"+subObjId).serialize();
			targStr=targStr+"&"+subStr+"&isCombine=true";
		}
		var action=$("#"+targetObjId).attr("action");
		//alert(action+"\n"+targStr);
		$.ajax({
			type:"post",
			url:action,
			data:targStr,
			success:function(msg){
				alert(msg);
				window.location.reload();
				if(1==SCHEMA_MODE){
					returnVal_Sys();
				}
			}
		});
	}
	
	function trueFalseCheckboxOnClick_Sys(thisObj){
		var checked=$(thisObj).attr("checked");
		//alert(checked+$(thisObj).val());
		
		if($(thisObj.previousSibling).val()==0){
			$(thisObj.previousSibling).val(1);
		}else{
			$(thisObj.previousSibling).val(0);
		}
		
		/*
		if(checked==null||checked=="false"||checked==false){
			$(thisObj).attr("checked","true");
			$(thisObj.previousSibling).val(0);
		}else{
			$(thisObj).removeAttr("checked");
			$(thisObj.previousSibling).val(1);	
		}
		*/
	}
			
