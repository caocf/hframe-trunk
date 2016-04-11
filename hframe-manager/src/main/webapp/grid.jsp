<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/base.js"></script>
		<style type="text/css">
	.zqh_grid{
		padding: 3px;
	}
	
	.zqh_grid td{
		padding: 5px;
	}
	
	.zqh_grid_title {
	
		background-color: #999;
		padding: 5px;
		font-size: 20px;
		text-align: center;
	}
	
	.zqh_grid_column {
	
		padding: 5px;
		font-size: 20px;
	}
	
	.zqh_grid_footer {
	
		padding: 3px;
		text-align: right;
	}
</style>

		<!-- 引入bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		
	</head>

	<body>
	
	
  <div class="panel panel-default" style="width:px;">
  <div class="panel-heading">
    <h3 class="panel-title">枚举值</h3>
  </div>
  <div class="">
		<table class="zqh_grid table table-striped" id="enumgrid"
			style="">
			<input id="enumgrid_TAGINFO" name="enumgrid_TAGINFO" type="hidden"
				value="tag_Type=mygrid&amp;tag_Id=enumgrid&amp;tag_Width=null&amp;tag_Height=null&amp;tag_Title=枚举值&amp;tag_View=core_enum&amp;tag_Param=null&amp;tag_Editable=true&amp;initial=null&amp;tag_Condition=null&amp;tag_Url=Hframe/coreenum/_coreenum_batchCreate&amp;tag_DefaultBtn=ajax(enumgroupform|enumgrid)">
			<form action="/Hframe/coreenum/_coreenum_batchCreate" method="post"
				id="enumgrid_GRID"></form>
			<thead>
				<tr>
					<input id="GlobalParam" name="GlobalParam" type="hidden" value="/">
					<input id="Param" name="Param" type="hidden" value="/">
					<th class="zqh_grid_column">
						值
					</th>
					<th class="zqh_grid_column">
						显示值
					</th>
					<th class="zqh_grid_column">
						是否默认
					</th>
					<th class="zqh_grid_column">
						枚举值描述
					</th>
					<th class="zqh_grid_column">
						优先级
					</th>
					<th class="zqh_grid_column"></th>
				</tr>
			</thead>
			<tbody>
				<tr class="zqh_grid_tr">
					<input name="coreEnums.coreEnumGroupId" type="hidden" style="">
					<input name="coreEnums.coreEnumId" type="hidden" style="">
					<td style="width: 0">
						<input type="text" class="form-control" id="ID" name="coreEnums.coreEnumValue"  placeholder="ID">
					</td>
					<td style="width: 0">
						<input type="text" class="form-control" id="ID" name="coreEnums.coreEnumDisplayValue" placeholder="ID">
					</td>
					<td style="width: 0">
						<div class="radio">
						    <label>
						      <input type="radio" name="coreEnums.coreEnumDefault"  value="1" checked>是
						    </label>
						    <label>
						      <input type="radio" name="coreEnums.coreEnumDefault"  value="0"> 否
						    </label>
						  </div>
					</td>
					<input name="coreEnums.coreEnumGroupShowType" type="hidden"
						style="">
					<td style="width: 0">
						<input type="text" class="form-control" id="ID" name="coreEnums.coreEnumDesc" placeholder="ID">
					</td>
					<td style="width: 0">
						<input type="text" class="form-control" id="ID" name="coreEnums.coreEnumPri" placeholder="ID">
					</td>
					<input name="coreEnums.ext1" type="hidden" style="">
					<input name="coreEnums.ext2" type="hidden" style="">
					<td>
						<a href="javascript:void(0)"
							onclick="btnClk_Sys(this,'current-delOneRow','test/createDialog.jsp',0,'{param=?&amp;targetObj=&amp;targetType=}')">×</a>
						<a href="javascript:void(0);" onclick="rowUp_Sys(this)">up</a>
						<a href="javascript:void(0);" onclick="rowDown_Sys(this)">down</a>
						<span class="glyphicon glyphicon-remove" aria-hidden="true" onClick="btnClk_Sys(this,'current-delOneRow','test/createDialog.jsp',0,'{param=?&amp;targetObj=&amp;targetType=}')" style='cursor:hand;'></span>
					<span class="glyphicon glyphicon-chevron-up" aria-hidden="true" onClick="rowUp_Sys(this)" style='cursor:hand;'></span>
					<span class="glyphicon glyphicon-chevron-down" aria-hidden="true" onClick="rowDown_Sys(this)" style='cursor:hand;'></span>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td class="zqh_form_footer" colspan="100%">
					</td>
				</tr>
				<tr>
					<td colspan="100%" align="right">
						<input class="zqh_form_add btn btn-default btn-sm" type="button" value="复制选择行"
							onclick="btnClk_Sys(this,'current-copyOneRow','test/createDialog.jsp',0,'{param=?&amp;targetObj=&amp;targetType=}')">
						<input class="zqh_form_add btn btn-default btn-sm" type="button" value="添加新行"
							onclick="btnClk_Sys(this,'current-addOneRow','test/createDialog.jsp',0,'{param=?&amp;targetObj=&amp;targetType=}')">
					</td>
				</tr>
			</tfoot>
		</table>
		</div>
  <div class="panel-footer" style="text-align:center;">
<button type="button" id="1" class="btn btn-default "
							onclick="btnClk_Sys(this,'action-ajax','test/createDialog.jsp',0,'{param=enumgroupform|enumgrid&amp;isAjax=&amp;priv=&amp;targetObj=&amp;targetType=}')">
							&nbsp;&nbsp;保&nbsp;存&nbsp;&nbsp;
						</button>  </div>
</div>
	</body>
</html>
