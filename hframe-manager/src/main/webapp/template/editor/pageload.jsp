<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="com.hframework.common.util.message.HtmlUtils" %>
<%@ page import="com.hframework.common.bean.PageContainer" %>
<%@ page import="com.hframework.common.util.message.PageTemplateParseUtil" %>
<%@ page import="com.hframework.common.util.FileUtils" %>
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
  String filePath = request.getRealPath("/");
  filePath += url;
  Document document = HtmlUtils.getDocumentFromFile(filePath);
  new PageContainer(document.body()).parse();
  PageTemplateParseUtil.setElementHfId(document);
    FileUtils.writeFile(filePath, document.outerHtml());
//  request.getRequestDispatcher("/" + url).forward(request, response);
  response.sendRedirect("/" + url);

%>

