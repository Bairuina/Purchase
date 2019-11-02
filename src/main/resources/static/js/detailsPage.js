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

//更新时间
$("#new").click(function () {
   var time = $("#BeginTime").val();
   var times= time.substring(6,10)+time.substring(0,2)+time.substring(3,5)+"000000";
   $.ajax({
       url:'/product/update/'+times,
       type:'POST',
       success:function (data) {
           if(data==0){
               alert(data.msg)
               window.location.reload();
           }else{
               alert(data.msg);
           }
       }
   })
});






//时间插件
// ①默认成当天的日期
$('.BeginTimeDiv').datepicker("setDate", "-1d");//增加时间控件,-1d减少一天，-1m减少一个月，-1y减少一年
$(".BeginTimeDiv").datepicker("setDate", "null"); // null 代表着当天
$('.BeginTimeDiv').datepicker("setDate", "-1y,-1m,-1d");// 代表这个 年，月，日，各减少一个数字
// ②选择小于当天的日期
$('#BeginTime').datepicker({
    autoclose:true,//选中日期后日期框自动消失
    clearBtn:true,//提供清除按钮，可以清除input框中日期
    language:"zh",//日期框显示语言
    orientation:"top",//日期框显示位置
    todayBtn:false,//是否显示今天按钮
    endDate:"-1d" //小于当天的日期的设置  endDate: new Date() 今天的日期和以前的能选择
});
var defaults = $.fn.datepicker.defaults = {
    autoclose: false,
    beforeShowDay: $.noop,
    calendarWeeks: false,
    clearBtn: false,
    daysOfWeekDisabled: [],
    endDate: Infinity,
    forceParse: true,
    format: 'yyyy年mm月dd日',
    keyboardNavigation: true,
    language: 'zh-cn',
    minViewMode: 0,
    multidate: false,
    multidateSeparator: ',',
    orientation: "auto",
    rtl: false,
    startDate: -Infinity,
    startView: 0,
    todayBtn: false,
    todayHighlight: false,
    weekStart: 0
};




