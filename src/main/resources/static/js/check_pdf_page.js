
$('#showPDFData').on('click',function () {
        var ddbh =$("#ddbhDataWord").text();
        console.log(ddbh);
        $.ajax({
            url:"/order-data/checkShowPage",
            type:'get',
            data:{
                ddbh:ddbh
            },
            success:function (data) {
                if(data.code==0){
                    alert(data.msg)
                    window.location.href="/PDFData/"+ddbh+".pdf"
                }else{
                    alert(data.msg);
                }
            }

        })
});