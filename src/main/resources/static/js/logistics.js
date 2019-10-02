$(".logisticsDetail").click(function(){
    var top = $(document).scrollTop();
    $(document).on('scroll.unable',function (e) {
        $(document).scrollTop(top);
    });
    $(".logisticsContainer").css("display","block");
});
$(".logisticsImg").click(function(){
    $(".logisticsContainer").css("display","none");
});
//
// //时间
//     function getNowFormatDate() {
//         var date = new Date();
//         var seperator1 = "-";
//         var year = date.getFullYear();
//         var month = date.getMonth() + 1;
//         var strDate = date.getDate();
//         var hour = date.getHours();
//         var m = date.getMinutes() ;
//         var s = date.getSeconds() ;
//         if (month >= 1 && month <= 9) {
//             month = "0" + month;
//         }
//         if (strDate >= 0 && strDate <= 9) {
//             strDate = "0" + strDate;
//         }
//         var a = ["日", "一", "二", "三", "四", "五", "六"];
//         var week = date.getDay();
//         var str = "星期"+ a[week];
//         var currentdate = year + seperator1 + month + seperator1 + strDate ;
//         console.log(currentdate);
//         document.getElementsByClassName('nowdate')[0].innerHTML =
//         // return currentdate;
//     }
// document.getElementsByClassName('nowdate')[0].innerHTML=getNowFormatDate();