<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'coorchoose.jsp' starting page</title>
    
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
<div id="colorChooseDiv" style="border: 1px solid blue;border-top:10px solid blue; width: 250px; height: 80px;text-align: center;z-index: 100;position: absolute;left: 100px;top: 100px;display: none;"   onmouseup='doDocMouseUp()' onmousemove='doDocMouseMove()' onmouseout='doDocMouseOut()'>
<div  >
	<table border=0 cellPadding=0 cellSpacing=10>
	<tr>
		<td align="right" style="background: #ddd;"><a  href="javascript:hiddenThisDiv()">╳</a> </td>
	</tr>
  <tr>
    <td>
      <table border=0 cellPadding=0 cellSpacing=0 id=ColorTable onclick="colorTableClick()" onmouseover="colorTableMouseOver()" onmouseout="colorTableMouseOut()"
      style="CURSOR: hand">
      <script type="text/javascript">
      function wc(r, g, b, n)
{
 r = ((r * 16 + r) * 3 * (15 - n) + 0x80 * n) / 15;
 g = ((g * 16 + g) * 3 * (15 - n) + 0x80 * n) / 15;
 b = ((b * 16 + b) * 3 * (15 - n) + 0x80 * n) / 15;

 document.write('<TD BGCOLOR=#' + ToHex(r) + ToHex(g) + ToHex(b) + ' height=8 width=8></TD>');
}

var cnum = new Array(1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0);

  for(i = 0; i < 16; i ++)
  {
     document.write('<TR>');
     for(j = 0; j < 30; j ++)
     {
      n1 = j % 5;
      n2 = Math.floor(j / 5) * 3;
      n3 = n2 + 3;

      wc((cnum[n3] * n1 + cnum[n2] * (5 - n1)),
       (cnum[n3 + 1] * n1 + cnum[n2 + 1] * (5 - n1)),
       (cnum[n3 + 2] * n1 + cnum[n2 + 2] * (5 - n1)), i);
     }

     document.writeln('</TR>');
  }
      </script>
       </table></td>
    <td>
      <table border=0 cellPadding=0 cellSpacing=0 id=GrayTable onclick="grayTableClick()" onmouseover="grayTableMouseOver()" onmouseout="grayTableMouseOut()"
      style="CURSOR: hand">
        <script language=JavaScript>
		  for(i = 255; i >= 0; i -= 8.5)
		     document.write('<TR BGCOLOR=#' + ToHex(i) + ToHex(i) + ToHex(i) + '><TD TITLE=' + Math.floor(i * 16 / 17) + ' height=4 width=20></TD></TR>');
		</script>
</table></td></tr></table></div>
<div>
<table border=0 cellPadding=0 cellSpacing=10>

  <tr>
    <td align=middle rowSpan=2>选中色彩
      <table border=1 cellPadding=0 cellSpacing=0 height=30 id=ShowColor
      width=40>

        <tr>
          <td></td></tr></table></td>
    <td rowSpan=2>基色: <span id=RGB></span><br>亮度: <span
      id=GRAY>120</span><br>代码: <input id=SelColor size=9></td>
    <td></td></tr>
  <tr>
    <td><input type=BUTTON onclick=window.close(); value="关闭" class=buttonface>
</td>
</tr>
</table>
</div>
</div>

  </body>
</html>
