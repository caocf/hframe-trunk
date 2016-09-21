require(['layer','ajax','js/hframework/errormsg'], function () {

    var layer = require('layer');
    var ajax = require('ajax');
    var errormsg = require('js/hframework/errormsg');
    var loadingDictionaryKeys = {};

    $.textLoad =function($this){

    }

    $.selectLoad = function ($this, _func, batchLoad) {
        var $tagName = $this[0].tagName;
        var  dataCode = $this.attr("data-code");
        var  dataCondition = $this.attr("data-condition");
        var dataValue = $this.attr("data-value");

        if(dataCode.startsWith("JSON:")) {
            var enums  =JSON.parse(dataCode.substr(5).replace(new RegExp(/(')/g),'"'));
            var _html = [];
            _html.push('<option value=""> - 请选择 - </option>');
            for(var key in enums) {
                _html.push('<option value="' + key + '">' + enums[key] + '</option>');
            }
            $this.html(_html.join(''));
            $this.val(dataValue);
            try{
                $this.change();
            }catch(e){
            }

            return ;
        }

        if(batchLoad) {
            if(loadingDictionaryKeys[dataCode + "|" + dataCondition] == null) {//首次加载
                loadingDictionaryKeys[dataCode + "|" + dataCondition] = -1;
            }else if(loadingDictionaryKeys[dataCode + "|" + dataCondition] == -1) {//正在加载过程中，服务端还未返回
                return;
            }else {//已有加载完成
                loadingDictionaryKeys[dataCode + "|" + dataCondition] = -1;;
            }
        }

        var _url =  "/dictionary.json";
        var _data = {"dataCode":dataCode,"dataCondition" : dataCondition};
        ajax.Post(_url,_data,function(data){
            if(data.resultCode == 0) {
                if($tagName == 'SELECT') {
                    var _html = [];
                    _html.push('<option value=""> - 请选择 - </option>');
                    for (var i = 0; i < data.data.length; i++) {
                        _html.push('<option value="' + data.data[i].value + '" data-hide=' + data.data[i].extInfo + '>' + data.data[i].text + '</option>');
                    }
                    var htmlStr = _html.join('');
                    loadingDictionaryKeys[dataCode + "|" + dataCondition] = 1;
                    if(batchLoad) {
                        $("select[data-code='" + dataCode + "'][data-condition='" + dataCondition + "']").each(function(){
                            $(this).html(htmlStr);
                            $(this).val($(this).attr("data-value"));
                            $(this).change();
                        });
                    }else {
                        $this.html(_html.join(''));
                        $this.val(dataValue);
                        $this.change();
                    }

                }else {

                    for (var i = 0; i < data.data.length; i++) {
                        $newNode = $($this.prop("outerHTML").replace("#text", data.data[i].text));
                        $input = $newNode.find("input");
                        $input.val(data.data[i].value);
                        $input.attr("id",$input.attr("name") + data.data[i].value);
                        $this.after($newNode);
                        //$this.after($this.clone());
                    }
                    $this.prop("outerHTML",$this.prop("outerHTML").replace("#value", "").replace("#text", "请选择"));
                }
                if(_func) {
                    _func();
                }

            }
        });
    }

    var treeDataCache = {};
    $.selectPanelLoad = function ($this, $option) {
        var  dataCode = $this.attr("data-code");
        var  dataCondition = $this.attr("data-condition");
        var dataValue = $this.attr("data-value");

        if(treeDataCache[dataCode] == null) {
            var _url =  "/treeData.json";
            var _data = {"dataCode":dataCode,"dataCondition" : dataCondition, "dataValue" : dataValue};
            ajax.Post(_url,_data,function(data){
                if(data.resultCode == 0) {
                    //console.info(data.data);
                    treeDataCache[dataCode] = data.data.data;
                    $($this).val(data.data.disValue);
                    //$($this).attr("level","city");
                    $($this).citypicker(treeDataCache[dataCode], $option);

                    var event = document.createEvent('HTMLEvents'); //创建事件
                    event.initEvent("input", true, true); //设置事件类型为 input
                    console.info($this);
                    $this[0].dispatchEvent(event); //触发下 该事件（input 事件）
                    //$($this).val(data.data.disValue);
                    //$($this).change();
                    //window.ChineseDistricts = data.data;
                }
            });
        }else {
            $($this).citypicker(treeDataCache[dataCode], $option);
            var event = document.createEvent('HTMLEvents'); //创建事件
            event.initEvent("input", true, true); //设置事件类型为 input
            console.info($this);
            $this[0].dispatchEvent(event); //触发下 该事件（input 事件）
            //$($this).input();
            //$($this).change();
        }


    }

    $.reloadDisplay = function (_$this) {
        $(_$this).find("[data-code][data-condition]").each(function(){
            var $this = $(this);
            if($this.is('select')) {
                $.selectLoad($this);
            }else {
                $.selectPanelLoad($this);
            }
        });
    }

    $("th[code][dataCode][dataCode!='']").each(function(){
        var $this =$(this);
        var code = $(this).attr("code");
        var dataCode = $(this).attr("dataCode");
        var dataValues=[];
        $("span[code='" + code + "']").each(function(){
            if($(this).text()) {
                dataValues.push($(this).text());
            }
        });
        var _url =  "/getTexts.json";
        var _data = {"dataCode":dataCode, "dataValues" : dataValues};
        ajax.Post(_url,_data,function(data){
            if(data.resultCode == 0) {
               if($this.html().endsWith("ID")) {
                   $this.html($this.html().substring(0,$this.html().length-2));
               }
                $("span[code='" + code + "']").each(function(){
                    if(data.data[$(this).text()] && data.data[$(this).text()].text) {
                        $(this).attr("value",$(this).text());
                        $(this).text(data.data[$(this).text()].text);
                    }
                });

            }
        });
    });

    $("[data-code][data-condition]").each(function(){
        var $this = $(this);
        if($this.is('select')) {
            $.selectLoad($this,null,true);
        }else {
            $.selectPanelLoad($this);
        }
    });

    function dealData1(_source, _data, _list) {
        var _html = [];
        _html.push('<option value=""> - 请选择 - </option>');

        for (var i = 0; i < _list.length; i++) {
            _html.push('<option value="' + _list[i].value + '" data-hide=' + _list[i].remark + '>' + _list[i].name + '</option>');
        }
        _html.push('</select>');
        var $child = $(_html.join(''));
        $child.change(_data['onchange']);
        var $parent = $(_source).parent().html('')
        $child.appendTo($parent);

        var _value = $(_source).val();
        //alert(_value);
        $('select[name="' + _data["tagName"] + '"]').val(_value);

        if(_data['onload'] != null) {
            _data['onload']();
        }
    }
    //
    //var pageNo = $(this).attr("pageNo");
    //var compoContainer = $(this).parents("[page][module]")[0];
    //var module = $(compoContainer).attr("module");
    //var page =$(compoContainer).attr("page");
    //var component  =$(compoContainer).attr("component");
    //var _url =  "/" + module + "/" + page + ".html";
    //var _data = {"pageNo":pageNo,"component" : component};
    //ajax.Post(_url,_data,function(data){
    //    var $newHfList = $(data);
    //    $(compoContainer).find(".hflist-pager").html($newHfList.find(".hflist-pager").html());
    //    $(compoContainer).find(".hflist-data").html($newHfList.find(".hflist-data").html());
    //},'html');
});

