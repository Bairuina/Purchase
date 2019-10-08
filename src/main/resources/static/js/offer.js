$('.select').on('click',function(){
   $(this).next().slideToggle() ;
   var selectText = $(this).children().find(span).text();
   console.log(selectText);
});