require(['layer','ajax','js/hframework/errormsg'], function () {
    var layer = require('layer');
    var ajax = require('ajax');

    $(".hfhref").click(function(){
        $action =JSON.parse($(this).attr("action"));
        $param  = formatContent($(this).attr("params"), $(this));
        doEvent($action, $param, $(this));
    });

    function doEvent($action, $param, $this){
        for($type in $action) {
            var url = $action[$type].action;
            if($type == "pageFwd") {
                var isStack =$action[$type].isStack;
                location.href = url + "?" + $param;
            }else if($type == "confirm") {
                var content = formatContent($action[$type].content,$this);
                showConfirmDialog(content,function(){
                    delete $action[$type];
                    doEvent($action,$param,$this);
                });
                break;
            }else if($type == "ajaxSubmit") {
                ajax.Post(url,parseUrlParamToJson($param),function(data){
                    delete $action[$type];
                    doEvent($action,$param,$this);
                });
            }else if($type == "component.reload") {

            }else if($type == "dialog") {
                showDialog(url + "?" + $param,function(){
                    delete $action[$type];
                    doEvent($action,$param,$this);
                });
                break;
            }
            delete $action[$type];
        }
    }

    function parseUrlParamToJson($paramStr){
        var result = {};
        $params = $paramStr.split("&");
        for($index in $params) {
            var key = $params[$index].substring(0, $params[$index].indexOf("="));
            var value = $params[$index].substring($params[$index].indexOf("=") + 1);
            result[key] = value;
        }
        return JSON.stringify(result);
    }

    function formatContent($param, $this){
        $position = $param.substring($param.indexOf("{") + 1, $param.indexOf("}"));
        $value = $this.parents("tr").find("span[code="+ $position +"]").text();
        return $param.replace("{" + $position +"}",$value);
    }

    function showDialog(url, ok){
        layer.open({
            area: ['766px', '510px'],
            type: 2,
            title: false,
            closeBtn: 0,
            content: url,
            success: function (l, i){
                l.find(".btn").on('click', function(){
                    layer.closeAll();
                    ok();
                    return true;
                });
                l.find('.hfconfirm-btn-cancel').on('click', function(){
                    layer.closeAll();
                    return false;
                    //location.reload();
                });
            }
        });
    }

    function showConfirmDialog(msg, ok){
        var _tpl = $('#myModal').html();
        layer.open({
            area: ['510px', '170px'],
            type: 1,
            title: false,
            closeBtn: 0,
            content: _tpl,
            success: function (l, i){
                $('.hfconfirm-content').html(msg);
                $('.hfconfirm-btn-ok').on('click', function(){
                    layer.closeAll();
                    ok();
                    return true;
                });
                $('.hfconfirm-btn-cancel').on('click', function(){
                    layer.closeAll();
                    return false;
                    //location.reload();
                });
            }
        });
    }
});

