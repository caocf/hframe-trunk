<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<%@page import="java.io.File"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pagedesignertools.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id="toolsDiv" style="width: 290px;text-align: left;z-index: 1000">
		<input id="objIdCounter" type="hidden" value="1">
		<input id="toolType" type="hidden" value="">
		
		<ul style="margin-top: 5px;">
			<li class='tab_title' id='baseTabs' onclick="javascript:tabsClick('baseTabs')">UI���</li>
			<li class='tab_title' id='psdTabs' onclick="javascript:tabsClick('psdTabs')">�زĿ�</li>
			<li class='tab_title' id='bgTabs' onclick="javascript:tabsClick('bgTabs')">��̨����</li>
		</ul>
	<!-- UI����б� -->
		<div id="baseTabsContent" style="text-align:left;">
		<table  style="font-size: 13px;" style="text-align:left;width:250px;">
			<tr>
				<td colspan="1"><b>ѡ��:</b><a href="javascript:clickTool('choose');">�J</a> </td>
				<td colspan="4"><b>ҳ��:</b><a href="javascript:loadPageCont();">����</a>&nbsp;
								<a href="javascript:exportPage();">����</a>&nbsp;
								<a href="javascript:reviewPage();">Ԥ��</a> 
				</td>
			</tr>
			<tr>
				<td><b>���⣺</b><a href="javascript:clickTool('h1');">H1</a> </td>
				<td><a href="javascript:clickTool('h2');">H2</a> </td>
				<td><a href="javascript:clickTool('h3');">H3</a> </td>
				<td><a href="javascript:clickTool('h4');">H4</a> </td>
				<td><a href="javascript:clickTool('h5');">H5</a> </td>
			</tr>
			<tr>
				<td colspan="5"><b>���</b>
					<a href="javascript:clickTool('table');">��</a>
					<font size="2">��</font><input id="rows" size="1" value="2">
					<font size="2" >��</font><input size="1" id="cols" value="3">
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<b>Div��</b><a href="javascript:clickTool('div');">Div</a>
					<b>�����ӣ�</b><a href="javascript:clickTool('div');">href</a> 
					<b>�б�</b><a href="javascript:clickTool('div');">list</a>
				 </td>
			</tr>
			<tr>
				<td colspan="5" >
					<b>ul��</b><a href="javascript:clickTool('ul1');">����</a>&nbsp;&nbsp;
							  <a href="javascript:clickTool('ul2');">����</a>&nbsp;&nbsp;
							  <b>ol��</b><a href="javascript:clickTool('ol1');">����</a>&nbsp;&nbsp;
							  <a href="javascript:clickTool('ol2');">����</a>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="5"><b>Maquee��</b><a href="javascript:clickTool('marquee');">Maquee</a> </td>
			</tr>
		</table>
	</div>
	<!-- �ز��б� -->
	<div id="psdTabsContent" style="display: none;height:200px;width: 150px; overflow-y: scroll; "  >
	<table >
		<tr>
			<th colspan="100%">ͼƬ�б�</th>
		</tr>
		<% File[] fileList=FileUtil.getFileList(new File(request.getRealPath("/design/material")));
		for(int i=0;i<fileList.length;i++){
		 	if(i%5==0){
		 %>
		 	<tr>
		 <%
		 	}
		%>
			<td><img src="design/material/<%=fileList[i].getName() %>" width="45px;" height="45px;" onclick="clickTool('img',this.src)" onmouseover="expendImg(this.src)" onmouseout="unexpendImg()"></td>
		<%	if(i%5==0){
		 %>
		 	</tr>
		 <%
		 	}
		 		}
		%>	
	</table>
	
	</div>
	<!-- ��̨�����б� -->
	<div id="bgTabsContent" style="display: none;height:200px;width: 150px; overflow-y: scroll; "  >
	<!-- 
	<table>
		<tr>
			<th align="right">DB:</th>
			<td><select id="currentDb" name="currentDb"  onchange='getTables()'></select></td>
		</tr>
		<tr>
			<th  align="right">TABLE:</th>
			<td><select id="currentTable" name="currentTable" onchange='getTableContent()'></select></td>
		</tr>
		<tr>
			<th  align="right">����:</th>
			<td><input type="radio" name="methodSelect" value="create1" onclick="getTableContent()" >��
				<input type="radio" name="methodSelect" value="create2" onclick="getTableContent()" >��
				<input type="radio" name="methodSelect" value="create3" onclick="getTableContent()" >����
			</td>
		</tr>
		<tr>
			<th  align="right">չʾ:</th>
			<td><input type="radio" name="methodSelect" value="show1" onclick="getTableContent()" >��
				<input type="radio" name="methodSelect" value="show2" onclick="getTableContent()" >��
				<input type="radio" name="methodSelect" value="show3" onclick="getTableContent()" >����
			</td>
		</tr>
	</table>
	 -->
	<table style="font-size: 18px;">
		<tr>
			<th  align="right">frame:</th>
			<td>
				<a href="javascript:bgDell('frame')">zqh:frame</a>
			</td>
		</tr>
		<tr>
			<th  align="right">menu:</th>
			<td>
				<a href="javascript:bgDell('menu')">zqh:menu</a>
			</td>
		</tr>
		<tr>
			<th  align="right">tab:</th>
			<td>
				<a href="javascript:bgDell('tab')">zqh:tab</a>
			</td>
		</tr>
		<tr>
			<th  align="right">dtree:</th>
			<td>
				<a href="javascript:bgDell('dtree')">zqh:dtree</a>
			</td>
		</tr>
		<tr>
			<th  align="right">myform:</th>
			<td>
				<a href="javascript:bgDell('myform')">zqh:myform</a>
			</td>
		</tr>
		<tr>
			<th  align="right">mygrid:</th>
			<td>
				<a href="javascript:bgDell('mygrid')">zqh:mygrid</a>
			</td>
		</tr>
		<tr>
			<th  align="right">mylist:</th>
			<td>
				<a href="javascript:bgDell('mylist')">zqh:mylist</a>
			</td>
		</tr>
	</table>
	</div>	
	<table  id="objAttrContent" >
		<tr>
			<td colspan="4"><b>��������</b>   <a id="moreAttr" href="javascript:moreAtrr()">����>></a> </td>
		</tr>
		<tr>
			<td>id:</td>
			<td colspan="3"><label id="focusObjId" ></label></td>
		</tr>
		<tr>
			<td>text:</td>
			<td colspan="3"><input id="focusObjText" size="6" onkeyup="changeAttributeValue('text')"></input></td>
		</tr>
		<tr>
			<td>width:</td>
			<td colspan="3"><input id="focusObjWidth" size="6" onkeyup="changeAttributeValue('width')"></input></td>
		</tr>
		<tr>
			<td>height:</td>
			<td colspan="3"><input id="focusObjHeight" size="6" onkeyup="changeAttributeValue('height')"></input></td>
		</tr>
		<tr>
			<td>bg-color:</td>
			<td colspan="3"><input id="focusObjBjColor" size="6" onchange="changeAttributeValue('bg-color')" onfocus="showColorChooseDiv()" onblur="hiddenColorChooseDiv()"></input></td>
		</tr>
		<tr>
			<td>font-color:</td>
			<td colspan="3"><input id="focusObjFontColor" size="6" onchange="changeAttributeValue('font-color')" onfocus="showColorChooseDiv()" onblur="hiddenColorChooseDiv()"></input></td>
		</tr>
		<tr>
			<td>border-color:</td>
			<td colspan="3"><input id="focusObjBorderColor" size="6" onchange="changeAttributeValue('border-color')" onfocus="showColorChooseDiv()" onblur="hiddenColorChooseDiv()"></input></td>
		</tr>
		<tr>
			<td>border-size:</td>
			<td colspan="3"><input id="focusObjBorderSize" size="6"  onkeyup="changeAttributeValue('border-size')"></input></td>
		</tr>
		
		<tr>
			<td>font-size:</td>
			<td colspan="3"><input id="focusObjBorderSize" size="6"  onkeyup="changeAttributeValue('font-size')"></input></td>
		</tr>
		<tr>
			<td>padding:</td>
			<td colspan="3"><input id="focusObjBorderSize" size="6"  onkeyup="changeAttributeValue('padding')"></input></td>
		</tr>
		<tr>
			<td>margin:</td>
			<td colspan="3"><input id="focusObjBorderSize" size="6"  onkeyup="changeAttributeValue('margin:')"></input></td>
		</tr>
		<tr>
			<td>float:</td>
			<td colspan="3"><input id="focusObjBorderSize" size="6"  onkeyup="changeAttributeValue('float')"></input></td>
		</tr>
		<tr>
			
			<td colspan="4">	<input id="mouseX" size="2"><input id="mouseY" size="2"><input id="screenX" size="2"><input id="screenY" size="2">
			</td>
		</tr>
	</table>
	<div id="tagObjDiv" style="position: absolute;left: -930px;top:0px;border: 1px solid #bbb;width:200px;height: 470px;background-color: eee;">
		<table width="200px;">
			<tr>
				<td width="100%" style="background-color: #dddddd"><h1 >����</h1></td>
			</tr>
			<tr>
				<td id="tagObjContent">
					
				</td>
			</tr>
		
		</table>
	</div>
	
	<div id="tagAttrDiv" style="position: absolute;left: -930px;top:480px;border: 1px solid #bbb;width:930px;height: 80px;font-size: 13px;background-color: eee;">
		<table>
			<tr>
				<td width="50px;" style="background-color: #dddddd"><h1 >����</h1></td>
				<td valign="top">
					<div class="tagAttrDiv" id="frameDiv" style="display: none;">
						<table>
						<tr>
							
							<td>FrameID��</td>
							<td><input class="tag_Type" type="hidden" value="frame"> <input class="tag_Id" size="6"> </td>
							<td>Width��</td>
							<td><input class="tag_Width" size="3" value="400"> </td>
							<td>Height:</td>
							<td><input class="tag_Height" size="3" value="200"> </td>
							<td>Title:</td>
							<td><input class="tag_Title" value="Ĭ�ϱ���1"> </td>
							
						</tr>
						<tr>
							<td>Isframe��</td>
							<td>
								<select class="tag_Isframe" name="tag_Isframe" >
									<option value="false">��</option>
									<option value="true">��</option>
								</select>
							</td>
							<td>Src��</td>
							<td><input class="tag_Src"><img src="design/images/search.jpg" onclick="searchfile()"/></td>
							<td>Style��</td>
							<td><input class="tag_Style"></td>
							<td><input class="tag_OK" type="button" value="ȷ��"/><input class="tag_Refresh" type="button" value="ˢ��"/></td>
						</tr>
					</table>	
					
					</div>
					<div class="tagAttrDiv" id="menuDiv" style="display: none;">
						<table>
						<tr>
							<td>MenuID��</td>
							<td><input class="tag_Type" type="hidden" value="menu"> <input class="tag_Id" size="6"> </td>
							<td>Width��</td>
							<td><input class="tag_Width" size="3" value="400"> </td>
							<td>Height:</td>
							<td><input class="tag_Height" size="3" value="200"> </td>
							<td>Title:</td>
							<td><input class="tag_Title" value="Ĭ�ϱ���1"> </td>
							<td>View��</td>
							<td><input class="tag_View" value="menu"> </td>
						</tr>
						<tr>
							<td>Direction��</td>
							<td>
								<select class="tag_Direction" name="tag_Direction" >
									<option value="1">���ϵ���</option>
									<option value="1">���µ���</option>
								</select>
							</td>
							
							<td>Checkbox��</td>
							<td>
								<select class="tag_Checkbox" name="tag_Checkbox" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>CanClick��</td>
							<td>
								<select class="tag_CanClick" name="tag_CanClick" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>Condition��</td>
							<td><input class="tag_Condition"></td>
							<td><input class="tag_OK" type="button" value="ȷ��"/><input class="tag_Refresh" type="button" value="ˢ��"/></td>
						</tr>
					</table>	
					
					</div>
					<div class="tagAttrDiv" id="tabDiv" style="display: none;">
						<table>
						<tr>
							<td>TabID��</td>
							<td><input class="tag_Type" type="hidden" value="tab"> <input class="tag_Id" size="6"> </td>
							<td>Width��</td>
							<td><input class="tag_Width" size="3" value="400"> </td>
							<td>Height:</td>
							<td><input class="tag_Height" size="3" value="200"> </td>
							<td>View��</td>
							<td><input class="tag_View"> </td>
							<td>Condition��</td>
							<td><input class="tag_Condition"></td>
							<td><input class="tag_OK" type="button" value="ȷ��"/><input class="tag_Refresh" type="button" value="ˢ��"/></td>
						</tr>
					</table>	
					</div>
					<div class="tagAttrDiv" id="dtreeDiv"  style="display: none;">
						<table>
						<tr>
							<td>DtreeID��</td>
							<td><input class="tag_Type" type="hidden" value="dtree"> <input class="tag_Id" size="6"> </td>
							<td>Width��</td>
							<td><input class="tag_Width" size="3" value="400"> </td>
							<td>Height:</td>
							<td><input class="tag_Height" size="3" value="200"> </td>
							<td>Title:</td>
							<td><input class="tag_Title" value="Ĭ�ϱ���1"> </td>
							<td>View��</td>
							<td><input class="tag_View" value="treeDemo"> </td>
						</tr>
						<tr>
							
							<td>Checkbox��</td>
							<td>
								<select class="tag_Checkbox" name="tag_Checkbox" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>Editable��</td>
							<td>
								<select class="tag_Editable" name="tag_Editable" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>RootId��</td>
							<td><input class="tag_RootId" size="3"></td>
							<td>Condition��</td>
							<td><input class="tag_Condition"></td>
							<td><input class="tag_OK" type="button" value="ȷ��"/><input class="tag_Refresh" type="button" value="ˢ��"/></td>
						</tr>
					</table>	
					</div>
					<div class="tagAttrDiv" id="myformDiv"   style="display: none;">
						<table>
						<tr> 
							<td>MyformID��</td>
							<td><input class="tag_Type" type="hidden" value="myform"> <input class="tag_Id" size="6"> </td>
							<td>Width��</td>
							<td><input class="tag_Width" size="3" value="400"> </td>
							<td>Height:</td>
							<td><input class="tag_Height" size="3" value="200"> </td>
							<td>Title:</td>
							<td><input class="tag_Title" value="Ĭ�ϱ���1"> </td>
							<td>View��</td>
							<td>
								<select class="tag_View">
								<%
									for(String tableView:SetCacheFactory.getTableSetKey()){
								%>
									<option value="<%=tableView %>"><%=tableView %></option>
								<%
									}
								 %>
									<option value="----">---------</option>
								<%
									for(String columnView:SetCacheFactory.getColumnSetKey()){
								%>
									<option value="<%=columnView %>"><%=columnView %></option>
								<%
									}
								 %>
							
								</select>
							</td>
						</tr>
						<tr>
							<td>Editable��</td>
							<td>
								<select class="tag_Editable" name="tag_Editable" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>ColNum��</td>
							<td><input class="tag_ColNum" size="3" value="3"></td>
							<td>Condition��</td>
							<td><input class="tag_Condition"></td>
							<td><input class="tag_OK" type="button" value="ȷ��"/><input class="tag_Refresh" type="button" value="ˢ��"/></td>
						
						</tr>
					</table>	
					</div>
					<div class="tagAttrDiv" id="mygridDiv"   style="display: none;">
						<table>
						<tr>
							<td>MygridID��</td>
							<td><input class="tag_Type" type="hidden" value="mygrid"> <input class="tag_Id" size="6"> </td>
							<td>Width��</td>
							<td><input class="tag_Width" size="3" value="400"> </td>
							<td>Height:</td>
							<td><input class="tag_Height" size="3" value="200"> </td>
							<td>Title:</td>
							<td><input class="tag_Title" value="Ĭ�ϱ���1"> </td>
							<td>View��</td>
							<td>
								<select class="tag_View">
								<%
									for(String tableView:SetCacheFactory.getTableSetKey()){
								%>
									<option value="<%=tableView %>"><%=tableView %></option>
								<%
									}
								 %>
									<option value="----">---------</option>
								<%
									for(String columnView:SetCacheFactory.getColumnSetKey()){
								%>
									<option value="<%=columnView %>"><%=columnView %></option>
								<%
									}
								 %>
							
								</select>
							 </td>
						</tr>
						<tr>
							<td>Editable��</td>
							<td>
								<select class="tag_Editable" name="tag_Editable" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>Condition��</td>
							<td><input class="tag_Condition"></td>
							<td><input class="tag_OK" type="button" value="ȷ��"/><input class="tag_Refresh" type="button" value="ˢ��"/></td>
						</tr>
					</table>	
					</div>
					<div class="tagAttrDiv" id="mylistDiv"   style="display: none;">
						<table>
							<tr>
								<td>MylistID��</td>
								<td><input class="tag_Type" type="hidden" value="mylist"> <input class="tag_Id" size="6"> </td>
								<td>Width��</td>
								<td><input class="tag_Width" size="3" value="400"> </td>
								<td>Height:</td>
								<td><input class="tag_Height" size="3" value="200"> </td>
								<td>Title:</td>
								<td><input class="tag_Title" value="Ĭ�ϱ���1"> </td>
								<td>View��</td>
								<td>
										<select class="tag_View">
								<%
									for(String fieldView:SetCacheFactory.getFieldSetKey()){
								%>
									<option value="<%=fieldView %>"><%=fieldView %></option>
								<%
									}
								 %>
							
								</select>
								</td>
							</tr>
							<tr>
								<td>Editable��</td>
								<td>
									<select class="tag_Editable" name="tag_Editable" >
										<option value="0">��</option>
										<option value="1">��</option>
									</select>
								</td>
								<td>Condition��</td>
								<td><input class="tag_Condition"></td>
								<td><input class="tag_OK" type="button" value="ȷ��"/><input class="tag_Refresh" type="button" value="ˢ��"/></td>
							
							</tr>
						</table>	
					</div>
					
					<!-- 
					<table>
						<tr>
							<td>ID��</td>
							<td><input id="tag_Id" size="6"> </td>
							<td>Width��</td>
							<td><input id="tag_Width" size="3" value="400"> </td>
							<td>Height:</td>
							<td><input id="tag_Height" size="3" value="200"> </td>
							<td>Title:</td>
							<td><input id="tag_Title" value="Ĭ�ϱ���1"> </td>
							<td>View��</td>
							<td><input id="tag_View"> </td>
						</tr>
						<tr>
							<td>Direction��</td>
							<td>
								<select id="tag_Direction" name="tag_Direction" >
									<option value="1">���ϵ���</option>
									<option value="1">���µ���</option>
								</select>
							</td>
							
							<td>Checkbox��</td>
							<td>
								<select id="tag_Checkbox" name="tag_Checkbox" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>Editable��</td>
							<td>
								<select id="tag_Editable" name="tag_Editable" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>Src��</td>
							<td><input id="tag_Src"> </td>
							<td>Style��</td>
							<td><input id="tag_Style"></td>
						</tr>
						<tr>
							<td>Isframe��</td>
							<td>
								<select id="tag_IsFrame" name="tag_IsFrame" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>CanClick��</td>
							<td>
								<select id="tag_CanClick" name="tag_CanClick" >
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
							<td>RootId��</td>
							<td><input id="tag_RootId" size="3"></td>
							<td>ColNum��</td>
							<td><input id="tag_ColNum" size="3" value="3"></td>
							<td>Condition��</td>
							<td><input id="tag_Condition"></td>
						</tr>
					</table>	
					 -->
				</td>
			</tr>
		</table>
	</div>
</div>
  </body>
</html>
