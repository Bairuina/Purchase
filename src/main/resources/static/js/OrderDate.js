$(".yes_order").on('click', function () {
    var orderReference = $(".ddbhData").text();
    console.log(orderReference);
    var inform = "您确定要接受订单号为" + orderReference + "的订单？";
    var r = confirm(inform);
    if (r) {
        $.ajax({
            type: "put",
            url: "/order-data/ensureORefuseOrder",
            data: {
                'ddbh': orderReference,
                'qrzt':0
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
    }
});


$(".lookContract").on('click', function () {
    var orderReference = $(".ddbh").text();
    console.log(orderReference);
    $.ajax({
        type: "get",
        url: "/contract/queryContract",
        data: {
            'ddbh': orderReference,
        },
        contentType:"application/json",
        dataType:"json",
        success: function (data) {
            if(data.code == 0){
                console.log(data.data);
                location.href= data.data.contractUrl;
            }else{
                alert(data.msg);
            }
        },
        error: function (msg) {
            alert("查看合同失败！");
        }
    })
});

$(".checkInvoice").on('click', function () {
    var orderReference = $(".ddbh").text();
    console.log(orderReference);
    $.ajax({
        type: "put",
        url: "/order-data/invoiceEndTimeSubmit",
        data: {
            'ddbh': orderReference,
        },
        contentType:"application/json",
        dataType:"json",
        success: function (data) {
            alert("开具发票成功！");
        },
        error: function (msg) {
            alert("开具发票失败！");
        }
    })
});


//提交更改
function update() {
    //获取模态框数据
    var ddbh = $('ddbh').text();
    var skbz = $('#skbz').val();
    console.log(skbz);
    var skje = $('#skje').val();
    var sksj = data();
    $.ajax({
        type: "",
        url: "",
        ddbh: ddbh,
        skbz: skbz,
        skje: skje,
        sksj: sksj,
        dataType: 'html',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function(result) {
            location.reload();
        }
    });
}
