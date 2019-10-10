
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
            window.location.reload();
         }else{
            alert(data.msg)
            window.location.reload();
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
            window.location.reload();
         }else{
            alert(data.msg);
            window.location.reload();
         }
      }
   })

});

//增值服务报价
$(".addServe").on('click',function () {
   //原价格
   var originAddName = $(this).prev().prev().prev().children().text();
   console.log("增值服务原价格"+originAddName);

   //增值服务编号
   var serialNumber = $(this).data('addnumber');
   console.log("增值服务编号"+serialNumber);
   //商品编号
   var number = $(".commodityNumber").data("number");
   console.log(number);
   //价格
   var addText = $(this).prev().prev().children().val();
   console.log(addText);
   //备注
   var addTextarea = $(this).prev().children().val();
   console.log(addTextarea);

   $.ajax({
      url:"/service-offer/Offer",
      data:{
         //增值服务编号
         fubh:serialNumber,
         //增值服务价格
         fujg:addText,
         //商品编号
         xhbh:number,
         //备注
         Text:addTextarea,
      },
      type:"PUT",
      success:function (data) {
         if (data.code==0){
            alert(data.msg);
            $(this).prev().prev().prev().children().text('originAddName');
            window.location.reload();
         }else{
            alert(data.msg);
            window.location.reload();
         }
      }
   })

});