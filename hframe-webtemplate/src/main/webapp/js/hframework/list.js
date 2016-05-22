$(".hflist-pager-button").click(function(){
    var pageNo = $(this).attr("pageNo");
    var $component = $parent($this);
    var module = $component.attr("module");
    var dataset = $component.attr("dataset");
    $ajax({
        url:module + "/" + dataset + "list",
        param:{
            "pageNo":pageNo,
        },
        success:function(ret){
            $(this).parent("hflist-pager").html(ret.paper);
            $(this).parent("hflist-data").html(ret.data);
        }
    });
});