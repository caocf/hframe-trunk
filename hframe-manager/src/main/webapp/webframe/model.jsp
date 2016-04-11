<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <div class="modal-body">
      
      <zqh:myform formid="full1_form_test111" view="full_form_test" colNum="4" title="测试" object="<%=null %>" editable="true" width="1800" defaultBtn="save,reset,back,ajax"  isAjax="">
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="保存"></zqh:button>
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="重置"></zqh:button>
	</zqh:myform>
       		<iframe id="null"
						src="test/createDialog.jsp?VIEW=diary_group&TYPE=FORM&CONDITION=&TITLE=我日"
						frameborder="0" style="height: 550px; width: 800px;"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Send message</button>
      </div>
    </div>
  </div>
</div>
 -->

<zqh:model id ="123123">
  <zqh:myform formid="full1_form_test111" view="full_form_test" colNum="4" title="测试" object="<%=null %>" editable="true" width="1800" defaultBtn="save,reset,back,ajax"  isAjax="">
		<!-- <center>  -->
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="保存"></zqh:button>
		<zqh:button src="" model="action-save" buttonId="111" icon="" buttonName="111" title="重置"></zqh:button>
		<!-- </center>  -->
	</zqh:myform>
</zqh:model>
		
		
<script style="text/javascript" >
  
  $(function(){
  // $('#123123').modal({
     //  keyboard: true
  //})
  });
 
</script>