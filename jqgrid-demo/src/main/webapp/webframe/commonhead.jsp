<%
  response.setHeader("Pragma","No-cache");
  response.setHeader("Cache-Control","no-cache");
  //com.ai.appframe2.util.locale.AppframeLocaleFactory.getCurrentLocale().toString()
%>
<%@taglib prefix="zqh" uri="/WEB-INF/zqhframe.tld" %>

<script language="JavaScript" src="<%=request.getContextPath()%>/secframe/common/common.js"/></script>
<script src="<%=request.getContextPath()%>/webframe/common/cookie.jsp"></script>
<!-- 
<link rel="stylesheet" href="<%=request.getContextPath()%>/webframe/common/main.css" type="text/css"> -->

<script src="<%=request.getContextPath()%>/jsv2/i18n/AILocale.jsp"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/jsv2/i18n/appframe_js_resource_zh_CN.js"></script>
<script src="<%=request.getContextPath()%>/jsv2/Globe_v2.jsp"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/jsv2/i18n/secframe_js_resource_zh_CN.js"></script>

<link id="theme_css_id" rel="stylesheet" type="text/css">

<script language="javascript">
document.onload = setupFontSize();
document.onload = setup();
</script>
 
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/AIEvent.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/CommUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/AIWaitBanner.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/showTitle.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/Globe_v2.jsp"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/DBListBox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/UserData_v2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/PopMenu_v2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/HtmlParameter.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsv2/DBTree_new.js"></script>

	