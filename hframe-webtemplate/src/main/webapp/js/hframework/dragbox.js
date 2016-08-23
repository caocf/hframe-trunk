require(['layer','ajax','js/hframework/errormsg'], function () {
    var layer = require('layer');
    var ajax = require('ajax');

    $(".dragbox").shapeshift({
    });


    $(".addGrid").bind("click",function(){
        var $newGrid = $('<div class="dragbox"></div>');
        $(this).parent(".dragbox-oper").before($newGrid);

        $($newGrid).shapeshift({
            colWidth: 100
        });
    });

    $(".submitDraw").bind("click",function(){
        var array = [];
        $(this).parent().parent().find(".dragbox").each(function(){
            var dataIds = "";
            $(this).find("div[data-id]").each(function(){
                dataIds = dataIds  + $(this).attr("data-id") + ",";
            });
            array.push(dataIds);
        });

        var _url = "/extend/page_generate.json";
        var _data = {};
        _data["dataIds"] = array;
        _data["moduleCode"] = $(this).parents(".dragbox-area[module-code]").attr("module-code");
        ajax.Post(_url,_data,function(data){
            if(data.resultCode != '0') {
                alert(data.resultMessage);
                return;
            }
            alert(data.resultMessage);
            window.location.reload();
        });
        console.info(_data);
    });

});

