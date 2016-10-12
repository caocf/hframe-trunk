

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
    //$( ".datepicker" ).datepicker({
    //    dateFormat: "yy-mm-dd"
    //});

    $( ".datepicker" ).datetimepicker({
        showSecond: true,
        showMillisec: false,
        timeFormat: 'hh:mm:ss',
        dateFormat: "yy-mm-dd"
    });





}

$.datepicker.regional['ru'] = {
    closeText: '1',
    prevText: 2,
    nextText: 'След&#x3e;',
    currentText: 'Сегодня',
    monthNames: ['一月','二月','三月','四月','五月','六月',
        '七月','八月','九月','十月','十一月','十二月'],
    monthNamesShort: ['Янв','Фев','Мар','Апр','Май','Июн',
        'Июл','Авг','Сен','Окт','Ноя','Дек'],
    dayNames: ['воскресенье','понедельник','вторник','среда','четверг','пятница','суббота'],
    dayNamesShort: ['вск','пнд','втр','срд','чтв','птн','сбт'],
    dayNamesMin: ['日','一','二','三','四','五','六'],
    weekHeader: 'Не',
    dateFormat: 'yy-mm-dd',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: ''
};
$.datepicker.setDefaults($.datepicker.regional['ru']);

$.timepicker.regional['ru'] = {
    timeOnlyTitle: '1',
    timeText: '时间',
    hourText: '时',
    minuteText: '分',
    secondText: '秒',
    millisecText: '毫秒',
    timezoneText: '时区',
    currentText: '当前时间',
    closeText: '确定',
    timeFormat: 'HH:mm',
    amNames: ['AM', 'A'],
    pmNames: ['PM', 'P'],
    isRTL: false
};

$.timepicker.setDefaults($.timepicker.regional['ru']);
componentinit();

