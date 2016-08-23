var gColorArr = ["","classicalblue","softgreen","classicalvista","asiainfoorange"];
var gccFontsizeArr = [,12,13,14];

function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	} else expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
var nameEQ = name + "=";
var ca = document.cookie.split(';');
for(var i=0;i < ca.length;i++) {
    var c = ca[i];
    while (c.charAt(0)==' ') c = c.substring(1,c.length);
    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
}
return null;
}

function setup(){
	var a = document.getElementById("theme_css_id");;
	a.href = "<%=request.getContextPath()%>/theme/"+ getColorName() +"/css/main.css";
}

function getColorName(){
	return gColorArr[stylecookie.charAt(1)];
}

function setupFontSize(){
	var a = document.styleSheets[0];
	a.rules.item(1).style.fontSize = a.rules.item(0).style.fontSize = gccFontsizeArr[stylecookie.charAt(2)]+"px";
}

var stylecookie = readCookie("style");
//frame color  font
if(!stylecookie)
	stylecookie ="111";
