$(".same").on('click',function(){
    var indexnumber = $(this).index();
    console.log(indexnumber);
    $(this).parent().next().children().children().eq(indexnumber).css('display','block').siblings().css('display','none');
});

//撤销请求的ajax
$(".repeal").on('click',function () {
    var number = $(this).data('number');
    console.log(number);
    $.ajax({
        data:{
            xhbh:number,
        },
        type:"DELETE",
        url:"/productoffer/offer",
        success:function (data) {
            if(data.code==0){
                console.log(data.msg);
            }else{
                console.log(data.msg);
            }
        }
    })
});
//下架请求的ajax
$(".present").on('click',function () {
    //下架原因
    var text= $(this).parent().prev().children().children().children().children().val();
    console.log(text);
//    商品编号
    var number = $('.outNumber').data('number');
    console.log(number);
    $.ajax({
        url:"/productoffer/offer/"+number+'/'+2+'/'+text,
        data:{
            xhbh:number,
            zt:2,
            text:text,
        },
        type:"GET",
        success:function (data) {
            if(data.code==0){
                alert(data.msg);
                window.location.reload();
                $(this).parent().prev().children().children().children().children().val('');
            }else{
                alert(data.msg);
                window.location.reload();
            }
        }
    })
});
//返回按钮
$(".cancel").on('click',function () {
    $(this).parent().prev().children().children().children().children().val('');
});
//上架请求的ajax
$(".putaway").on('click',function () {
    //上架原因
    var text= $(this).parent().prev().children().children().children().children().val();
    console.log(text);
//    商品编号
    var putawaynumber = $('.putAway').data('putawaynumber');
    console.log(putawaynumber);
    $.ajax({
        url:"/productoffer/offer/"+putawaynumber+'/'+1+'/'+text,
        data:{
            xhbh:putawaynumber,
            zt:1,
            text:text,
        },
        type:"GET",
        success:function (data) {
            if(data.code==0){
                alert(data.msg);
                window.location.reload();
                $(this).parent().prev().children().children().children().children().val('');
            }else{
                alert(data.msg);
                window.location.reload();
            }
        }
    })
});