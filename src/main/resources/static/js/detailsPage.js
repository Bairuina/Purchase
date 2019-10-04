$(".moreThan").on('click',function(){
    var content = $(this).find("span").text();
    if(content=="更多"){
        $(this).find("span").text("收起");
        $(this).parent().next().css('height',"auto");
    }else{
        $(this).find("span").text("更多");
        $(this).parent().next().css('height',"70px");
        if(arrows.src.match('up')){
            arrows.src="images/down.png"
        }
    }
});

//搜索框对焦时
$(".inputProduct").focus(function(){
    $(this).children().children().css("");
});

//搜索时的ajax请求
