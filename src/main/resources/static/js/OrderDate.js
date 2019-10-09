$(".yes_order").on('click', function () {
    var orderReference = $("#orderDataDdbh").text();
    var orderStatus = $(".orderDataZt").text();
    console.log(orderStatus);
    console.log(orderReference);
    var inform = "您确定要接受订单号为" + orderReference + "的订单？";
    var r = confirm(inform);
    if(orderStatus!='2'&&orderStatus!='3'){
        alert("该订单不允许这样操作！")
        r=!r
    }
    if (r) {
        $.ajax({
            type: "put",
            url: "/order-data/ensureORefuseOrder",
            data: {
                'ddbh': orderReference,
                'qrzt': orderStatus
            },
            contentType:"application/json",
            dataType:"json",
            success: function (data) {
                console.log(data);
                if(data.code=='0'){
                    alert("确认订单成功！")
                    window.onload;
                }
                else{
                    alert("确认订单失败！")
                }
            },
            error: function (msg) {
                alert("确认订单失败");
            }
        })
    }
    else {
        location.reload(true);
    }
});

$(".no_order").on('click', function () {
    var orderReference = $("#orderDataDdbh").text();
    var orderStatus = $(".orderDataZt").text();
    var inform = "您确定要拒绝订单号为" + orderReference + "的订单？";
    var r = confirm(inform);
    var urlData;
    console.log(orderStatus);
    console.log(orderReference);

    if(orderStatus=='2'){
        urlData='/order-data/ensureORefuseOrder'
    }
    else if(orderStatus=='3'){
        urlData='/order-data/deletEnsureOrder'
    }

    if(orderStatus!='2'&&orderStatus!='3'){
        alert("该订单不允许这样操作！")
        r=!r
    }
    if (r) {
        $.ajax({
            type: "put",
            url: urlData,
            data: {
                ddbh:orderReference,
                ddzt:orderStatus
            },
            contentType:"application/json",
            dataType: "JSON",
            success: function (data) {
                if (data.code == '0') {
                    alert("取消订单成功！")
                    window.onload;
                } else {
                    alert("取消订单失败！")
                }
            },
            error: function (msg) {
                alert("拒绝订单失败");
            }
        })
    }
    else {
    }
});