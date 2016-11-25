require(['layer','ajax','js/hframework/errormsg'], function () {

    var layer = require('layer');
    var ajax = require('ajax');
    var errormsg = require('js/hframework/errormsg');
    var loadingDictionaryKeys = {};

    $.textLoad =function($this){

    }

    $.selectLoad = function ($this, _func, batchLoad, _$container) {
        var $tagName = $this[0].tagName;
        var  dataCode = $this.attr("data-code");
        var  dataCondition = $this.attr("data-condition");
        var dataValue = $this.attr("data-value");
        var relatElement = $this.attr("relat-element");

        if(relatElement) {
            var elementName = relatElement.substring(relatElement.indexOf("{") + 1,relatElement.indexOf("}"))
            var $relElement;
            if($this.parents(".hfform").size() > 0) {
                $relElement =  $($this.parents(".hfform")[0]).find("[name=" + elementName + "]");
            }else if($this.parents("tr").size() > 0) {
                $relElement = $this.parents("tr").find("[name=" + elementName + "]");
            }else if($this.parents(".breadcrumb").size() > 0) {
                $relElement = $this.parents(".breadcrumb").find("[name=" + elementName + "]");
            }

            var relElementValue =$relElement.val();
            if(!relElementValue) {//由于使用依赖的元素也是通过ajax加载，对应的value值还不能正确取出
                relElementValue = $relElement.attr("data-value");
            }

            if(dataCondition) {
                dataCondition = dataCondition + " && " + relatElement.replace("{" + elementName + "}",  relElementValue);
            }else {
                dataCondition = relatElement + "=" +relatElement.replace("{" + elementName + "}",  relElementValue);
            }
        }

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
            if(loadingDictionaryKeys[_$container.attr("id") + dataCode + "|" + dataCondition] == null) {//首次加载
                loadingDictionaryKeys[_$container.attr("id") + dataCode + "|" + dataCondition] = -1;
            }else if(loadingDictionaryKeys[_$container.attr("id") + dataCode + "|" + dataCondition] == -1) {//正在加载过程中，服务端还未返回
                return;
            }else {//已有加载完成
                loadingDictionaryKeys[_$container.attr("id") + dataCode + "|" + dataCondition] = -1;;
            }
        }

        var _url =  "/dictionary.json";
        if(dataCode.startsWith("URL:")) {
            _url = dataCode.substring(4);
        }
        var _data = {"dataCode":dataCode,"dataCondition" : dataCondition};
        ajax.Post(_url,_data,function(data){
            if(data.resultCode == 0) {
                if($tagName == 'SELECT') {
                    var _html = [];
                    _html.push('<option value=""> - 请选择 - </option>');
                    if(data.data) {
                        for (var i = 0; i < data.data.length; i++) {
                            _html.push('<option value="' + data.data[i].value + '" data-hide=' + data.data[i].extInfo + '>' + data.data[i].text + '</option>');
                        }
                    }

                    var htmlStr = _html.join('');
                    if(batchLoad) {
                        loadingDictionaryKeys[_$container.attr("id") + dataCode + "|" + dataCondition] = 1;
                    }
                    if(data.data) {
                        if(batchLoad) {
                            _$container.find("select[data-code='" + dataCode + "'][data-condition='" + dataCondition + "']").each(function(){
                                $(this).html(htmlStr);
                                $(this).val($(this).attr("data-value"));
                                if((dataCode.startsWith("URL:") || dataCode.split(".").length > 2) && data.data.length > 10) { //选择框设置为selectx元素
                                    $(this).addClass("hfselectx");
                                    $(this).chosen();//设置为selectx
                                }

                                $(this).change();
                            });
                        }else {
                            $this.html(_html.join(''));
                            $this.val(dataValue);
                            if((dataCode.startsWith("URL:") || dataCode.split(".").length > 2) && data.data.length > 10) { //选择框设置为selectx元素
                                $this.addClass("hfselectx");
                                $this.chosen();//设置为selectx
                            }
                            $this.change();
                        }
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

    $.reloadDisplay = function (_$container) {
        var $elements = $(_$container).find("[data-code][data-condition]");
        $elements.each(function(){
            var $this = $(this);
            if($this.is('select')) {
                $.selectLoad($this,null,true,_$container);
            }else {
                $.selectPanelLoad($this);
            }
        });
    }
    $.reloadDisplay($("body"));

    $.reloadListDisplay = function () {
        listTextDisplay();
    }

    function listTextDisplay() {
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
                        if(data.data && data.data[$(this).text()] && data.data[$(this).text()].text) {
                            $(this).attr("value",$(this).text());
                            $(this).text(data.data[$(this).text()].text);
                        }
                    });
                }
            });
        });
    }
    listTextDisplay();


    //
    //$("[data-code][data-condition]").each(function(){
    //    var $this = $(this);
    //    if($this.is('select')) {
    //        $.selectLoad($this,null,true,$("body"));
    //    }else {
    //        $.selectPanelLoad($this);
    //    }
    //});

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

