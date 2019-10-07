$(".yes_order").on('click', function () {
    var orderReference = $(".ddbh").text();
    var orderStatus = $(".ddzt span").text();
    console.log(orderStatus);
    var inform = "您确定要接受订单号为" + orderReference + "的订单？";
    var r = confirm(inform);
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
                alert("确认订单成功");
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
    var orderReference = $(".ddbh").text();
    var orderStatus = $(".ddzt span").text();
    var inform = "您确定要拒绝订单号为" + orderReference + "的订单？";
    var r = confirm(inform);
    if (r) {
        $.ajax({
            type: "put",
            url: "/order-data/selectOneOrder",
            data: {
                ddbh:orderReference,
                ddzt:orderStatus
            },
            contentType:"application/json",
            dataType: "JSON",
            success: function (data) {
                alert("拒绝订单成功");
            },
            error: function (msg) {
                alert("拒绝订单失败");
            }
        })
    }
    else {
        location.reload(true);
    }
});