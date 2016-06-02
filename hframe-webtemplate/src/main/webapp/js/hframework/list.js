require(['layer','ajax','js/hframework/errormsg'], function () {

    var layer = require('layer');
    var ajax = require('ajax');
    var errormsg = require('js/hframework/errormsg');

    $(".hflist-pager-button").click(function(){
        var pageNo = $(this).attr("pageNo");
        var compoContainer = $(this).parents("[dataset][module]")[0];
        var module = $(compoContainer).attr("module");
        var dataset =$(compoContainer).attr("dataset");
        var _url = module + "/" + dataset + "list";
        var _data = {"pageNo":pageNo};
        ajax.Post(_url,_data,function(data){
            $(this).parent("hflist-pager").html(data.paper);
            $(this).parent("hflist-data").html(data.data);
        });
    });
});

