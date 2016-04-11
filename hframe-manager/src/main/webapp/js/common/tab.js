function addTab_Sys(id,title,url,width,height){
	
	if(width == null ){
	 	width = 1000;
	}
	if(height == null){
		height = 600;
	}
	
	$("#"+id+" .tabContainer .tabHead").append("<li><a href=\"javascript:void(0);\">"+title+"</a><span class=\"deleteTab\"></span></li>");
    $("#"+id+" .tabBody ul").append("<li class=\"tabCot\"> <iframe height='"+height+"' width='"+width+"' src=\""+url+"\" frameborder='0'></iframe></li>");
    $("#"+id+" .tabContainer .tabHead").find("li").eq($("#"+id+" .tabBody ul").find("li").size()-1).click();
}


$(function(){
    //单击tab标题
  	$(".zqh_tab .tabContainer .tabHead li").live("click",function(){
  		if($(this).attr("class")!='currentBtn'){
   		var curIndex=$(this).parent().children("li").index($(this));
   		$(".zqh_tab .tabContainer .tabHead .currentBtn").removeClass("currentBtn");
   		
   		$(this).addClass("currentBtn");

   		$(".zqh_tab .tabBody .currentTabCot").attr("class","tabCot");

   		$(".zqh_tab .tabBody ul li").eq(curIndex).attr("class","currentTabCot");
  		}
  	});
    //打击tab标题删除按钮
  	$(".deleteTab").live("click",function(){
 		if($(this).parent().attr("class")=="currentBtn"){
    	//$(this).parent().prev().attr("class","currentBtn");
    		$(this).parent().prev().click();
    	}
    	var curIndex=$(this).parent().parent().children("li").index($(this).parent());
		$(this).parent().parent().parent().next().find("ul").children("li").eq(curIndex).remove();
    	$(this).parent().remove();
    });
    
    //单击tab标题旁添加按钮
	$(".addTab").click(function(){
	
		try {
			addTab();
		}catch(e) {
    	$(this).prev().append("<li><a href=\"javascript:void(0);\">this is title</a><span class=\"deleteTab\"></span></li>");
    	$(this).parent().next().find("ul").append("<li class=\"tabCot\"> <p>this is content ...</p></li>");
    	$(this).prev().find("li").eq($(this).prev().find("li").size()-1).click();
		}
		
    });
});
            
