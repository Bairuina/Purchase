// $('.select').on('click',function(){
//    $(this).next().slideToggle() ;
//    var selectText = $(this).children().find(span).text();
//    // console.log(selectText);
// });
//提交ajax请求
$('.confirm').click(function () {

   var number = $(".commodityNumber").data("number");
   console.log(number);
   var price = $('#offerInputContent').val();
   console.log(price);
   var content = $("#offerTextareaContent").val();
   console.log(content);
   $.ajax({
      url:'/productoffer/offer',
      data:{
         xhbh:number,
         price:price,
         text:content
      },
      type:'PUT',
      success:function (data) {
         if(data.code==0){
            alert(data.msg);
         }else{
            alert(data.msg)
         }
      }
   });
});

//配件报价
$('.submitPrice').on('click',function () {
    //原价格
   var originName = $(this).prev().prev().prev().children().text();
   console.log(originName);
    //价格
    var accessoriesText = $(this).prev().prev().children().val();
    console.log(accessoriesText);
    //备注
    var accessoriesTextarea = $(this).prev().children().val();
   console.log(accessoriesTextarea);
   //配件编号
   var accessoriesNumber = $('.accessoriesnumber').data("accessories");
   console.log(accessoriesNumber);

   //商品编号
   var number = $(".commodityNumber").data("number");
   console.log(number);
   $.ajax({
      url:"/parts-offer/offer",
      data:{
         pjbh:accessoriesNumber,
         xhbh:number,
         price:accessoriesText,
         text:accessoriesTextarea,
      },
      type:"PUT",
      success:function (data) {
         if (data.code==0){
            alert(data.msg);
            $(this).prev().prev().prev().children().text('accessoriesText');
         }else{
            alert(data.msg);
         }
      }
   })

});

// //增值服务报价
// $(".addServe").on('click',function () {
//    //原价格
//    var originAddName = $(this).prev().prev().prev().children().text();
//    console.log(originAddName);
//    //价格
//    var addText = $(this).prev().prev().children().val();
//    console.log(addText);
//    //备注
//    var addTextarea = $(this).prev().children().val();
//    console.log(addTextarea);
//    //配件编号
//    var accessoriesNumber = $('.accessoriesnumber').data("accessories");
//    //商品编号
//    var number = $(".commodityNumber").data("number");
//    console.log(number);
//    console.log(accessoriesNumber);
//    $.ajax({
//       url:"/service-offer/Offer",
//       data:{
//          pjbh:accessoriesNumber,
//          xhbh:number,
//          price:accessoriesText,
//          text:accessoriesTextarea,
//       },
//       type:"PUT",
//       success:function (data) {
//          if (data.code==0){
//             alert(data.msg);
//             $(this).prev().prev().prev().children().text('accessoriesText');
//          }else{
//             alert(data.msg);
//          }
//       }
//    })
//
// });