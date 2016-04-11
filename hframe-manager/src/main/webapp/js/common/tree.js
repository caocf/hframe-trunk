
function treeAdd_Sys(tree,text){
	if (typeof(tree)!="object")
 	tree=document.getElementById(tree);	
	var d=new Date(); 
	tree.insertNewItem(tree.getSelectedItemId(),d.valueOf(),text,0,0,0,0,'SELECT');
	 fixImage(d.valueOf());
	 
}
function treeEdit_Sys(tree,text){
	if (typeof(tree)!="object")
 	tree=document.getElementById(tree);	
	var d=new Date(); 
	tree.insertNewItem(tree.getSelectedItemId(),d.valueOf(),text,0,0,0,0,'SELECT');
	 fixImage(d.valueOf());
}

function getTreeSel_Sys(tree){

	if (typeof(tree)!="object")
 	tree=document.getElementById(tree);

	var selId=tree.getAllChecked();
	return selId;
}
function TreeDel_Sys(tree){

	if (typeof(tree)!="object")
 	tree=document.getElementById(tree);

	tree.deleteItem(tree.getSelectedItemId(),true);
}

function TreeClick_Sys(id){

}
function TreeClickGetText_Sys(id){

			var treeDivObj =FindParentElement(event.srcElement,"DIV");
	 		while(treeDivObj.className!='containerTableStyle'){
	 			treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		}
	 		treeDivObj =FindParentElement(treeDivObj,"DIV");
			return eval(treeDivObj.id).getItemText(id);
}
function TreeClickGetUrl_Sys(id){

			var treeDivObj =FindParentElement(event.srcElement,"DIV");
	 		while(treeDivObj.className!='containerTableStyle'){
	 			treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		}
	 		treeDivObj =FindParentElement(treeDivObj,"DIV");
			

			var url = eval(treeDivObj.id).getUserData(id,"url");
			if(url!=null&&url!=""&&url!="javascript:void(0);"){
				return url;
			}
			return "";
}


function TreeClick_Sys4Nav(id){

			var treeDivObj =FindParentElement(event.srcElement,"DIV");
	 		while(treeDivObj.className!='containerTableStyle'){
	 			treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		}
	 		treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		
			alert(eval(treeDivObj.id).getItemText(id));

			var url = eval(treeDivObj.id).getUserData(id,"url");
			if(url!=null&&url!=""&&url!="javascript:void(0);"){
				parent.RightFrame.location=url;
			}
	//alert(id);
}
function TreeClick_Sys4Other(id){
	
			refresh_Sys(null,'sysOrgForm','tag_ObjectId='+id)
			return id;
}


