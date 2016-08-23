 <%
String PATH = request.getContextPath();
String BASEPATH= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+PATH+"/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

     <base href="<%=BASEPATH%>">
 
  <link type="text/css" rel="stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/jscal2.css" />
    <link type="text/css" rel="stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/border-radius.css" />
    <!-- <link type="text/css" rel="stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/reduce-spacing.css" /> -->

    <link id="skin-win2k" title="Win 2K" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/win2k/win2k.css" />
    <link id="skin-steel" title="Steel" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/steel/steel.css" />
    <link id="skin-gold" title="Gold" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/gold/gold.css" />
    <link id="skin-matrix" title="Matrix" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/matrix/matrix.css" />

    <link id="skinhelper-compact" type="text/css" rel="alternate stylesheet" href="third/JSCal2-1.9/JSCal2-1.9/src/css/reduce-spacing.css" />

    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/jscal2.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/unicode-letter.js"></script>

    <!-- you actually only need to load one of these; we put them all here for demo purposes -->
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/ca.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/cn.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/cz.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/de.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/es.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/fr.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/hr.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/it.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/jp.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/nl.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/pl.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/pt.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/ro.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/ru.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/sk.js"></script>
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/sv.js"></script>

    <!-- this must stay last so that English is the default one -->
    <script src="third/JSCal2-1.9/JSCal2-1.9/src/js/lang/en.js"></script>
