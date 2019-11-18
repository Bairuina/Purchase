
$('#showPDFData').on('click',function () {
        var ddbh =$("#ddbhDataWord").text();
        console.log(ddbh);
        $.ajax({
            url:"/order-data/checkShowPage",
            data:{
                ddbh:ddbh
            },
            type:'get',
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