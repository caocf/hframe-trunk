

function componentinit(){
    $(".hfmutexcont .box-content .box-header .box-icon").html("");
    $(".hfmutexcont .box-content .box-header").removeClass("box-header");

    $(".hftab .box-content .nav-tabs").each(function(){
        $(this).find("li:first").addClass("active");
    });
    $(".hftab .box-content .tab-content").each(function(){
        $(this).find("div:first").addClass("active");
    });

    $(".hftab  .box-content .nav-tabs a").click(function(b) {
        b.preventDefault();
        $(this).tab("show");
    });

    $(".hflist  .box-content .hflist-data a[when][when!='{}']").each(function(){
        $(this).hide();
       var conditions = JSON.parse( $(this).attr("when"));
        for(var key in conditions) {
            var $span = $(this).parent("td").parent("tr").find("span[code='"+ key +"']");
            var value = conditions[key];
            if($span.attr("value") == value || $span.text() == value) {
                $(this).show();
            }
        }
    });

    $(".hflist  .box-content .hflist-data tr td").each(function(){
        var count = 0;
        var $this = $(this);
        $(this).find("a").each(function(){
            if( $(this).attr("when") &&  $(this).attr("when") !="{}") {
                var conditions = JSON.parse( $(this).attr("when"));
                for(var key in conditions) {
                    var $span = $(this).parent("td").parent("tr").find("span[code='"+ key +"']");
                    var value = conditions[key];
                    if($span.attr("value") == value || $span.text() == value) {
                        count = count + 1;
                    }
                }
            }else {
                count = count + 1;
            }
        });
        if(count > 3) {
            $($this).attr("width",count*50 + "px;");
        }

    });
}
componentinit();