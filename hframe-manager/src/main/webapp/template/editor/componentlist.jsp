<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="com.hframework.common.util.message.HtmlUtils" %>
<%@ page import="com.hframework.common.bean.PageContainer" %>
<%@ page import="com.hframework.common.util.message.PageTemplateParseUtil" %>

<%
	String url = request.getParameter("url");
	String filePath = request.getRealPath("/");
	filePath += url;
	Document document = HtmlUtils.getDocumentFromFile(filePath);
	new PageContainer(document.body()).parse();
	PageTemplateParseUtil.setElementHfId(document);
	String treeXml = PageTemplateParseUtil.getTreeXml(document);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ include file="/webframe/commonhead.jsp" %>
<%@ include file="/webframe/mycommonhead.jsp" %>
<head>

	<title>组件列表</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">



	</script>


</head>

<body>
<div id="munuTreedf"  class="dhtmlxTree_zqh" style=""></div>
<script type="text/javascript">
	var munuTreedf=new dhtmlXTreeObject("munuTreedf","100%","100%",0);
	munuTreedf.setImagePath("third/dhtmlxTree/imgs/");
	munuTreedf.enableCheckBoxes(1);
	munuTreedf.enableThreeStateCheckboxes(false)//false为失效

	munuTreedf.enableDragAndDrop(true);
//	setItemColor(munuTreedf.getSelectedItemId(), "red", "red");

	munuTreedf.setOnCheckHandler(elementCheck);
	munuTreedf.setOnClickHandler(elementClick);

	munuTreedf.loadXMLString('<tree id="0"><%=treeXml %></tree>');
//	munuTreedf.loadXMLString('<tree id="0"><item text="Books" id="books" open="1" im0="books_close.gif" im1="tombs.gif" im2="tombs.gif" call="1" select="1"><item text="Mystery &amp; Thrillers" id="mystery" im0="book.gif" im1="books_open.gif" im2="books_close.gif"><item text="Lawrence Block" id="lb" im0="book.gif" im1="books_open.gif" im2="book.gif"><item text="All the Flowers Are Dying" id="lb_1" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="The Burglar on the Prowl" id="lb_2" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="The Plot Thickens" id="lb_3" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Grifters Game" id="lb_4" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="The Burglar Who Thought He Was Bogart" id="lb_5" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/></item><item text="Robert Crais" id="rc" im0="book.gif" im1="books_open.gif" im2="book.gif"><item text="The Forgotten Man" id="rc_1" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Stalking the Angel" id="rc_2" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Free Fall" id="rc_3" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Sunset Express" id="rc_4" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Hostage" id="rc_5" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/></item><item text="Ian Rankin" id="ir" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="James Patterson" id="jp" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Nancy Atherton" id="na" im0="book.gif" im1="books_open.gif" im2="book.gif"></item></item><item text="History" id="history" im0="book.gif" im1="books_open.gif" im2="books_close.gif"><item text="John Mack Faragher" id="jmf" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Jim Dwyer" id="jd" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Larry Schweikart" id="ls" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="R. Lee Ermey" id="rle" im0="book.gif" im1="books_open.gif" im2="book.gif"></item></item><item text="Horror" id="horror" open="1" im0="book.gif" im1="books_open.gif" im2="books_close.gif"><item text="Stephen King" id="sk" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Dan Brown" id="db" im0="book.gif" im1="books_open.gif" im2="book.gif"><item text="Angels &amp; Demons" id="db_1" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Deception Point" id="db_2" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Digital Fortress" id="db_3" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="The Da Vinci Code" id="db_4" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Deception Point" id="db_5" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/></item><item text="Mary Janice Davidson" id="mjd" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Katie Macalister" id="km" im0="book.gif" im1="books_open.gif" im2="book.gif"></item></item><item text="Science Fiction &amp; Fantasy" id="fantasy" im0="book.gif" im1="books_open.gif" im2="books_close.gif"><item text="Audrey Niffenegger" id="af" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Philip Roth" id="pr" im0="book.gif" im1="books_open.gif" im2="book.gif"></item></item><item text="Sport" id="sport" im0="book.gif" im1="books_open.gif" im2="books_close.gif"><item text="Bill Reynolds" id="br" im0="book.gif" im1="books_open.gif" im2="book.gif"></item></item><item text="Teens" id="teens" im0="book.gif" im1="books_open.gif" im2="books_close.gif"><item text="Joss Whedon" id="jw" im0="book.gif" im1="books_open.gif" im2="book.gif"><item text="Astonishing X-Men" id="jw_1" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Joss Whedon: The Genius Behind Buffy" id="jw_2" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Fray" id="jw_3" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="Tales Of The Vampires" id="jw_4" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/><item text="The Harvest" id="jw_5" im0="book_titel.gif" im1="book_titel.gif" im2="book_titel.gif"/></item><item text="Meg Cabot" id="mc" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Garth Nix" id="gn" im0="book.gif" im1="books_open.gif" im2="book.gif"></item><item text="Ann Brashares" id="ab" im0="book.gif" im1="books_open.gif" im2="book.gif"></item></item></item><item text="Magazines" id="magazines" im0="tombs_mag.gif" im1="tombs_mag.gif" im2="tombs_mag.gif"><item text="Sport" id="mag_sp" im0="magazines_open.gif" im1="magazines_open.gif" im2="magazines_close.gif"></item></item></tree>');

    function elementClick(id){
//		var content =$(parent.frames["htmlframe"].document).find("[hf-id=" + id + "]").html();
//		alert(content);
		$curElement = $(parent.frames["htmlframe"].document).find("[hf-id=" + id + "]");
		if($curElement.css("border") == "2px solid rgb(255, 255, 0)"){
			$curElement.css("border","");
		}else {
			$curElement.css("border","2px solid rgb(255, 255, 0)");
		}
	}

	function elementCheck(id){
		var checked = munuTreedf.isItemChecked(id);
		$curElement = $(parent.frames["htmlframe"].document).find("[hf-id=" + id + "]");
		if(checked == 1) {//选中
			$curElement.css("border","2px solid rgb(255, 0, 0)");
			$curElement.addClass(".hframe-component");
		}else {//未选中
			$curElement.css("border","");
			$curElement.removeClass(".hframe-component");
		}

		$("#page-modify").attr("src","<%=request.getContextPath() %>/template/editor/pagemodify.jsp?url=<%=url %>"
				+ "&hf-id=" + id + "&checked=" + checked);

	}
</script>

<iframe src="" width="100%" frameborder="0" width="100%" name="page-modify" id="page-modify" class="page-modify" scrolling="yes" style="" height="20px;" ></iframe>
<script type="text/javascript">
//	createContextMenu_Sys("#munuTreedf span");
</script>
</body>
</html>
