// 添加用户
function addUser() {
    var inform = "您确定添加一条用户信息吗？";
    if(confirm(inform) == true){
        $.ajax({
            //几个参数需要注意一下
            type: "PUT",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/user/insertUser" ,//url
            data: $('#form1').serialize(),
            success: function (data) {
                console.log(data);//打印服务端返回的数据(调试用)
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.href = "/user/userList/1";
                }else{
                    alert(data.msg);
                    window.location.href = "/user/userList/1";
                };
            },
            error : function() {
                alert("异常！");
            }
        });
    }
}