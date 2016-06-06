require(['layer','ajax','js/hframework/errormsg'], function () {

    var layer = require('layer');
    var ajax = require('ajax');
    var errormsg = require('js/hframework/errormsg');

    $(".hflist-pager-button").live("click",function(){
        var pageNo = $(this).attr("pageNo");
        var compoContainer = $(this).parents("[page][module]")[0];
        var module = $(compoContainer).attr("module");
        var page =$(compoContainer).attr("page");
        var component  =$(compoContainer).attr("component");
        var _url =  "/" + module + "/" + page + ".html";
        var _data = {"pageNo":pageNo,"component" : component};
        ajax.Post(_url,_data,function(data){
            var $newHfList = $(data);
            $(compoContainer).find(".hflist-pager").html($newHfList.find(".hflist-pager").html());
            $(compoContainer).find(".hflist-data").html($newHfList.find(".hflist-data").html());
        },'html');
    });
});

