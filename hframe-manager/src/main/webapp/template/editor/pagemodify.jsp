<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="com.hframework.common.util.message.HtmlUtils" %>
<%@ page import="com.hframework.common.bean.PageContainer" %>
<%@ page import="com.hframework.common.util.message.PageTemplateParseUtil" %>
<%@ page import="com.hframework.common.util.FileUtils" %>
<%@ page import="org.jsoup.select.Elements" %>
<%--
  Created by IntelliJ IDEA.
  User: zhangqh6
  Date: 2016/1/3
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String url = request.getParameter("url");
  String hfId = request.getParameter("hf-id");
  String checked = request.getParameter("checked");
  String filePath = request.getRealPath("/");
  filePath += url;
  Document document = HtmlUtils.getDocumentFromFile(filePath);
  Elements elements = document.select("[hf-id=" + hfId + "]");
  if("1".equals(checked)) {
    elements.get(0).addClass("hframe-component");
  }else {
    elements.get(0).removeClass("hframe-component");
  }
  FileUtils.writeFile(filePath, document.outerHtml());
%>

<%
if("1".equals(checked)) {
%>
  <span>已设置该组件..</span>
<%
}else {
%>
  <span>已取消该组件..</span>
<%
  }
%>

