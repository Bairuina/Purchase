$(".yes_order").on('click', function () {
    var orderReference = $("#ddbhData").text();
    console.log(orderReference);
    var inform = "您确定要接受订单号为" + orderReference + "的订单？";
    var r = confirm(inform);
    if (r) {
        $.ajax({
            type: "put",
            url: "/order-data/ensureORefuseOrder",
            data: {
                'ddbh':orderReference,
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
    var orderReference = $("#ddbhData").text()
    var inform = "您确定要拒绝订单号为" + orderReference + "的订单？";
    var ztDataWord=$("#ztDataWordLi").data('ztdata');
    var urlData;
    console.log(ztDataWord);
    if(ztDataWord=='2'){

        urlData='/order-data/ensureORefuseOrder'
    }
    if(ztDataWord=='3'){
        urlData='/order-data/deletEnsureOrder'
    }
    var r = confirm(inform);
    if (r) {
        $.ajax({
            type: "put",
            url: urlData,
            data: {
                ddbh:orderReference,
                ddzt:1
            },
            contentType:"application/json",
            dataType: "JSON",
            success: function (data) {
                console.log(data);
                if(data.code=='0'){
                    alert("拒绝订单成功！")
                    window.onload;
                }
                else{
                    alert("拒绝订单失败！")
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


$(".lookContract").on('click', function () {
    var orderReference = $("#ddbhData").text();
    console.log(123)
    console.log(orderReference);
    $.ajax({
        type: "GET",
        url: "/contract/queryContract",
        data: {
            'ddbh': orderReference,
        },
        contentType:"application/json",
        dataType:"json",
        success: function (data) {
            console.log(data);
            if(data.code=='0'){
                alert("查看合同成功！")
                window.location.href = data.data.contractUrl;
            }
            else{
                alert("查看合同失败！")
            }
        },
        error: function (msg) {
            alert("查看合同失败！");
        }
    })
});

// $(".checkInvoice").on('click', function () {
//     var orderReference = $("#ddbhData").text();
//     console.log(orderReference);
//     $.ajax({
//         type: "put",
//         url: "/order-data/invoiceEndTimeSubmit",
//         data: {
//             'ddbh': orderReference,
//         },
//         contentType:"application/json",
//         dataType:"json",
//         success: function (data) {
//             console.log(data);
//             if(data.code=='0'){
//                 alert("开具发票成功！")
//                 window.onload;
//             }
//             else{
//                 alert("开具发票失败！")
//             }
//         },
//         error: function (msg) {
//             alert("开具发票失败！");
//         }
//     })
// });


//提交更改
function update() {
    //获取模态框数据
    var ddbh = $('#ddbhData').text();
    var skbz = $('#skbz').val();
    console.log(skbz);
    var skje = $('#skje').val();
    var sksj = $("#sksj").val();
    var skbzNum;
    if(skbz == "已收款")  {
        skbzNum=1;
    }
    else if(skbz == "未收款"){
        skbzNum=2;
    }
    else{
        alert("收款标志错误!");
        window.onload;
    }
    $.ajax({
        type: "put",
        url: "/order-data/getMoneyDataSubmit",
        ddbh: ddbh,
        skbz: skbzNum,
        skje: skje,
        sksj: sksj,
        dataType: 'html',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function(result) {
            console.log(data);
            if(data.code=='0'){
                alert("推送物流消息成功！")
                window.onload;
            }
            else{
                alert("推送物流消息失败！")
            }
        }
    });
}
