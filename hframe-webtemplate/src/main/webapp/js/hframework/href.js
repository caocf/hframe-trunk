$(".hfhref").click(function(){
    var $component = $parent($this);
    var module = $component.attr("module");
    var dataset = $component.attr("dataset");
    var operate = $component.attr("operate");
    var params = $component.attr("params");
    var url = module + "/" + dataset + "/" + operate;
    gotoUrl(url,params);

});