// var data=window.location.href;
// var orderDataDdbh=$("#orderDataDdbh").text();
//
// if(data.match("/order-data/selectOneOrder")){
//     console.log("开始更新订单。。。");
//     $.ajax({
//         url:"/order-data/upOrderData",
//         type:"POST",
//         dataType:"JSON",
//         data:{
//             ddbh:orderDataDdbh,
//         },
//         success:function(data){
//             console.log(data);
//             if(data.code=='0'){
//                 console.log("确认订单成功！")
//                 window.onload;
//             }
//             else{
//                 console.log("确认订单失败！")
//             }
//         },
//     });
// }