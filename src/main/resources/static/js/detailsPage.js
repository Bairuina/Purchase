$(".moreThan").on('click',function(){
    var content = $(this).find("span").text();
    if(content=="更多"){
        $(this).find("span").text("收起");
        $(this).parent().next().css('height',"auto");
    }else{
        $(this).find("span").text("更多");
        $(this).parent().next().css('height',"45px");
        if(arrows.src.match('up')){
            arrows.src="images/down.png"
        }
    }
});

//搜索框对焦时
$(".inputProduct").focus(function(){
    $(this).children().children().css("");
});


// $('.list1').on('click',function () {
//     //  获取点击的类名
//     var listName = $(this).children().data("listnumber");
//     console.log(listName);
//     if(listName==''){
//         listName=0;
//     }
//     //  获取品目的名字
//     var items = $('.namesOfParts').data("itemsnumber");
//     console.log(items);
//     if(items=='' ){
//         items=0;
//     }
// //    获取品牌
//     var brank = $('.brankName').data("branknumber");
//     console.log(brank);
//     if(brank==''){
//         brank=0;
//     }
// //    获取搜索框的内容
//     var inputMessage = $("#productSearch").val();
//     console.log(inputMessage);
//     //判断如果值为空则返回0
//     if(inputMessage==''){
//         inputMessage=0;
//     }
//     console.log(listName);
//     console.log(items);
//     console.log(brank);
//     console.log(inputMessage);
//     //发送数据请求
//     $.ajax({
//         url:'/product/'+listName+'/'+items+'/'+brank+'/'+inputMessage,
//         type:'GET',
//         data:{
//             lbbh:listName,
//             pmbh:items,
//             ppbh:brank,
//             nr:inputMessage
//         },
//         async:true,
//         success:function (data) {
//             alert(data)
//             if(data.code==0){
//             //    刷新当前页面
//                 console.log('/product/'+listName+'/'+items+'/'+brank+'/'+inputMessage);
//                 window.location.reload();
//             }
//         }
//     })
// });