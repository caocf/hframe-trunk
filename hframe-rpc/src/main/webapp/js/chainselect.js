  $(function(){
  
  
    //城市选择的下拉select框++++++++++++++++++++++++++++++++++++
  	$(".provices").html("");
 	$("<option value='0'>-请选择-</option>").appendTo($(".provices"));
 	$("<option value='bj'>北京市</option>").appendTo($(".provices"));
 	$("<option value='sc'>四川省</option>").appendTo($(".provices"));
 	$("<option value='hn'>河南省</option>").appendTo($(".provices"));
 	$("<option value='gd'>广东省</option>").appendTo($(".provices"));
 	$(".citys").html("");
  	$("<option value='0'>-请选择-</option>").appendTo($(".citys"));

  	$(".provices").change(function(){
  		var yourchoose=$(this).val();
  		
  		if("bj"==yourchoose){
  			$(".citys").html("");
  			$("<option value='0'>-请选择-</option>").appendTo($(".citys"));
  			$("<option>海淀区</option>").appendTo($(".citys"));
  			$("<option>宣武区</option>").appendTo($(".citys"));
  			$("<option>朝阳区</option>").appendTo($(".citys"));
  			$("<option>大兴区</option>").appendTo($(".citys"));
  		}else if("sc"==yourchoose){
  			$(".citys").html("");
  			$("<option value='0'>-请选择-</option>").appendTo($(".citys"));
  			$("<option>成都市</option>").appendTo($(".citys"));
  			$("<option>南充市</option>").appendTo($(".citys"));
  			$("<option>绵阳市</option>").appendTo($(".citys"));
  			$("<option>德阳市</option>").appendTo($(".citys"));
  			$("<option>自贡市</option>").appendTo($(".citys"));
  			$("<option>宜宾市</option>").appendTo($(".citys"));
  			$("<option>泸州市</option>").appendTo($(".citys"));
  			$("<option>广元市</option>").appendTo($(".citys"));
  		}else if("gd"==yourchoose){
  			$(".citys").html("");
  			$("<option value='0'>-请选择-</option>").appendTo($(".citys"));
  			$("<option>广州市</option>").appendTo($(".citys"));
  			$("<option>深圳市</option>").appendTo($(".citys"));
  			$("<option>东莞市</option>").appendTo($(".citys"));
  			$("<option>佛山市</option>").appendTo($(".citys"));
  			$("<option>中山市</option>").appendTo($(".citys"));
  		}else if("hn"==yourchoose){
  			$(".citys ").html("");
  			$("<option value='0'>-请选择-</option>").appendTo($(".citys"));
  			$("<option>郑州市</option>").appendTo($(".citys"));
  			$("<option>洛阳市</option>").appendTo($(".citys"));
  			$("<option>开封市</option>").appendTo($(".citys"));
  			$("<option>焦作市</option>").appendTo($(".citys"));
  			$("<option>南阳市</option>").appendTo($(".citys"));
  			$("<option>安阳市</option>").appendTo($(".citys"));
  		}else if("0"==yourchoose){
  			$(".citys").html("");
  			$("<option value='0'>-请选择-</option>").appendTo($(".citys"));
  		}
  	});
  
  
  
 //关于学校的选择信息++++++++++++++++++++++++++++++++++
 	$(".student").html("");
 	$("<option value='0'>-请选择-</option>").appendTo($(".student"));
 	$("<option value='1'>小学生</option>").appendTo($(".student"));
 	$("<option value='2'>中学生</option>").appendTo($(".student"));
 	$("<option value='3'>大学生</option>").appendTo($(".student"));
 	$(".subject").html("");
 	$("<option value='0'>-请选择-</option>").appendTo($(".subject"));
 	$(".grade").html("");
 	$("<option value='0'>-请选择-</option>").appendTo($(".grade"));
 	$("<option value='1'>-一年级-</option>").appendTo($(".grade"));
 	$("<option value='2'>-二年级-</option>").appendTo($(".grade"));
 	$("<option value='3'>-三年级-</option>").appendTo($(".grade"));
 	$("<option value='4'>-四年级-</option>").appendTo($(".grade"));
 	$("<option value='5'>-五年级-</option>").appendTo($(".grade"));
 	
 	
 	
 	$(".student").change(function(){
  		var yourchoose=$(this).val();
  		
  		if("1"==yourchoose){
  			$(".subject").html("");
  			$("<option value='00'>-请选择-</option>").appendTo($(".subject"));
  			$("<option value='01'>语文</option>").appendTo($(".subject"));
  			$("<option value='02'>数学</option>").appendTo($(".subject"));
  			$("<option value='03'>外语</option>").appendTo($(".subject"));
  			$("<option value='04'>算术</option>").appendTo($(".subject"));
  			$("<option value='05'>自然</option>").appendTo($(".subject"));
  			
  		}else if("2"==yourchoose){
  			$(".subject").html("");
  			$("<option value='00'>-请选择-</option>").appendTo($(".subject"));
  			$("<option value='01'>语文</option>").appendTo($(".subject"));
  			$("<option value='02'>数学</option>").appendTo($(".subject"));
  			$("<option value='03'>外语</option>").appendTo($(".subject"));
  			$("<option value='04'>物理</option>").appendTo($(".subject"));
  			$("<option value='05'>化学</option>").appendTo($(".subject"));
  			$("<option value='06'>生物</option>").appendTo($(".subject"));
  			$("<option value='07'>政治</option>").appendTo($(".subject"));
  			$("<option value='08'>历史</option>").appendTo($(".subject"));
  			$("<option value='09'>地理</option>").appendTo($(".subject"));
  			$("<option value='10'>体育</option>").appendTo($(".subject"));
  			$("<option value='11'>音乐</option>").appendTo($(".subject"));
  			
  		}else if("3"==yourchoose){
  		$(".subject").html("");
  			$("<option value='00'>-请选择-</option>").appendTo($(".subject"));
  			$("<option value='01'>大学英语</option>").appendTo($(".subject"));
  			$("<option value='02'>大学语文</option>").appendTo($(".subject"));
  			$("<option value='03'>高等数学</option>").appendTo($(".subject"));
  			$("<option value='04'>大学物理</option>").appendTo($(".subject"));
  			$("<option value='05'>计算机技术-软件</option>").appendTo($(".subject"));
  			$("<option value='06'>计算机技术-硬件</option>").appendTo($(".subject"));
  			$("<option value='07'>计算机技术-网络</option>").appendTo($(".subject"));
  		}else if("0"==yourchoose){
  			$(".citys").html("");
  			$("<option>-请选择-</option>").appendTo($(".citys"));
  		}
  	});
  	
//关于新闻的选择信息++++++++++++++++++++++++++++++++++
	$(".newscontry").html("");
 	$("<option value='0'>-请选择-</option>").appendTo($(".newscontry"));
 	$("<option value='1'>国内</option>").appendTo($(".newscontry"));
 	$("<option value='2'>国际</option>").appendTo($(".newscontry"));
 	$(".newssort").html("");
 	$("<option value='0'>-请选择-</option>").appendTo($(".newssort"));
 	$("<option value='1'>-政治-</option>").appendTo($(".newssort"));
 	$("<option value='2'>-军事-</option>").appendTo($(".newssort"));
 	$("<option value='3'>-娱乐-</option>").appendTo($(".newssort"));
 	$("<option value='4'>-体育-</option>").appendTo($(".newssort"));
 	$("<option value='5'>-其他-</option>").appendTo($(".newssort"));
  	
  });