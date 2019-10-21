
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
	// function getQueryVariable(variable) {
	// 	var query = window.location.search.substring(1);
	// 	var vars = query.split("&");
	// 	for (var i=0;i<vars.length;i++) {
	// 		var pair = vars[i].split("=");
	// 		if(pair[0] == variable){return pair[1];}
	// 	}
	// 	return(false);
	// }
	$(".Item1").click(function() {
		var brandnumber = $(this).data("brandnumber");

	});

	$(".Item2").click(function() {
		var text = $(this).text();
		var content = getQueryVariab
		le('ppmc');
		if(content){
			var URL = location.pathname + '?';
			console.log(URL);
			URL += ('ppmc='+text);
			var pmmc = getQueryVariable('pmmc');
			if(pmmc){
				URL += ('&pmmc='+pmmc)
			}
			console.log(URL);
			var lbmc = getQueryVariable('lbmc');
			if(lbmc){
				URL += ('&lbmc='+lbmc)
			}
			console.log(URL);
			location.href = URL;
		}else {
			if(location.search===""){
				location.href = (location.href + '?ppmc=' + text)
			}else {
				location.href = location.href + '&ppmc=' + text
			}
		}
	});

	$(".Item3").click(function() {
		var text = $(this).text();
		var content = getQueryVariable('lbmc');
		if(content){
			var URL = location.pathname + '?';
			URL += 'lbmc=' + text;
			var ppmc = getQueryVariable('ppmc');
			if(ppmc){
				URL += ('&ppmc='+ppmc)
			}
			var pmmc = getQueryVariable('pmmc');
			if(pmmc){
				URL += ('&pmmc=' + pmmc)
			}
			location.href = URL;
		}else {
			if(location.search===""){
				location.href = (location.href + '?lbmc=' + text)
			}else {
				location.href = location.href + '&lbmc=' + text
			}
		}
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
});


