require(['layer','ajax','js/hframework/errormsg'], function () {

    var layer = require('layer');
    var ajax = require('ajax');
    var errormsg = require('js/hframework/errormsg');

    $(".hflist-pager-button").live("click",function(){
        var pageNo = $(this).attr("pageNo");
        var compoContainer = $(this).parents("[page][module]")[0];
        RefreshList(pageNo,compoContainer);
    });


    $(".hflist select").live("change", function(){
        $this = $(this);
        valueChange($this);
    });
    $(".hfform select").live("change", function(){
        $this = $(this);
        valueChange($this);
    });



    $('.hfselect').live('input', function(){
        $this = $(this);
        valueChange($this);
    });


    function valueChange($this){

        $code = $this.attr("name");
        $value =$this.val();
        //alert($code + "=" + $value);
        var $allRules;
        if($this.parents(".hfform").size() > 0) {
            $allRules = JSON.parse($(".hfform .ruler").text());
        }else {
            $allRules = JSON.parse($(".hflist .ruler").text());
        }

        $curRules = $allRules[$code + "=" + $value];
        if(!$curRules) {
            $curRules = $allRules[$code];
        }
        doRulerEvent($this, $curRules, $value);

        $allRules = JSON.parse($(" .helper").text());
        $curRules = $allRules[$code];

        var $targetComponent = $(" .helper").parents("[component]")[0];
        doHelperEvent($this, $curRules, $value,$targetComponent);
    }

    function doHelperEvent($this, $curRules,$value,$targetComponent) {
        if(!$curRules || !$value) {
            return ;
        }


        var $targetCode = $curRules["targetCode"];
        var $editable = $curRules["editable"];
        RefreshListHelper(1,$targetComponent,$targetCode,$value);
        //for(var $index in $curRules) {
        //    var $rule = $curRules[$index];
        //    var $targetCode = $rule["targetCode"];
        //    var $editable = $rule["editable"];
        //    RefreshListHelper(1,$targetComponent,$targetCode,$value);
        //}

        return;
    }

    function doRulerEvent($this, $curRules,$value) {
        if(!$curRules || !$value) {
            return ;
        }
        if($curRules[0]["ruleType"] == "2") {
            var dataCode = $this.attr("data-code");
            var _url =  "/queryOne.json";
            var _data = {"dataCode":dataCode,"dataValue" : $value};
            ajax.Post(_url,_data,function(data){
                if(data.resultCode == 0) {
                    for(var $index in $curRules) {
                        var $rule = $curRules[$index];
                        var $targetCode = $rule["targetCode"];
                        var $targetValue = $rule["targetValue"];
                        var $editable = $rule["editable"];
                        var $target;
                        if($this.parents(".hfform").size() > 0) {
                            $target =  $($this.parents(".hfform")[0]).find("[name=" + $targetCode + "]");
                        }else {
                            $target = getTargetElement($this.parents("tr"), "[name=" + $targetCode + "]");
                        }


                        $targetValue = $targetValue.substring($targetValue.indexOf("object.") + 7, $targetValue.length-1);
                        //console.info(data.data);
                        $target.val(data.data[$targetValue]);
                        if($editable == "false") {
                            $target.attr("disabled",true);
                        }
                    }
                }
            });
        }else {
            for(var $index in $curRules) {
                //console.info($curRules[$index]);
                var $rule = $curRules[$index];
                var $targetCode = $rule["targetCode"];
                var $targetValue = $rule["targetValue"];
                var $editable = $rule["editable"];
                var $ruleType = $rule["ruleType"];
                var $target = getTargetElement($this.parents("tr"), "[name=" + $targetCode + "]");
                if($ruleType == "1") {
                    $target.val($targetValue);
                }
                if($editable == "false") {
                    $target.attr("disabled",true);
                }
            }
        }
    }
    //$(".hflist select").each(function(){
    //    console.info($(this).html());
    //    $(this).change();
    //});

    function getTargetElement($parent, $selector) {
        return  $parent.find($selector);
        //if($parent.next()) {
        //    $target = $parent.next().find("[name=" + $targetCode + "]");
        //    if($target.size() == 0) {
        //        return getTargetElement($parent.next(), $selector)
        //    }
        //    return $target;
        //}
        //return null;
    }


    $(".hflist-tools-button").die().live("click",function(){
        $(this).toggleClass("badge-info");
        if(!$(this).hasClass("badge-info")) {
            $(this).css("opacity","0.3");
            $(this).find("i").attr("class","icon-minus-sign");

            var tagId = $(this).attr("tag-id");
            var id = $(".hflist-fast-data input[id$='Id']").eq(0).attr("id");

            $curRow =$(".hflist-fast-data input[id='" + id + "'][value='" + tagId + "']").parents("tr")[0];
            $newRow = $($curRow).clone();
            $($newRow).find("input[type=hidden]").val("");
            $newRow.find("input[value*='{'][value*='}']").each(function(){
                $(this).val(replaceVarChars($(this).val()));
            });
            $($curRow).find("select").each(function(i){
                $($newRow).find("select").eq(i).val($(this).val());
            });

            if($(".hflist-data").children(":last").find("input[value !='']").size() == 0 ||
                $(".hflist-data").children(":last").find("input[value !='']").val().length == 0 ) {
                $(".hflist-data").children(":last").remove()
            }

            $(".hflist-data").append($newRow);
            //
            //$($curRow).after($newRow);

        }else {
            $(this).css("opacity","1.0");
            $(this).find("i").attr("class","icon-plus-sign");
        }
    });

    function fastDataTagInit(){
        $(".hflist-fast-data").each(function(i){
            var id = $(this).find("input[id$='Id']").eq(0).attr("id");
            var name =$(this).find("input[id$='Name']").eq(0).attr("id");
            var code =$(this).find("input[id$='Code']").eq(0).attr("id");
            $this = $(this);
            $(".hflist-tools").html("");
            $this.find("input[id="+ id + "]").each(function(i){
                //alert($(this).val() + $this.find("input[id="+ name + "]").eq(i).val());
                var tagId = $(this).val();
                var tagName = $this.find("input[id="+ name + "]").eq(i).val();
                var tagValue = replaceVarChars($this.find("input[id="+ code + "]").eq(i).val());
                tagName = replaceVarChars(tagName);
                var isContain = false;

                $(".hflist-data").find("input[id='"+ code + "']").each(function(){
                    if($(this).val().toUpperCase() == tagValue.toUpperCase()) {
                        isContain = true;
                    }
                });
                //console.info(tagId,tagName,tagValue, isContain);
                var _html = [];
                _html.push('<div class="hflist-tools-button badge badge-info " tag-id="' + tagId + '" style="position: relative;cursor:pointer;margin-left: 3px;"><i class="icon-plus-sign"></i>' +  tagName + '</div>');
                var $child = $(_html.join(''));
                $child.appendTo($(".hflist-tools"));
                $(".hflist-tools").css("display","");

                if(isContain) {
                    $(".hflist-tools-button[tag-id='" + tagId + "']").toggleClass("badge-info");
                    $(".hflist-tools-button[tag-id='" + tagId + "']").css("opacity","0.3");
                    $(".hflist-tools-button[tag-id='" + tagId + "']").find("i").attr("class","icon-minus-sign");
                }
            });
        });
    }
    fastDataTagInit();

    function replaceVarChars(tagValue) {
        if(tagValue.indexOf("{") >= 0) {
            var var1 = tagValue.substring(tagValue.indexOf("{") + 1, tagValue.indexOf("}"));
            var varValue = $("#" + var1).val();
            if(varValue != "" && varValue.length > 0) {
                if(varValue.charAt(0) == varValue.charAt(0).toLowerCase()) {
                    tagValue = tagValue.replace("{" + var1 + "}",varValue).toLowerCase();
                }else {
                    tagValue = tagValue.replace("{" + var1 + "}",varValue).toUpperCase();
                }
            }else {
                tagValue = tagValue.replace("{" + var1 + "}",varValue);
            }

        }

        return tagValue;
    }


    var RefreshList = function(pageNo, compoContainer){
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

            $(compoContainer).find("[data-code][data-condition]").each(function(){
                var $this = $(this);
                $.selectLoad($this);
            });
        },'html');
    }

    var RefreshListHelper = function(pageNo, compoContainer, $targetCode, $value){
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
        _data[$targetCode]=$value;
        console.log(_data);
        //alert(_data);
        ajax.Post(_url,_data,function(data){
            var $newHfList = $(data);
            //console.log(data);
            $(compoContainer).find(".hflist-fast-data").html($newHfList.find(".hflist-fast-data").html());
            fastDataTagInit();
            $(compoContainer).find(".hflist-fast-data").find("[data-code][data-condition]").each(function(){
                var $this = $(this);
                $.selectLoad($this);
            });
        },'html');
    }

    return {
        RefreshList: RefreshList
    };
});

