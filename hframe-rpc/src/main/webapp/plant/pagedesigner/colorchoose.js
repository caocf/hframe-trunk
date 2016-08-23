var SelRGB = '';
var DrRGB = '';
var SelGRAY = '120';

var srcObj;

var hexch = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');

function ToHex(n)
{ var h, l;

 n = Math.round(n);
 l = n % 16;
 h = Math.floor((n / 16)) % 16;
 return (hexch[h] + hexch[l]);
}

function DoColor(c, l)
{ var r, g, b;

  r = '0x' + c.substring(1, 3);
  g = '0x' + c.substring(3, 5);
  b = '0x' + c.substring(5, 7);

  if(l > 120)
  {
    l = l - 120;

    r = (r * (120 - l) + 255 * l) / 120;
    g = (g * (120 - l) + 255 * l) / 120;
    b = (b * (120 - l) + 255 * l) / 120;
  }else
  {
    r = (r * l) / 120;
    g = (g * l) / 120;
    b = (b * l) / 120;
  }

  return '#' + ToHex(r) + ToHex(g) + ToHex(b);
}

function EndColor()
{ var i;

  if(DrRGB != SelRGB)
  {
    DrRGB = SelRGB;
    for(i = 0; i <= 30; i ++)
      GrayTable.rows(i).bgColor = DoColor(SelRGB, 240 - i * 8);
  }

  SelColor.value = DoColor(RGB.innerText, GRAY.innerText);
  ShowColor.bgColor = SelColor.value;
}

function colorTableClick(){
		srcObj.value = event.srcElement.bgColor;
		
		if("focusObjBjColor"==srcObj.id){
			focusObj.style.backgroundColor=srcObj.value;
		}
		if("focusObjFontColor"==srcObj.id){
			focusObj.style.color=srcObj.value;
		}
		if("focusObjBorderColor"==srcObj.id){
			focusObj.style.borderColor=srcObj.value;
		}
		EndColor();
	
}

function colorTableMouseOver(){
	
	  RGB.innerText = event.srcElement.bgColor;
	  EndColor();
}

function colorTableMouseOut(){
	
	RGB.innerText = SelRGB;
	  EndColor();
	
}

function grayTableClick(){
	SelGRAY = event.srcElement.title;
	  EndColor();
	
}

function grayTableMouseOver(){
	 GRAY.innerText = event.srcElement.title;
	  EndColor();
	
}

function grayTableMouseOut(){
	 GRAY.innerText = SelGRAY;
	  EndColor();
	
}

function okClick(){
	
	 window.returnValue = SelColor.value;
	  window.close();
}

function showColorChooseDiv(){
	
	 srcObj = event.srcElement;
	
	
	 var mouseX = (event.x + document.body.scrollLeft);
	 var mouseY = (event.y + document.body.scrollTop);
	
	 document.getElementById("colorChooseDiv").style.position="absolute";
	 
	document.getElementById("colorChooseDiv").style.left=(mouseX-300)+"px";
	document.getElementById("colorChooseDiv").style.top=mouseY+"px";
	
	
	document.getElementById("colorChooseDiv").style.display="";
}

function hiddenColorChooseDiv(){
	//document.getElementById("colorChooseDiv").style.display="none";
}

function hiddenThisDiv(){
	document.getElementById("colorChooseDiv").style.display="none";

}
