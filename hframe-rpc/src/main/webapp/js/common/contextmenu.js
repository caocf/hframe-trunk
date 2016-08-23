	function FindParentElement(element, tagName)
	{
	    element = element.parentElement;
	
	    while(element != null && element.tagName != tagName )
	    {
	        element = element.parentElement;
	    }
	    if ( element != null && element.tagName == tagName )
	    {
	        return element;
	    }
	    return null;
	}
	
	function FindPreVElement(element, tagName ,className)
	{	    	
		
	
		//element = element.previousSibling;
	
	    while(element != null &&element.className!=className)
	    {
	        element = element.previousSibling;
	    }
	    if ( element != null && element.tagName == tagName )
	    {
	        return element;
	    }
	    return null;
	}

	function create_Right(e ){
	 		var tagName = e.element().tagName.toLowerCase(),
            x = e.screenX,
            y = e.screenY;
            alert('you clicked on <' + tagName + '> element at x: ' + x + ', and y: ' + y);
	}
	
	function create_Right4Tree(e ){
	 		var treeDivObj =FindParentElement(e.element(),"DIV");
	 		while(treeDivObj.className!='containerTableStyle'){
	 			treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		}
	 		treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		
			treeAdd_Sys(eval(treeDivObj.id),"����... ");
	}
		 		
	
	
	function edit_Right4Tree(e){
				var treeDivObj =FindParentElement(e.element(),"DIV");
	 		while(treeDivObj.className!='containerTableStyle'){
	 			treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		}
	 		treeDivObj =FindParentElement(treeDivObj,"DIV");
	 		
			treeEdit_Sys(eval(treeDivObj.id),"�༭... ");
            
	}
	
	
	function delete_Right(e){
	}
	function delete_Right4Tree(e){
		var treeDivObj =FindParentElement(e.element(),"DIV");
 		while(treeDivObj.className!='containerTableStyle'){
 			treeDivObj =FindParentElement(treeDivObj,"DIV");
 		}
 		treeDivObj =FindParentElement(treeDivObj,"DIV");
 		
		TreeDel_Sys(eval(treeDivObj.id),"�༭... ");
	}
	
	
	
	
	function copy_Right(e){
			var tagName = e.element().tagName.toLowerCase(),
            x = e.screenX,
            y = e.screenY;
            alert('you clicked on <' + tagName + '> element at x: ' + x + ', and y: ' + y);
	}
	
	
	
	 var myMenuItems = [
          {
            name: '����',
            className: 'new',
            callback: create_Right4Tree
          },{
            name: '�༭',
            className: 'edit', 
            callback: edit_Right4Tree
          },{
            name: 'ɾ��', 
            className:'delete',
            callback: delete_Right4Tree
            
          },{
            separator: true
          },{
            name: '����',
            className: 'copy', 
            callback: copy_Right
          }
        ];
        
	function createContextMenu_Sys(target,content){
		if(content==null) content=myMenuItems;
		return new Proto.Menu({
          selector: target,//һ��cssѡ����������ʹ�õ�
          className: 'menu desktop',
          menuItems: content
        });
	}
		
	/*
	 document.observe('dom:loaded', function(){
        new Proto.Menu({
          selector: '#test',//һ��cssѡ����������ʹ�õ�
          className: 'menu desktop',
          menuItems: myMenuItems
        });
      });
	*/