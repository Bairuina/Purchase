
var testUrl;

$(function() {

	var hh = document.documentElement.clientHeight;
	var ls = document.documentElement.clientWidth;
	if (ls < 640) {
		$(".select dt").click(function() {
			if ($(this).next("div").css("display") == 'none') {
				$(".theme-popover-mask").height(hh);
				$(".theme-popover").css("position", "fixed");
				$(this).next("div").slideToggle("slow");
				$(".select div").not($(this).next()).hide();
			}
			else{
				$(".theme-popover-mask").height(0);
				$(".theme-popover").css("position", "static");
				$(this).next("div").slideUp("slow");;
			}
		});

		$(".eliminateCriteria").on("click", function() {
			$(".dd-conent").hide();
		});

		$(".select dd").on("click", function() {
			$(".theme-popover-mask").height(0);
			$(".theme-popover").css("position", "static");
			$(".dd-conent").hide();
		});

		$(".theme-popover-mask").on("click", function() {
			$(".theme-popover-mask").height(0);
			$(".theme-popover").css("position", "static");
			$(".dd-conent").hide();
		});

	}

	$("span.love").click(function() {
		$(this).toggleClass("active");
	});
	//点击下边列表时的值
	//	标签种类的编号
	// var listNumber = $("#selectB").data("listnumber");
	// console.log(listNumber);
	// if(listNumber=='null'){
    //     listNumber='0';
    // }
	// //	标签品目的编号
	// var itemNumner = $("#selectC").data("itemnumber");
	// console.log(itemNumner);
    // if(itemNumner=='null'){
    //     itemNumner='0';
    // }
	// //标签品牌的编号
	// var brandNumber = $("#selectA").data("brandnumber");
    // if(brandNumber=='null'){
    //     brandNumber='0';
    // }
	// 	//品牌
	// $(".Item1").on("click",function(){
	// 	//被选择品牌的编号
	// 	var brandnumber = $(this).data("brandnumber");
	// 	//被选择的品牌
	// 	console.log(brandnumber);
	// 	var text = $(this).children().text();
	// 	console.log(text);
	//
    //     console.log(itemNumner);
    //     console.log(brandnumber);
    //     $.ajax({
	// 		url:"/product/"+listNumber+'/'+itemNumner+'/'+brandnumber+'/1',
	// 		type:"GET",
	// 		success : function(data){
	// 			window.location.href="/product/"+listNumber+'/'+itemNumner+'/'+brandnumber+'/1';
	// 		}
	// 	})
	// });
	// //类别
	// $(".Item2").on("click",function(){
	// //选择的类别编号
	// 	var listsNumber = $(this).data("listsnumber");
	// 	console.log(listsNumber);
	// //	类别名称
	// 	var contentLists = $(this).children().text();
	// 	console.log(contentLists);
	// 	$.ajax({
	// 		url:"/product/"+listsNumber+'/'+itemNumner+'/'+brandNumber+'/1',
	// 		type:"GET",
	// 		success:function(data){
	// 			window.location.href="/product/"+listsNumber+'/'+itemNumner+'/'+brandNumber+'/1';
	// 		}
	// 	})
	// });
	// //品目
	// $(".Item3").on('click',function(){
	// //	被选择品目的编号
	// 	var itemsnumber = $(this).data("itemnumber");
	// 	console.log(itemsnumber);
	// //被选择品目的名字
	// 	var itemsText = $(this).children().text();
	// 	console.log(itemsText);
    //     $.ajax({
	// 		url:"/product/"+listNumber+'/'+itemsnumber+'/'+brandNumber+'/1',
	// 		type:"GET",
	// 		success:function(data){
	// 			window.location.href="/product/"+listNumber+'/'+itemsnumber+'/'+brandNumber+'/1';
	// 		}
	// 	})
	// });


	//自带样式效果
	$(document).on("click","#selectA", function() {
		$(this).remove();
		$("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
	});

	$(document).on("click","#selectB", function() {
		$(this).remove();
		$("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
	});

	$(document).on("click","#selectC", function() {
		$(this).remove();
		$("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
	});


	$(document).on("click",".select dd", function() {
		if ($(".select-result dd").length > 1) {
			$(".select-no").hide();
			$(".eliminateCriteria").show();
			$(".select-result").show();
		} else {
			$(".select-no").show();
			$(".select-result").hide();
		}
	});

	//清除按钮
	$(".eliminateCriteria").on("click", function() {
		$("#selectA").remove();
		$("#selectB").remove();
		$("#selectC").remove();
		$(".select-all").addClass("selected").siblings().removeClass("selected");
		$(".eliminateCriteria").hide();
		$(".select-no").show();
		$(".select-result").hide();
	});



//	高度改变
	$(".select-all").on('click',function () {
		var allText = $(this).children().text();
		if(allText=="全部"){
			$(this).parent().css("height","auto");
			$(this).children().text("收起");
		}else{
			$(this).parent().css("height","62px");
			$(this).children().text("全部");
		}
	})
});


