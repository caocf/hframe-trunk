require(['layer','ajax','js/hframework/errormsg'], function () {

    var layer = require('layer');
    var ajax = require('ajax');
    var errormsg = require('js/hframework/errormsg');

    $("[data-code][data-condition]").each(function(){
        var $this = $(this);
        var $tagName = $this[0].tagName;
        var  dataCode = $this.attr("data-code");
        var  dataCondition = $this.attr("data-condition");
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
                    $this.html(_html.join(''));
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
            }
        });
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

