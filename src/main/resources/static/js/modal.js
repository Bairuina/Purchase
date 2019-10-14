//时间JQ
$(function () {
	$(".ui_timepicker").datetimepicker({
		//showOn: "button",
		//buttonImage: "./css/images/icon_calendar.gif",
		//buttonImageOnly: true,
		showSecond: true,
		timeFormat: 'hh:mm:ss',
		stepHour: 1,
		stepMinute: 1,
		stepSecond: 1
	})
})


//消息通知模态框
function inform(informInner){
	$(".informInner").html(informInner);
	$(".modal").slideDown(10);
	setTimeout(function(){
		$(".modal").slideUp(100);
	},1800);
}



//发票开具时间
function billStartSend(){
	var orderID = $("#orderID").val();
	var billStartTime = $("#bill_StartTime").val();
	var firstStartTime = billStartTime.replace(/-/g,'');
	var secStartTime = firstStartTime.replace(/:/g,'');
	var thirdStartTime = secStartTime.replace(/\s+/g,'');
	var startTimetest = /\d{14}/;
	if(startTimetest.test(thirdStartTime)){
		$.ajax({
			type:"post",
			url:"",
			dataType:"json",
			data:{
				"ddbh":ope_orderID,
				"fpkjsj":billStartTime
			},
			success:function(data){
				if(data.code == 1){
					inform("您好，发票开具时间已推送到商城！");
				}else{
					inform("您所填信息有误! 请检查后重新提交!");
				}
			},
			error:function(){
				inform("网络故障");
			}
		});
	}else{
		inform("您所填信息有误! 请检查后重新提交!");
	}
}

//发票签收时间
function billEndSend(){
	var sign_orderID = $("#sign_OrderID").val();
	var bill_SignTime = $("#bill_SignTime").val();
	var firstSignTime = billSignTime.replace(/-/g,'');
	var secSignTime = firstSignTime.replace(/:/g,'');
	var thirdSignTime = secSignTime.replace(/\s+/g,'');
	var endTimetest = /\d{14}/;    //正则验证
	if(endTimetest.test(thirdSignTime)){
		$.ajax({
			type:"post",
			url:"",
			dataType:"json",
			data:{
				"ddbh":sign_orderID,
				"fpsdsj":bill_SignTime
			},
			success:function(data){
				if(data.code == 1){
					inform("您好，发票签收时间已推送到商城！");
				}else{
					inform("您所填信息有误! 请检查后重新提交!");
				}
			},
			error:function(){
				inform("网络故障");
			}
		});
	}else{
		inform("您所填信息有误! 请检查后重新提交!");
	}
}

// 点击上传商品唯一标识码
$(".AddIdentification").on('click',function () {
	// // 获取订单编号和商品编号
	var xhbh = $("this").parent().parent().children().eq(0).children().eq(0).text();
	// var xhbh =123;
	var ddbh = $(".ddbh").text();
	// console.log($(".ddbh").text());
	// 上传框显示并将ddbh和spbh值放到框内
	$(".code_modal").slideUp(10);
	$('#orderID').val(ddbh);
	$('#QRcode_goodsID').val(xhbh);
})
//推送标识码
// function codeSend() {
//
// }

//发票开具模块
function billStartreturn(){
	$(".start_modal").slideUp(10);
}

function billStartSubimt(){
	billStartSend();
	billStartreturn();
}


//发票签收模块
function billSignreturn(){
	$(".end_modal").slideUp(10);
}

function billSignSubimt(){

	billEndSend();
	billSignreturn();
}

//标识码模块
function codeSendReturn(){
	// $(".code_modal").slideUp(10);
}

function codeSendSubmit(){
	codeSend();
	codeSendReturn();
}

//上传图片
var url;
$(function() {
	$(".pic").click(function () {
		$(this).parent().find(".QRcode").click(); //隐藏了input:file样式后，点击头像就可以本地上传
		$(this).parent().find(".QRcode").on("change",function(){
			var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			if (objUrl) {
				$(this).parent().find(".pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
				url = objUrl;
			}
		});
	});
});

function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}



//事件
$(function(){
	//模态框显示
	$(".checkInvoice").click(function(){
		billSignreturn();
		codeSendReturn();
		$(".start_modal").slideDown(10);
	})
	$(".checkInvoice").click(function(){
		billStartreturn();
		codeSendReturn();
		$(".end_modal").slideDown(10);
	})
	$(".AddIdentification").click(function(){
		var spxh = $("this").parent().parent().children().eq(0).children().eq(0).text();
		var ddbh = $('.ddbh').text();
		console.log(spxh);
		billStartreturn();
		billSignreturn();
		$(".code_modal").slideDown(10);
	})

	//返回
	$(".startTime_return").click(function(){
		billStartreturn();
	})
	$(".signTime_return").click(function(){
		billSignreturn();
	})
	$(".idenCode_return").click(function(){
		codeSendReturn();
	})

	//提交
	$(".startSubmit_btn").click(function(){
		billStartSubimt();
	})
	$(".signSubmit_btn").click(function(){
		billSignSubimt();
	})
	$(".codeSubmit_btn").click(function(){
		var ddbh = $("#orderID").val();
		var xhbh = $("#QRcode_onlyCodeID").val();
		var wybs = $("#QRcode_goodsID").val();
		var files = $("#image").prop('files');
		console.log(ddbh,xhbh,wybs);
		console.log(files[0]);
		var formData = new FormData();
		formData.append('myFileName', files[0]);
		formData.append('ddbh', ddbh);
		formData.append('xhbh', xhbh);
		formData.append('wybs', wybs);
		$.ajax({
			url: '/productList/product',
			type:'post',
			data: formData,
			contentType:false,
			processData:false,
			success:function (data) {
				console.log(data.msg);
			},
			error:function(){
				alert("信息上传失败！");
			}
		})
	})

})