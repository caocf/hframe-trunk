require(['layer','ajax','js/hframework/errormsg'], function () {
    var layer = require('layer');
    var ajax = require('ajax');
    //var flist = require('js/hframework/list');

    $('form').submit(function(){
        return false;
    });

    $("a").click(function(){
        var href = $(this).attr("href");
        if(href.endsWith(".json") || href.endsWith(".html")) {
            $(this).attr("orig-href", $(this).attr("href"));
            $(this).attr("href", $(this).attr("orig-href") + "?" +　getPageContextInfo());
        }

    });

    $(".hfhref").live("click", function(){
        $action =JSON.parse($(this).attr("action"));
        $param  = formatContent($(this).attr("params"), $(this));
        $contextValues = getPageContextInfo();
        if($contextValues) {
            $param = $contextValues + "&" + $param;
        }
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
        }else if($type == "alert") {
            var content = formatContent($action[$type].content,$this);
            showConfirmDialog(content,function(){
                delete $action[$type];
                doEvent($action,$param,$this);
            });
        }else if($type == "ajaxSubmitByJson") {
            var _data;
            var targetId =$action[$type].targetId;

            if(targetId != null) {
                var json = {};
                var targetIds = targetId.split(",");
               for(var tarId in targetIds) {
                   $component = $("[component= " + targetIds[tarId] +"]");
                   if($component.find("form").length > 0) {
                       json[targetIds[tarId]] = JSON.parse($($component.find("form")[0]).serializeJson());
                   }else {
                       var hierarchy = $component.orgchart('getHierarchy');
                       json[targetIds[tarId]]  = JSON.stringify(hierarchy, null, 2);
                   }

               }
                //console.log(JSON.stringify(json));
                _data = JSON.stringify(json);
            }else if($param == "thisForm") {
                $thisForm = $this.parents("form")[0];
                _data = $($thisForm).serializeJson();
            }else {
                _data = parseUrlParamToObject($param);
            }
            //_data ='[{"hfpmProgramId":"123","hfpmProgramName":"test","hfpmProgramCode":"234","hfpmProgramDesc":"234","opId":"234","createTime":"2015-10-31 00:20:58","modifyOpId":"","modifyTime":"2015-10-31 00:20:58","delFlag":""},{"hfpmProgramId":"151031375370","hfpmProgramName":"框架","hfpmProgramCode":"hframe","hfpmProgramDesc":"框架","opId":"999","createTime":"2015-10-31 00:20:58","modifyOpId":"999","modifyTime":"2015-10-31 00:20:58","delFlag":"0"}]';
            //console.log(_data);

            $.ajax({
                url: "/" + url,
                data: _data,
                type: 'post',
                contentType : 'application/json;charset=utf-8',
                dataType: 'json',
                success: function(data){
                    if(data.resultCode != '0') {
                        alert(data.resultMessage);
                        return;
                    }

                    delete $action[$type];
                    doEvent($action,$param,$this);
                }
            });

        }else if($type == "ajaxSubmit") {
            var _data = {};
            if($param == "thisForm") {
                $thisForm = $this.parents("form")[0];
                _data = parseUrlParamToObject($($thisForm).serialize());
            }else {
                if($($this).attr("params") == "checkIds") {
                    var checkIds = new Array();
                    var $thisList = $this.parents(".hflist")[0];
                    var $allChecked = $($thisList).find("input[type=checkbox][name=checkIds]:checked");
                    $allChecked.each(function(){
                        var columnName = $(this).attr("value-key");
                        var columnValue  = formatContent("{" + columnName + "}", $(this));
                        checkIds.push(columnValue);
                    });

                    _data["checkIds"] = checkIds;

                }else {
                    $thisForm = $this.parents("form")[0];
                    _data = parseUrlParamToObject($param);
                    tmpArray = parseUrlParamToObject($($thisForm).serialize());
                    for(var $index in tmpArray) {
                        _data[$index] = decodeURIComponent(tmpArray[$index]);
                    }
                }



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
            $targetComponent = $this.parents("[component]")[0]
            if(targetId) {
                $targetComponent = $("[component=" + targetId + "]");
            }

            if($this.parents("form").size() > 0) {
                $thisForm = $this.parents("form")[0];
                $($targetComponent).attr("param",$($thisForm).serialize());
            }

            //$thisForm = $this.parents("form")[0];
            //$($targetComponent).attr("param",$($thisForm).serialize());
            //alert($($thisForm).serialize());
            delete $action[$type];
            refreshList(1,$targetComponent);
        }else if($type == "page.reload") {
            delete $action[$type];
            location.reload();
        }else if($type == "component.row.add") {
            $curRow = $this.parents("tr")[0];
            $newRow = $($curRow).clone();
            $($newRow).find("input").val("");
            $($curRow).find(".hfselect").each(function(i){
                var $target = $($newRow).find(".hfselect").eq(i);
                $target.removeClass("city-picker-input");
                $target.next().remove();
                $target.next().remove();
                //$target.citypicker.Constructor

                $.selectPanelLoad($target);;
            });
            $($curRow).after($newRow);

        }else if($type == "component.row.copy") {
            $curRow = $this.parents("tr")[0];
            $newRow = $($curRow).clone();
            $($newRow).find("input[type=hidden]").val("");
            $($curRow).find("select").each(function(i){
                $($newRow).find("select").eq(i).val($(this).val());
            });
            $($curRow).find(".hfselect").each(function(i){
                var $target = $($newRow).find(".hfselect").eq(i);
                $target.removeClass("city-picker-input");
                $target.next().remove();
                $target.next().remove();
                //$target.citypicker.Constructor

                  $.selectPanelLoad($target);;
            });
            $($curRow).after($newRow);
        }else if($type == "component.row.up") {
            $curRow = $this.parents("tr")[0];
            $targetRow = $($curRow).prev();
            $newRow = $($targetRow).clone();
            $($targetRow).find("select").each(function(i){
                $($newRow).find("select").eq(i).val($(this).val());
            });
            $($curRow).after($newRow);
            $targetRow.remove();

        }else if($type == "component.row.down") {
            $curRow = $this.parents("tr")[0];
            $targetRow = $($curRow).next();
            $newRow = $($targetRow).clone();
            $($targetRow).find("select").each(function(i){
                $($newRow).find("select").eq(i).val($(this).val());
            });
            $($curRow).before($newRow);
            $targetRow.remove();
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
            console.log("{\"" + (param + "&1=1").replace(new RegExp("=","gm"),"\":").replace(new RegExp("&","gm"),",\"").replace(new RegExp(":,","gm"),":null,")  + "}");
            var params =JSON.parse("{\"" + (param + "&1=1").replace(new RegExp("=","gm"),"\":\"").replace(new RegExp("&","gm"),"\",\"").replace(new RegExp(":,","gm"),"\":null,")  + "\"}")
            //var params =JSON.parse("{\"" + (param + "&1=1").replace(new RegExp("=","gm"),"\":").replace(new RegExp("&","gm"),",\"").replace(new RegExp(":,","gm"),":null,")  + "}");
            for (var key in params) {
                _data[key]=decodeURI(params[key]).trim();
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
        if(!$paramStr) {
            return result;
        }
        $params = $paramStr.split("&");
        for($index in $params) {
            var key = $params[$index].substring(0, $params[$index].indexOf("="));
            var value = $params[$index].substring($params[$index].indexOf("=") + 1);
            if(value != '') {
                //if(result[key] != null) {
                //    if(result[key] instanceof Array) {
                //        result[key].push(value);
                //    }else {
                //        result[key] = [result[key],value];
                //    }
                //}else {
                    if(key == "createTime" || key == "modifyTime") {
                        continue;
                    }
                    result[key] = value;
                //}
            }
        }
        return result;
    }

    function formatContent($param, $this){
        if($param) {
            $position = $param.substring($param.indexOf("{") + 1, $param.indexOf("}"));
            $value = $this.parents("tr").find("span[code="+ $position +"]").text();
            return $param.replace("{" + $position +"}",$value);
        }
       return null;
    }

    function getPageContextInfo(){
        $pageContextValues = $($("#breadcrumb").find("form")[0]).serialize();
        return $pageContextValues;
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

    (function($){
        $.fn.serializeJson = function(){
            var jsonData1 = {};
            var serializeArray = this.serializeArray();
            // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
            $(serializeArray).each(function () {
                if (jsonData1[this.name] != null) {
                    if ($.isArray(jsonData1[this.name])) {
                        jsonData1[this.name].push(this.value);
                    } else {
                        jsonData1[this.name] = [jsonData1[this.name], this.value];
                    }
                } else {
                    jsonData1[this.name] = this.value;
                }
            });
            // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
            var vCount = 0;
            // 计算json内部的数组最大长度
            for(var item in jsonData1){
                var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
                vCount = (tmp > vCount) ? tmp : vCount;
            }

            if(vCount > 1) {
                var jsonData2 = new Array();
                for(var i = 0; i < vCount; i++){
                    var jsonObj = {};
                    for(var item in jsonData1) {
                        jsonObj[item] = jsonData1[item][i];
                    }
                    jsonData2.push(jsonObj);
                }
                return JSON.stringify(jsonData2);
            }else{
                return "[" + JSON.stringify(jsonData1) + "]";
            }
        };
    })(jQuery);
});

