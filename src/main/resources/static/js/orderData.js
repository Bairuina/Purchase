$("yes_order").click(function () {

    $.ajax({
        url:"/order-data/ensureORefuseOrder",
        type:"POST",

        dataType:"JSON",
        data:{
            ddbh:$("#ddbhData").html(),
            qrzt:0
        },
        success:function(data){
            console.log(data);
            if(data.code=='0'){
                alert("确认订单成功！")
                window.onload;
            }
            else{
                alert("确认订单失败！")
            }
        },
    });
});


$("no_order").click(function () {
    var urlData;
    var ztDataWord=$("#ztDataWordLi").data('ztdata');
    console.log(ztDataWord);
    if(ztDataWord=='2'){
        urlData='/order-data/ensureORefuseOrder'
    }
    if(ztDataWord=='3'){
        urlData='/order-data/deletEnsureOrder'
    }
    console.log(urlData)
    if(urlData!=null) {
        $.ajax({
            url: urlData,
            type: "POST",
            dataType: "JSON",
            data: {
                ddbh: $("#ddbhData").html(),
                qrzt: 1
            },
            success: function (data) {
                console.log(data);
                if (data.code == '0') {
                    alert("取消订单成功！")
                    window.onload;
                } else {
                    alert("取消订单失败！")
                }
            },
        });
    }
    else {
        alert("该订单不可以操作！")
    }
});