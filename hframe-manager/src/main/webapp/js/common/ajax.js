
	
	function refreshList(id,pageIndex,pageSize){
	
		var taginfo=document.getElementById(id+"_TAGINFO").value;
		
		taginfo=taginfo+"&pageSize="+pageSize+"&pageIndex="+pageIndex;
	
		$.ajax({
			type:"get",
			url:getSYSTEMPATH()+"/core/core_page_getListContentByAjax?"+taginfo,
			success:function(msg){
				var htmls = msg.split('@@');
				$("#"+id+" tbody").html(htmls[0]);
				$("#"+id+" .zqh_list_pagination").html(htmls[1]);
			}
		});
	}
	
	function refresh_Sys(type,id,param){
		var taginfo=document.getElementById(id+"_TAGINFO").value;
		
		//for tree complent start
		var startpart=taginfo.substring(0,taginfo.indexOf("&tag_Title=")+11);
		var tempart=taginfo.substring(taginfo.indexOf("&tag_Title=")+11);
		var title=tempart.substring(0,tempart.indexOf("&"));
		var endpart=tempart.substring(tempart.indexOf("&"));
		
		taginfo=startpart+encodeURI(encodeURI(title))+endpart;
		//for tree complent end

		if(taginfo.indexOf("tag_Type=select&")>-1){
			refreshSel_Sys(id,param,taginfo);
			return ;
		}
		if(param!=null&&param!=""){
			param=param+"&";
		}

		$.ajax({
			type:"get",
			url:getSYSTEMPATH()+"/core/core_page_refreshTagContent?"+param+taginfo,
			success:function(msg){
				if(taginfo.indexOf("tag_Type=dtree&")>-1){
					eval(msg);
				}else if(taginfo.indexOf("tag_Type=mylist&")>-1||taginfo.indexOf("tag_Type=mygrid&")>-1){
					var tfootBack=$("#"+id+" tfoot").html();
					document.getElementById(id).outerHTML=msg;
					$("#"+id+" tfoot").html(tfootBack);
					
				}else{
					document.getElementById(id).outerHTML=msg;
				}
			}
		});
	}
	function  refreshSel_Sys(id,param,taginfo){
		if(document.getElementById(id).innerHTML!=""){
			if(param==null||param=='') return;
			//alert(document.getElementById(id).outerHTML);
		}
		if(param!=null&&param!=""){
			param="tag_Condition="+param+"&";
		}else{
			param="";
		}
		$.ajax({
			type:"post",
			url:getSYSTEMPATH()+"/core/core_page_refreshTagContent?"+param+taginfo,
			success:function(msg){
				$("#"+id).html(msg);
			}
		});
	}
	
