<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

	.zqh_list{
		padding: 3px;
	}
	
	.zqh_list td{
		padding: 5px;
	}
	
	.zqh_list_title {
	
		padding: 5px;
		font-size: 20px;
		text-align: center;
	}
	
	.zqh_list_field {
	
		padding: 5px;
		font-size: 20px;
	}
	
	.zqh_list_pagination {
	
		padding: 3px;
		text-align: right;
	}
	
	.zqh_list_footer {
	
		padding: 3px;
		text-align: center;
	}
</style>

		<!-- 引入bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</head>

	<body>
  		<h2 class="sub-header">展示方式列表</h2>
		<table class="zqh_list table table-striped" id="222" style="width: 800px;">
			<thead>
				<tr>
					<th class="zqh_list_field">
					<input id="222_TAGINFO" name="222_TAGINFO" type="hidden"
				value="tag_Type=mylist&amp;tag_Id=222&amp;tag_Width=null&amp;tag_Height=null&amp;tag_Title=展示方式列表&amp;tag_View=SYS_SHOW_TYPE_LIST&amp;tag_Editable=null&amp;tag_Initial=null&amp;tag_Condition=1=1&amp;tag_DefaultBtn=">
					
						全选
						<input type="checkbox" onclick="allSelect_Sys(this)">
					</th>
					<th class="zqh_list_field">
						类型
					</th>
					<th class="zqh_list_field">
						前缀
					</th>
					<th class="zqh_list_field">
						后缀
					</th>
					<th class="zqh_list_field">
						枚举值组
					</th>
					<th class="zqh_list_field">
						引用元素
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="1">
					</td>
					<td style="">
						input
					</td>
					<td style=""></td>
					<td style=""></td>
					<td style=""></td>
					<td style=""></td>
				</tr>
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="10">
					</td>
					<td style="">
						input
					</td>
					<td style=""></td>
					<td style=""></td>
					<td style=""></td>
					<td style=""></td>
				</tr>
				
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="17">
					</td>
					<td style="">
						openwin
					</td>
					<td style="">

					</td>
					<td style="">
					</td>
					<td style="">
					</td>
					<td style="">
						3
					</td>
				</tr>
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="2">
					</td>
					<td style="">
						select
					</td>
					<td style="">
					</td>
					<td style="">
					</td>
					<td style="">
						男,女
					</td>
					<td style="">
					</td>
				</tr>
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="29">
					</td>
					<td style="">
						openwin
					</td>
					<td style="">
					</td>
					<td style="">
					</td>
					<td style="">
					</td>
					<td style="">
						4
					</td>
				</tr>
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="1">
					</td>
					<td style="">
						input
					</td>
					<td style=""></td>
					<td style=""></td>
					<td style=""></td>
					<td style=""></td>
				</tr>
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="17">
					</td>
					<td style="">
						openwin
					</td>
					<td style="">

					</td>
					<td style="">
					</td>
					<td style="">
					</td>
					<td style="">
						3
					</td>
				</tr>
				<tr>
					<td style="">
						<input name="222_INPUT" type="checkbox" value="17">
					</td>
					<td style="">
						openwin
					</td>
					<td style="">

					</td>
					<td style="">
					</td>
					<td style="">
					</td>
					<td style="">
						3
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan=100% style="text-align:center;">
						<div style="float:left;">共19条记录,每页
						  <span style="color: #FF0000" mce_style="color:#FF0000">10</span>条第
						  <span style="color: #FF0000" mce_style="color:#FF0000">1</span>页/共2页</div>
						  <div style="float:right;">
						<nav>
						  <ul class="pagination"  style = "margin:0px;">
						    <li>
						      <a href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <li><a href="javascript:refreshList('222',2,10);">1</a></li>
						    <li><a href="javascript:refreshList('222',2,10);">2</a></li>
						    <li><a href="javascript:refreshList('222',2,10);">3</a></li>
						    <li><a href="javascript:refreshList('222',2,10);">4</a></li>
						    <li><a href="javascript:refreshList('222',2,10);">5</a></li>
						    <li>
						      <a href="javascript:refreshList('222',2,10);" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>	
						</div>
					</td>
				</tr>
				<tr>
					<td class="zqh_list_footer" colspan="100%">
						<button type="button" id="1111" class="btn btn-default "
							onclick="btnClk_Sys(this,'openwin-create','test/createDialog.jsp?VIEW=core_show_type&amp;TYPE=FORM&amp;CONDITION=&amp;TITLE=展示方式',0,'{param=width:400;height:300&amp;isAjax=null&amp;priv=null&amp;targetObj=&amp;targetType=null}')">
							新建
						</button>
						&nbsp;&nbsp;&nbsp;
						<button type="button" id="1121" class="btn btn-default "
							onclick="btnClk_Sys(this,'openwin-update','test/createDialog.jsp?VIEW=core_show_type&amp;TYPE=FORM&amp;CONDITION=&amp;TITLE=展示方式',0,'{param=width:400;height:300&amp;isAjax=null&amp;priv=null&amp;targetObj=&amp;targetType=null}')">
							修改
						</button>
						&nbsp;&nbsp;&nbsp;
						<button type="button" id="1311" class="btn btn-default "
							onclick="btnClk_Sys(this,'action-delete','111',0,'{param=null&amp;isAjax=null&amp;priv=null&amp;targetObj=&amp;targetType=null}')">
							删除
						</button>
					</td>
				</tr>
			</tfoot>

		</table>
	</body>
</html>
