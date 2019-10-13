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


//发票开具模块
function billStartreturn(){
	$(".start_modal").slideUp(10);
}

function billStartSubimt(){
	billStartSend();
	$(".start_modal").slideUp(10);
}


//发票签收模块
function billSignreturn(){
	$(".end_modal").slideUp(10);
}

function billSignSubimt(){
	billEndSend();
	$(".end_modal").slideUp(10);
}


//事件
$(function(){
	//模态框显示
	$(".startBill").click(function(){
		$(".end_modal").slideUp(10);
		$(".start_modal").slideDown(10);
	})
	$(".signBill").click(function(){
		$(".start_modal").slideUp(10);
		$(".end_modal").slideDown(10);
	})
	
	//返回
	$(".startTime_return").click(function(){
		billStartreturn();
	})
	$(".signTime_return").click(function(){
		billSignreturn();
	})
	
	//提交
	$(".startSubmit_btn").click(function(){
		billStartSubimt();
	})
	$(".signSubmit_btn").click(function(){
		billSignSubimt();
	})
	
})