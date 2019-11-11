var Data = '';
//类的选择
var lbmc;
var pmmc;
var ppmc;
$(".selectList").change(function () {
    var text = $("option:selected",this).data("text");//需求主键
    lbmc = text;
    console.log(text);
    $.ajax({
        url:'/productoffer/offer/lbmc',
        data:{
            lbmc:text,
        },
        type:"GET",
        success:function (data) {
             Data = data.data;
            $('#select1').empty();
            $("#select1").append("<option>"+'--请选择--'+"</option>\n");
            $.each(Data,function (i,val) {
                $("#select1").append("<option data-listval='"+val+"'>"+val+"</option>\n");
            })
        }
    })
});
//品目的选择
$("#select1").change(function () {
    var PMtext = $("option:selected",this).val();
    pmmc = PMtext;
    $.ajax({
        url:'/productoffer/offer/pmmc',
        data:{
            pmmc: PMtext,
        },
        type:'GET',
        success:function (data) {
             Data = data.data;
            console.log(Data);
            $("#select2").empty();
            $("#select2").append("<option>"+'--请选择--'+"</option>\n");
            $.each(Data,function (i,val) {
                $("#select2").append("<option data-Puval='"+val+"'>"+val+"</option>\n");
            })
        }
    })
});
//品牌的选择
$("#select2").change(function () {
    var Pptext = $("option:selected",this).val();
    ppmc = Pptext;
});
//选择的ajax
$('#submit').click(function () {
    var inputText = $('.productSearch').val();
    console.log(inputText);
    var zt = $(this).data('zt');
    console.log(zt);
    $.ajax({
        url:'/productoffer/offer/select/'+zt+'/'+lbmc+"/"+pmmc+"/"+ppmc+"/"+inputText+'/'+1,
        type:'GET',
        success:function (data) {
            if (data.code==0){
                console.log("成功");
            }
        }

    })

});





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
                alert(data.msg);
                window.location.reload();
            }else{
                alert(data.msg);
                window.location.reload();
            }
        }
    })
});
//下架编号
var num="";
$('.md-trigger').on('click',function(){
    var modal = $(this).data('modal');
    num = $(this).data('number');
    // console.log(num);
    $("#" + modal).niftyModal();
});
//下架请求的ajax
$(".present").on('click',function () {
    //下架原因
    var text= $(this).parent().prev().children().children().children().children().val();
    console.log(text);
    //下架商品编号
    $.ajax({
        url:"/productoffer/offer/"+num+'/'+2+'/'+text,
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
//重置按钮
$(".cancel").on('click',function () {
    $(this).parent().prev().children().val('');
});
//上架编号
var putnumber='';
$(".Racking").on('click',function () {
    putnumber = $(this).data('putawaynumber');
    console.log(putnumber);
});
//上架请求的ajax
$(".putaway").on('click',function () {
    //上架原因
    var text= $(this).parent().prev().children().children().children().children().val();
    console.log(putnumber +"===================");
    console.log(text);
    $.ajax({
        url:"/productoffer/offer/"+putnumber+'/'+1+'/'+text,
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

