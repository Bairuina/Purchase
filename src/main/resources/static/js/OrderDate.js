$(".yes_order").on('click', function () {
    var orderReference = $("#ddbhDataWord").text();
    console.log(orderReference);
    var inform = "您确定要接受订单号为" + orderReference + "的订单？";
    var r = confirm(inform);
    if (r) {
        $.ajax({
            type: "post",
            url: "/order-data/ensureORefuseOrder",
            dataType:"json",
            data: {
                'ddbh':orderReference,
                'qrzt':'0'
            },
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
    var orderReference = $("#ddbhDataWord").text()
    var inform = "您确定要拒绝订单号为" + orderReference + "的订单？";
    var ztDataWord=$("#ztDataWordLiWord").data('ztdata');
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
            type: "post",
            url: urlData,
            dataType: "JSON",
            data: {
                ddbh:orderReference,
                qrzt:'1'
            },
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



//提交更改
$("#submitMoney").on('click',function update() {
    //获取模态框数据
    var ddbh = $('#ddbhDataWord').text();
    var skbz = $('#skbzDataSelect').val();
    console.log(ddbh);
    console.log(skbz);
    var skje = $('#skjeDataSelect').val();
    var sksj = $("#sksjDataSelect").val();
    console.log(skje);
    console.log(sksj);
    var firstSignTime = sksj.replace(/-/g, '');
    var secSignTime = firstSignTime.replace(/:/g, '');
    var thirdSignTime = secSignTime.replace(/\s+/g, '');
    var endTimetest = /\d{14}/;
    var skbzNum;
    if (skbz == "已收款") {
        skbzNum = '1';
    } else if (skbz == "未收款") {
        skbzNum ='2';
    }
    console.log(thirdSignTime);
    console.log(skbzNum);
    if (endTimetest.test(thirdSignTime)) {
        console.log("信息接入成功")
        $.ajax({
            type: "post",
            url: "/order-data/selectDataNumberSubmit",
            dataType: "json",
            data: {
                'ddbh': ddbh,
                'skbz': skbzNum,
                'skje': skje,
                'sksj': thirdSignTime
            },
            success: function (data) {
                console.log(data);
                if (data.code == '0') {
                    alert("发送信息成功！")
                    location.href = "date";
                } else {
                    alert("发送信息失败！")
                }
            },
            error: function (data) {
                alert("发送信息失败！");
            }
        })
    }
});
// 标识码照片放大
$(function() {
    $(".pic").click(function() {
        var _this = $(this); //将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    });
});

function imgShow(outerdiv, innerdiv, bigimg, _this) {
    var src = _this.attr("src"); //获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src); //设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function() {
        var windowW = $(window).width(); //获取当前窗口宽度
        var windowH = $(window).height(); //获取当前窗口高度
        var realWidth = this.width; //获取图片真实宽度
        var realHeight = this.height; //获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8; //缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if (realHeight > windowH * scale) { //判断图片高度
            imgHeight = windowH * scale; //如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight / realHeight * realWidth; //等比例缩放宽度
            if (imgWidth > windowW * scale) { //如宽度扔大于窗口宽度
                imgWidth = windowW * scale; //再对宽度进行缩放
            }
        } else if (realWidth > windowW * scale) { //如图片高度合适，判断图片宽度
            imgWidth = windowW * scale; //如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth / realWidth * realHeight; //等比例缩放高度
        } else { //如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width", imgWidth); //以最终的宽度对图片缩放

        var w = (windowW - imgWidth) / 2; //计算图片与窗口左边距
        var h = (windowH - imgHeight) / 2; //计算图片与窗口上边距
        $(innerdiv).css({
            "top": h,
            "left": w
        }); //设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast"); //淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function() { //再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}
