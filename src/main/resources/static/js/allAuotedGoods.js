$(".same").on('click',function(){
    var indexnumber = $(this).index();
    console.log(indexnumber);
    $(this).parent().next().children().children().eq(indexnumber).css('opacity','1').siblings().css('opacity','0');
});