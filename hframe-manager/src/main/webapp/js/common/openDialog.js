	//弹出框函数
	function showMyDialog_Sys(src,width,height,refreshFlag,obj){
		if(width==null){
			width=300;
		}
		if(height==null){
			height=500;
		}
		var ret = window.showModalDialog(getSYSTEMPATH()+"/"+src,obj,
					"scroll:yes;help:no;resizable:yes;status:no;dialogHeight:"
					+height+"px;dialogWidth:"+width+"px");
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
	function magnifier_Sys(thisObj,src,param,targetObj,width,height){
		//提交给弹出框的参数
		var transObj=new Object();
		if(param!=null){
			param=param.substring(1,param.length-1);
			var params=param.split("&");
			for(var i=0;i<params.length;i++){
				var key=params[i].split("=")[0];
				var value=params[i].split("=")[1];
				if(key=='SCHEMA_MODE'){
					SCHEMA_MODE=value;
				}
				eval("transObj."+key+" = '"+value+"'");
			}
		}
		
		var resObjStr=showMyDialog_Sys(src,width,height,SCHEMA_MODE,transObj);
		var resObj=string2Array(resObjStr);
		if(resObj!=null){
			if(resObj.length>0){
			 	var curObj=thisObj.previousSibling;
				for(var i=(resObj[0].length-1);i>-1;i--){
					curObj.value=resObj[0][i];
					curObj=curObj.previousSibling;
				}
			}		
			//thisObj.previousSibling.value=resObj[0][0];
		}
	}
 		
 	var transObj=null;
	var retObj=new Array();
	var SCHEMA_MODE=0;
	
	function array2String(resObj){
		var str;
		if(resObj!=null){
			if(resObj.length>0){
			    str="";
				for(var m=0;m<resObj[0].length;m++){
					if(m!=0){
						str+=',';
					}
					str+=resObj[0][m];
				}
				 str+="|$$|";
			}		
		}
		return str;
	}
	
	function string2Array(str){
		
		var obj=new Array();
		var entity=new Array();
		if(str==null){
			return obj;
		}
		
		var params = str.split("|$$|");
		for(var i=0;i<params.length;i++){
			if(params[i]!=null&&params[i]!=""){
				var vars=params[i].split(",");
				for(var j=0;j<vars.length;j++){
					entity.push(vars[j]);
				}
			}
		}
		obj.push(entity);
		
		return obj;
	}
	
	function load(){
		transObj=window.dialogArguments;
		for(var i in transObj){
			if(i=='SCHEMA_MODE'){
				SCHEMA_MODE=transObj[i];
			}
			eval(" var "+i+" = '"+transObj[i]+"' ;");
		}
	}
	
	function clearRetObj(){
		retObj=new Array();
	}

	function  pushToReturnVal_Sys(val1,val2,val3,val4,val5,val6,val7,val8,val9,val10){
		var entity=new Array();
		for(var i=1;i<=10;i++){
			eval("if(val"+i+"!=null){ entity.push(val"+i+");}");
		}
		retObj.push(entity);
	}

   	function returnVal_Sys(){
   		window.returnValue=array2String(retObj);
		window.close();
   	}
   	
   	function cancel_Sys(){
		window.returnValue=null;
		window.close();	   	
	}