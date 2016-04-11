	function rowUp_Sys(obj){
	  	var object =$(obj);
	  	var curObj=object.parent().parent();
	  	var curContent=object.parent().parent().html();
	  	var pervContent=object.parent().parent().prev("tr").html();
	  	var perObj=object.parent().parent().prev("tr");
	  	if(curObj.attr("class")==perObj.attr("class")){
		  	object.parent().parent().html(pervContent);
		  	perObj.html(curContent);
	  	}
	}
	
	function rowDown_Sys(obj){
		
	var object =$(obj);
	var curObj=object.parent().parent();
  	var curContent=object.parent().parent().html();
  	var nextContent=object.parent().parent().next("tr").html();

  	var nextObj=object.parent().parent().next("tr");
  	
  	if(curObj.attr("class")==nextObj.attr("class")){
	  	object.parent().parent().html(nextContent);
	  	nextObj.html(curContent);
  	}
  	  	
  	
	}