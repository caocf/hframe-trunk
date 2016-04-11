function tdDoUp(obj){
  	
  	var object =$(obj);
  	var curContent=object.parent().parent().html();
  	var pervContent=object.parent().parent().prev("tr").html();
  	var perObj=object.parent().parent().prev("tr");
  	
  	object.parent().parent().html(pervContent);
  	perObj.html(curContent);
  	
  	
  }
  
  function tdDoDown(obj){
  	
  	var object =$(obj);
  	var curContent=object.parent().parent().html();
  	var nextContent=object.parent().parent().next("tr").html();

  	var nextObj=object.parent().parent().next("tr");
  	object.parent().parent().html(nextContent);
  	  	
  	nextObj.html(curContent);
  	
  }
  
  function tdDoDel(obj){
  	var object =$(obj);
  	object.parent().parent().remove();
  }
  
  function showControlTd(obj){
    	var object =$(obj);
  		object.find(".controlTd").css("visibility","visible");
  	
  }
  
  function hiddenControlTd(obj){
  		var object =$(obj);
  		object.find(".controlTd").css("visibility","hidden");
  
  }