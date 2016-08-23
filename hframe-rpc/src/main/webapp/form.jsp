<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'form.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.6.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	
	
	.zqh_form{
	}
	
	.zqh_form td{
	}
	
	.zqh_form_title {
	
		text-align:left;
		background-color: #999;
		padding: 5px;
		font-size: 20px;
		background-image: url("../images/bg.gif");
	}
	
	.zqh_form_footer {
	
		background-color: #ccc;
		padding: 3px;
		text-align: center;
	}
	
	
	.zqh_form_td_name {
		text-align:right;
	}
	
	.zqh_form_td_value {
	}
	
	
	.zqh_grid{
		border: 1px solid blue;
		padding: 3px;
	}
	
	.zqh_grid td{
		padding: 5px;
		background-color: #eee;
	}
	
	.zqh_grid_title {
	
		background-color: #999;
		padding: 5px;
		font-size: 20px;
	}
	
	.zqh_grid_column {
	
		background-color: #CCC;
		padding: 5px;
		font-size: 20px;
	}
	
	.zqh_grid_footer {
	
		background-color: #ccc;
		padding: 3px;
		text-align: right;
	}
	
	#userGridDiv{
		overflow: scroll;
		width: 1000px;
	}
	</style>
	
	
	<script type="text/javascript">
	
	
	$(function(){
		
	$("#run").click(function(){
	    $(this)
      .animate({ left: -10 })
      .animate({ left: 10 })
      .animate({ left: -10 })
      .animate({ left: 10 })
      .animate({ left: 0 });
      
      alert($("form").serialize());
     
       var filedsAttr=$("form").serializeArray();
       
       $.each(filedsAttr,function(i, filed){
       	
       		alert(filed.name+"="+filed.value);
       
       });
      
      

	}); 
	
	
	
	});
	
	
	</script>
	  <!-- 引入bootstrap -->
	  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	  <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	  <script src="bootstrap/js/bootstrap.min.js"></script>
  </head>
  
  <body>
  
  
  
<div class="panel panel-default" style="width:;">
  <div class="panel-heading">
    <h3 class="panel-title">客户资料填写</h3>
  </div>
  <div class="">
     <form class="form-inline" >
		<table class='zqh_form table table-bordered' id='user_form' >
			<tbody>
				<tr>
						<td  class='zqh_form_td_name ' style='width:0'>
							<label for="ID">编号</label>
	    				</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>
							<input type="text" class="form-control" id="ID" placeholder="ID">
						</td>
						<td  class='zqh_form_td_name ' style='width:0'>
							<label for="NAME">姓名</label>
	    				</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>
							<input type="text" class="form-control" id="NAME" placeholder="Name">
						</td>
				</tr>
				<tr>
						<td  class='zqh_form_td_name ' style='width:0'>
							<label for="Gender">性别</label>
	    				</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>
							<div class="radio">
							    <label>
							      <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>男
							    </label>
							    <label>
							      <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2"> 女
							    </label>
							  </div>
							 
						</td>
						
						<td  class='zqh_form_td_name ' style='width:0'> 
							<label for="BirthDate">出生日期</label>
	    				</td>
						<td colspan=1  class='zqh_form_td_value' style='width:200'>
							<div class="form-group form-inline">
								<select name='BirthDate' class="form-control" style=''>
									<option  value=1987>1987</option>
									<option  value=1988>1988</option>
								</select>年
								<select name='BirthDate' class="form-control" style=''>
									<option  value=1>1</option>
									<option  value=2>2</option>
								</select>月
								<select name='BirthDate' class="form-control" style=''>
									<option  value=1>1</option>
									<option  value=2>2</option>
								</select>日
							</div>
						</td>
					</tr>
					<tr>
						<td  class='zqh_form_td_name ' style='width:0'>
							<label for="Addr">地址</label>
	    				</td>
						<td colspan=3  class='zqh_form_td_value' style='width:0'>
							<input type="text" class="form-control" id="Addr" placeholder="四川。成都" >
 						</td>
					</tr>
					<tr>
						<td  class='zqh_form_td_name ' style='width:0'>
							 <label for="Developer">发展人</label>
	    				</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>
						<div class="form-group form-inline">
							<input type="text" class="form-control" id="Developer" placeholder="张三" disabled>
							<span class="glyphicon glyphicon-search" aria-hidden="true" onClick='selectDeveloper();' style='cursor:hand;'></span>
						</div>
						</td>
						<td  class='zqh_form_td_name ' style='width:0'>
							 <label for="price">名词</label>
	    				</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>
						<div class="form-group form-inline">
							第<input type="text" class="form-control" id="price" placeholder="">名
						</div>
 						</td>
					</tr>
					<tr>
						<td  class='zqh_form_td_name' style='width:0'>
							<label for="price">爱好</label>
		   				</td>
						<td colspan=3  class='zqh_form_td_value' style='width:200'>
							<div class="checkbox">
							    <label>
							      <input name='爱好' type="checkbox"> 篮球
							    </label>
							    <label>
							      <input name='爱好' type="checkbox"> 足球
							    </label>
							    <label>
							      <input name='爱好' type="checkbox"> 读书
							    </label>
							  </div>
						</td>
					</tr>
			</tbody>		
		</table>
</form>

  </div>
  <div class="panel-footer" style="text-align:center;">
<button type="button" id="111" class="btn btn-default" onclick="btnClk_Sys(this,'openwin-update','test/createDialog.jsp',0,'{param=null&amp;isAjax=null&amp;priv=null&amp;targetObj=&amp;targetType=null}')">修改</button>
  </div>
</div>

  <h2 class="sub-header">Section title</h2>
    
 <form action="" method="post" id="user_form_id">
<table class='zqh_form' id='user_form'>
			<thead>
				<tr>
					<td colspan=100% class='zqh_form_title'>hahahh </td>
				</tr>
			</thead>
			<tbody>
				<tr>
						<td  class='zqh_form_td_name' style='width:0'>【1】;ID;${value}-->$oldObjectValue.get(${column.Name})</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'><input name='ID' style=''/></td>
						<td  class='zqh_form_td_name' style='width:0'>【2】;Name;${value}-->$oldObjectValue.get(${column.Name})</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'><input name='Name' style='' /></td>
				</tr>
				<tr>
						<td  class='zqh_form_td_name' style='width:0'>【3】;Gender;1-->1</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>
								<select name='Gender'  style='' >
										<option  value=1>男</option>
										<option  value=0>女</option>
								</select>
						</td>
						<td  class='zqh_form_td_name' style='width:0'>【4】;BirthDate;1-->$oldObjectValue.get(${column.Name})</td>
						<td colspan=1  class='zqh_form_td_value' style='width:200'>
							<select name='BirthDate' style=''>
								<option  value=1987>1987</option>
								<option  value=1988>1988</option>
							</select>年
							<select name='BirthDate' style=''>
								<option  value=1>1</option>
								<option  value=2>2</option>
							</select>月
							<select name='BirthDate' style=''>
								<option  value=1>1</option>
								<option  value=2>2</option>
							</select>月
						</td>
					</tr>
					<tr>
						<td  class='zqh_form_td_name' style='width:0'>【5】;Addr;四川。成都-->四川。成都</td>
						<td colspan=3  class='zqh_form_td_value' style='width:0'>
							<input name='Addr' style='width:450'  value='四川。成都'	/>
 						</td>
					</tr>
					<tr>
						<td  class='zqh_form_td_name' style='width:0'>【6】;Developer;四川。成都-->$oldObjectValue.get(${column.Name})</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>
							<input name='Developer' disabled='disabled' style='' /><img id='selectOrgnize' border='0' src='/zqhFrame_part1/image/query.gif' onClick='selectDeveloper();' align='absmiddle' style='cursor:hand;'/>
 					</td>
						<td  class='zqh_form_td_name' style='width:0'>【7】;price;四川。成都-->$oldObjectValue.get(${column.Name})</td>
						<td colspan=1  class='zqh_form_td_value' style='width:0'>第<input name='price' style='width:40' />名
 						</td>
					</tr>
					<tr>
						<td  class='zqh_form_td_name' style='width:0'>【8】;爱好;四川。成都-->$oldObjectValue.get(${column.Name})</td>
						<td colspan=1  class='zqh_form_td_value' style='width:200'>
							<input name='爱好' type='checkbox' style=''/>
							篮球
							<input name='爱好' type='checkbox' style=''/>
							足球
							<input name='爱好' type='checkbox' style=''/>
							读书
						</td>
					</tr>
			</tbody>		
			<tfoot>
				<tr>
					<td class='zqh_form_footer' colspan=100%><button id="run">Run</button><div>11</div> <input class="zqh_form_submit" type="submit" name="提交"> </td>
				</tr>
			</tfoot>		
		</table>
</form>




  </body>
</html>
