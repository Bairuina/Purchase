
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
	var listNumber = $("#selectB").data("listnumber");
	console.log(listNumber);
	//	标签品目的编号
	var itemNumner = $("#selectC").data("itemnumber");
	console.log(itemNumner);
	//标签品牌的编号
	var brandNumber = $("#selectA").data("brandnumber");
	// var
		//品牌
	$(".Item1").on("click",function(){
		//被选择品牌的编号
		var brandnumber = $(this).data("brandnumber");
		//被选择的品牌
		console.log(brandnumber);
		var text = $(this).children().text();
		console.log(text);
		$.ajax({
			url:"/product/"+listNumber+'/'+itemNumner+'/'+brandnumber+'/1',
			type:"GET",
			success:function(data){
				window.location.reload();
			}
		})
	});
	//类别
	$(".Item2").on("click",function(){
	//选择的类别编号
		var listsNumber = $(this).data("listsnumber");
		console.log(listsNumber);
	//	类别名称
		var contentLists = $(this).children().text();
		console.log(contentLists);
		$.ajax({
			url:"/product/"+listsNumber+'/'+itemNumner+'/'+brandNumber+'/1',
			type:"GET",
			success:function(data){
				window.location.reload();
			}
		})

	});
	//品目
	$(".Item3").on('click',function(){
	//	被选择品目的编号
		var itemsnumber = $(this).data("itemsnumber");
		console.log(itemsnumber);
	//被选择品目的名字
		var itemsText = $(this).children().text();
		console.log(itemsText);
		$.ajax({
			url:"/product/"+itemsnumber+'/'+itemNumner+'/'+brandNumber+'/1',
			type:"GET",
			success:function(data){
				window.location.reload();
			}
		})
	});


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

	//品目品牌关闭时按钮
	$('.closeOne').click(function () {
		var URL = location.pathname;
		const ppmc = getQueryVariable('ppmc');
		const lbmc = getQueryVariable('lbmc');
		if(ppmc && lbmc){
			URL += '?ppmc=' + ppmc + '&lbmc=' + lbmc;
		}else if(lbmc && !ppmc){
			URL += '?lbmc=' + lbmc;
		}else if(!lbmc && ppmc){
			URL += '?ppmc=' + ppmc
		}else{
			console.log(URL)
		}
		location.href = URL;
	});
	$('.closeTwo').click(function () {
		var URL = location.pathname;
		const pmmc = getQueryVariable('pmmc');
		const lbmc = getQueryVariable('lbmc');
		if(pmmc && lbmc){
			URL += '?pmmc=' + pmmc + '&lbmc=' + lbmc;
		}else if(lbmc && !pmmc){
			URL += '?lbmc=' + lbmc;
		}else if(!lbmc && pmmc){
			URL += '?pmmc=' + pmmc
		}else{
			console.log(URL)
		}
		location.href = URL;
	})
	$('.closeThree').click(function () {
		var URL = location.pathname;
		const ppmc = getQueryVariable('ppmc');
		const pmmc = getQueryVariable('pmmc');
		if(ppmc && pmmc){
			URL += '?ppmc=' + ppmc + '&pmmc=' + pmmc;
		}else if(pmmc && !ppmc){
			URL += '?pmmc=' + pmmc;
		}else if(!pmmc && ppmc){
			URL += '?ppmc=' + ppmc
		}else{
			console.log(URL)
		}
		location.href = URL;
	})

	//每一个点击全部时刷新页面
	$('.allA').click(function () {
		var URL = location.pathname;
		const ppmc = getQueryVariable('ppmc');
		const lbmc = getQueryVariable('lbmc');
		if(ppmc && lbmc){
			URL += '?ppmc=' + ppmc + '&lbmc=' + lbmc;
		}else if(lbmc && !ppmc){
			URL += '?lbmc=' + lbmc;
		}else if(!lbmc && ppmc){
			URL += '?ppmc=' + ppmc
		}else{
			console.log(URL)
		}
		location.href = URL;
	});
	$('.allB').click(function () {
		var URL = location.pathname;
		const pmmc = getQueryVariable('pmmc');
		const lbmc = getQueryVariable('lbmc');
		if(pmmc && lbmc){
			URL += '?pmmc=' + pmmc + '&lbmc=' + lbmc;
		}else if(lbmc && !pmmc){
			URL += '?lbmc=' + lbmc;
		}else if(!lbmc && pmmc){
			URL += '?pmmc=' + pmmc
		}else{
			console.log(URL)
		}
		location.href = URL;
	})
	$('.allC').click(function () {
		var URL = location.pathname;
		const ppmc = getQueryVariable('ppmc');
		const pmmc = getQueryVariable('pmmc');
		if(ppmc && pmmc){
			URL += '?ppmc=' + ppmc + '&pmmc=' + pmmc;
		}else if(pmmc && !ppmc){
			URL += '?pmmc=' + pmmc;
		}else if(!pmmc && ppmc){
			URL += '?ppmc=' + ppmc
		}else{
			console.log(URL)
		}
		location.href = URL;
	})
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


