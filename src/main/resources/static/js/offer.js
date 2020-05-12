//提交ajax请求
$('.confirm').click(function () {
   var number = $(".commodityNumber").data("number");
   console.log(number);
   var price = $('#offerInputContent').val();
   console.log(price);
   //自有价格
   var ownPrice = $('#oneselfPrice').val();
   console.log(ownPrice);
   var content = $("#offerTextareaContent").val();
   console.log(content);
   var addressText = $("#address").val();
   var address = $(".address").text();
   console.log(addressText);
   var attention = $('.attention').text();
   var attentionText = $("#attention").val();
   console.log(attentionText);
   var aa = /(^[\-0-9][0-9]*(.[0-9]+)?)$/;
   //地址的提示框
   var alertAddress = $("#alertAddress").text();
   //获取省份的编号
   var addressnumber =  $(".selectAddress").find("option:selected").data("addressnumber");
   //价格后的提示框
   //获取省份
   var addressname =  $(".selectAddress").find("option:selected").text();

   if(price!=''){
      if(aa.test(price)){
         $("#priceHint").text("");
         $("#textHint").text("");
         $(".attention").text("");
         $(".address").text("");
         $("#alertAddress").text('');
         $.ajax({
            url:'/productoffer/offer',
            data:{
               xhbh:number,
               price:price,
               text:content,
               fwcn:attentionText,
               productlink:addressText,
               area:addressnumber,
               zyjg:ownPrice
            },
            type:'PUT',
            success:function (data) {
               console.log(addressname)
               if(data.code==0){
                  alert(data.msg);
                  window.location.reload();
               }else{
                  alert(data.msg);
                  window.location.reload();
               }
            }
         });
      }else{
         $("#priceHint").text("格式不正确");
      }}
   else if(price==''){
      $("#priceHint").text("请输入价格");
   }else if(addressname==""){
      $("#alertAddress").text("请输入省份");
   }else if(addressText==''){
      $(".address").text("请输入商品地址");
   }else if(content==''){
      $("#textHint").text("请输入备注");
   }else if(attention==''){
      $(".attention").text("请输入该商品售后问题");
   }
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

   var aa = /(^[\-0-9][0-9]*(.[0-9]+)?)$/;
   $(this).prev().prev().find("span").text();
   if(accessoriesText!='' && accessoriesTextarea!=''){
      if(aa.test(accessoriesText)){
         //备注提示清空
         $(this).prev().find('span').text('');
         //价格提示清空
         $(this).prev().prev().find('span').text('');
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
      }else{
         //价格提示清空
         $(this).prev().prev().find('span').text('格式不正确');
      }
   }else if(accessoriesText==''){
      $(this).prev().prev().find('span').text('无价格');
   }else if(accessoriesTextarea==''){
      $(this).prev().find('span').text('无备注');
   }



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
   var aa = /(^[\-0-9][0-9]*(.[0-9]+)?)$/;
   //提示
   var serveprice = $(this).prev().prev().find("span").text();
   var serveText = $(this).prev().find("span").text();


   if(addText!=''&&addTextarea!=''){
      //备注提示清空
      $(this).prev().find('span').text('');
      //价格提示清空
      $(this).prev().prev().find('span').text('');
      if(aa.test(addText)){
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
      }else{
         $(this).prev().prev().find("span").text("格式不正确");
      }
   }else if(serveprice==''){
      $(this).prev().prev().find("span").text("无价格");
   }else if(addTextarea==''){
      $(this).prev().find("span").text("无备注");
   }else if(addText==''){
      $(this).prev().prev().find("span").text("无价格");
   }
});