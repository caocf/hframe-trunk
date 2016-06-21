require(['layer','ajax','js/hframework/errormsg','js/hframework/list'], function () {
    var layer = require('layer');
    var ajax = require('ajax');
    var flist = require('js/hframework/list');

    $('form').submit(function(){
        return false;
    });

    $(".hfhref").live("click", function(){
        $action =JSON.parse($(this).attr("action"));
        $param  = formatContent($(this).attr("params"), $(this));
        doEvent($action, $param, $(this));
    });

    function doEvent($action, $param, $this){
        $type = null;
        for(type in $action) {
            $type = type;
            break;
        }

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
        }else if($type == "ajaxSubmit") {
            var _data;
            if($param == "thisForm") {
                $thisForm = $this.parents("form")[0];
                _data = parseUrlParamToObject($($thisForm).serialize());
            }else {
                _data = parseUrlParamToObject($param);
            }
            console.log(_data);
            //_data = {"hfmdEntityAttrId":"","hfmdEntityAttrName":"1231232132","hfmdEntityAttrCode":"","hfmdEntityAttrDesc":"","attrType":"","size":"","ispk":"","nullable":"","isBusiAttr":"","isRedundantAttr":"","relHfmdEntityAttrId":"","hfmdEnumClassId":"","pri":"","hfpmProgramId":"","hfpmModuleId":"","hfmdEntityId":"","opId":"","createTime":"2015-02-13 12:12:12","modifyOpId":"","modifyTime":"2015-02-13 12:12:12","delFlag":""};
            ajax.Post(url,_data,function(data){
                if(data.resultCode != '0') {
                    alert(data.resultMessage);
                    return;
                }

                delete $action[$type];
                doEvent($action,$param,$this);
            });
        }else if($type == "component.reload") {

            var targetId = $action[$type].targetId;
            if(targetId) {
                $targetComponent = $("[component=" + targetId + "]");
                $thisForm = $this.parents("form")[0];
                $targetComponent.attr("param",$($thisForm).serialize());
                alert($($thisForm).serialize());
                delete $action[$type];
                refreshList(1,$targetComponent);
            }
        }else if($type == "dialog") {
            showDialog(url + "?" + "isPop=true&" +$param,function(){
                delete $action[$type];
                doEvent($action,$param,$this);
            });
        }
        //delete $action[$type];
    }

    var refreshList = function(pageNo, compoContainer){
        var module = $(compoContainer).attr("module");
        var page =$(compoContainer).attr("page");
        var component  =$(compoContainer).attr("component");
        var param  =$(compoContainer).attr("param");
        var _url =  "/" + module + "/" + page + ".html";
        var _data = {"pageNo":pageNo,"component" : component};
        if(param) {
            var params =JSON.parse("{\"" + (param + "&1=1").replace(new RegExp("=","gm"),"\":").replace(new RegExp("&","gm"),",\"").replace(new RegExp(":,","gm"),":null,")  + "}");
            for (var key in params) {
                _data[key]=params[key];
            }
        }
         console.log(_data);
        //alert(_data);
        ajax.Post(_url,_data,function(data){
            var $newHfList = $(data);
            $(compoContainer).find(".hflist-pager").html($newHfList.find(".hflist-pager").html());
            $(compoContainer).find(".hflist-data").html($newHfList.find(".hflist-data").html());
        },'html');
    }

    function parseUrlParamToJson($paramStr){
        return JSON.stringify(parseUrlParamToObject($paramStr));
    }

    function parseUrlParamToObject($paramStr){
        var result = {};
        $params = $paramStr.split("&");
        for($index in $params) {
            var key = $params[$index].substring(0, $params[$index].indexOf("="));
            var value = $params[$index].substring($params[$index].indexOf("=") + 1);
            if(value != '') {
                result[key] = value;
            }

        }
        return result;
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

