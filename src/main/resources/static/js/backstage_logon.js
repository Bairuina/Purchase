function logon() {
    //用户名'
    var submitFlg = true;
    var user = { register: /^[\w]{1,12}$/, tip: '请输入1-6位的英文、数字、下划线' };
    var username = document.getElementById("userName").value;
    var spans = document.getElementById("userName").parentNode.getElementsByTagName("span");
    spans[0].innerHTML = "";
    if (!user.register.test(username)) {
        spans[0].innerHTML = user.tip;
        submitFlg = false;
    }
    //    密码
    var pass = { register: /^[\w]{6,12}$/, tip: '请输入6-12位的英文、数字、下划线' };
    var passWord = document.getElementById("passWord").value;
    var remark = document.getElementById("passWord").parentNode.getElementsByTagName("span");
    remark[0].innerHTML = "";
    if (!user.register.test(passWord)) {
        remark[0].innerHTML = pass.tip;
        submitFlg = false;
    }
    return submitFlg;

}
logon();
// //登录AJAX请求
$(".entry").click(function() {
    console.log("管理者后台");
    $.ajax({
        url: "http://localhost:8080/user/login",
        type: "GET",
        dataType: "JSON",
        // async:true,
        data: {
            userName: $("#userName").val(),
            password: $("#passWord").val(),
        },
        success: function(data) {
            if (data.code == 100) {
                window.location.href = 'http://localhost:8080/administrator/backstageIndexJump';
            }
        },
    });
});