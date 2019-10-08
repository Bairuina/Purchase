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