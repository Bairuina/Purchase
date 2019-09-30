function getStyle(ele) {
    if (ele.currentStyle) {
        return ele.currentStyle;
    } else {
        return getComputedStyle(ele, null);
    }
}

function animate(div, obj) {

    clearInterval(div.timer);

    div.timer = setInterval(function() {
        var flag = true;
        for (var key in obj) {

            var target = obj[key];
            if (key == 'opacity') {
                var speed = (target - parseFloat(getStyle(div)[key])) * 100 / 8;
                speed = (speed > 0 ? Math.ceil(speed) : Math.floor(speed));
                var op = parseFloat(getStyle(div)[key]) + speed / 100;
                div.style[key] = op;
                if (parseFloat(getStyle(div)[key]) != target) {
                    flag = false;
                }

            } else {
                var speed = (target - parseInt(getStyle(div)[key])) / 8;
                speed = (speed > 0 ? Math.ceil(speed) : Math.floor(speed));
                div.style[key] = parseInt(getStyle(div)[key]) + speed + 'px';
                if (parseInt(getStyle(div)[key]) != target) {
                    flag = false;
                }
            }
        }

        if (flag == true) {
            clearInterval(div.timer);
        }

    }, 30);
}
var rightBtn = document.querySelector('.rightBtn');
var oImg = document.querySelectorAll('.item li');
//          console.log(oImg[0])

//先将页数为0，从第一张开始
var pageName = 0;
//调用数字列表显示样式的函数
changeColor(pageName);

//给右按钮添加点击事件
rightBtn.onclick = function() {

    //使轮播图隐藏
    animate(oImg[pageName], { opacity: 0 });
    console.log(oImg[pageName]);
    pageName++; //页数加1 ，页数++必须写在这两个animate中间（先让上一张图片隐藏再让下一张图片显示出来）

    //当页数为5的时候，也就是滚动到了第6张，图片已经没有了
    if (pageName == 5) {
        pageName = 0; //这个时候让页数从0（第一张继续开始）
    }
    changeColor(pageName);
    animate(oImg[pageName], { opacity: 1 }); //使图片显示出来

}

var leftBtn = document.querySelector('.leftBtn');
leftBtn.onclick = function() { //给左侧大按钮添加点击事件

    animate(oImg[pageName], { opacity: 0 }); //左侧同右侧
    pageName--;
    changeColor(pageName);

    if (pageName == -1) {
        pageName = 4;
    }
    changeColor(pageName);
    animate(oImg[pageName], { opacity: 1 });

}

var timer = setInterval(function() { //设定一个定时器，每隔两秒钟调用一次右侧点击函数
    rightBtn.onclick();
}, 2000);

var box = document.querySelector('.box');
box.onmouseenter = function() { //当鼠标划入的时候，让定时器停止
    clearInterval(timer);

}
box.onmouseleave = function() { //当鼠标划出的时候让定时器继续
    timer = setInterval(function() { //注意：timer前面不能用var关键字，用了之后就和上面全局变量重名
        rightBtn.onclick();
    }, 2000);
}


function changeColor(name) { //更改数字列表的颜色
    var pages = document.querySelectorAll('.page li');
    for (var i = 0; i < pages.length; i++) { //遍历出所有的li，
        pages[i].style.background = '#ccc'; //一开始颜色为#ccc

    }
    pages[name].style.background = '#ff0'; //改变后颜色为#ff0
}


//给数字列表小圆点加点击事件
var oPages = document.querySelectorAll('.page li')
for (let i = 0; i < oPages.length; i++) { //遍历出所有的li
    oPages[i].onclick = function() { //给每个li添加点击事件
        console.log(i);
        for (let j = 0; j < oImg.length; j++) { //循环遍历出所有放的图片的li
            animate(oImg[j], { opacity: 0 }) //先让它隐藏
        }
        animate(oImg[i], { opacity: 1 }); //然后点击哪个小圆点让相对应的图片显示出来

        pageName = i; //因为点击小圆点改变了页码，页码是可以被改变的，把页码设为i，必须得加上
        changeColor(pageName);
    }
}
var rightBtn = document.querySelector('.rightBtn');
var oImg = document.querySelectorAll('.item li');
//          console.log(oImg[0])

//先将页数为0，从第一张开始
var pageName = 0;
//调用数字列表显示样式的函数
changeColor(pageName);

//给右按钮添加点击事件
rightBtn.onclick = function() {

    //使轮播图隐藏
    animate(oImg[pageName], {
        opacity: 0
    });
    console.log(oImg[pageName]);
    pageName++; //页数加1 ，页数++必须写在这两个animate中间（先让上一张图片隐藏再让下一张图片显示出来）

    //当页数为5的时候，也就是滚动到了第6张，图片已经没有了
    if (pageName == 5) {
        pageName = 0; //这个时候让页数从0（第一张继续开始）
    }
    changeColor(pageName);
    animate(oImg[pageName], {
        opacity: 1
    }); //使图片显示出来

}

var leftBtn = document.querySelector('.leftBtn');
leftBtn.onclick = function() { //给左侧大按钮添加点击事件

    animate(oImg[pageName], {
        opacity: 0
    }); //左侧同右侧
    pageName--;
    changeColor(pageName);

    if (pageName == -1) {
        pageName = 4;
    }
    changeColor(pageName);
    animate(oImg[pageName], {
        opacity: 1
    });

}

var timer = setInterval(function() { //设定一个定时器，每隔两秒钟调用一次右侧点击函数
    rightBtn.onclick();
}, 2000);

var box = document.querySelector('.box');
box.onmouseenter = function() { //当鼠标划入的时候，让定时器停止
    clearInterval(timer);

}
box.onmouseleave = function() { //当鼠标划出的时候让定时器继续
    timer = setInterval(function() { //注意：timer前面不能用var关键字，用了之后就和上面全局变量重名
        rightBtn.onclick();
    }, 2000);
}


function changeColor(name) { //更改数字列表的颜色
    var pages = document.querySelectorAll('.page li');
    for (var i = 0; i < pages.length; i++) { //遍历出所有的li，
        pages[i].style.background = '#ccc'; //一开始颜色为#ccc

    }
    pages[name].style.background = '#ff0'; //改变后颜色为#ff0
}


//给数字列表小圆点加点击事件
var oPages = document.querySelectorAll('.page li')
for (let i = 0; i < oPages.length; i++) { //遍历出所有的li
    oPages[i].onclick = function() { //给每个li添加点击事件
        console.log(i);
        for (let j = 0; j < oImg.length; j++) { //循环遍历出所有放的图片的li
            animate(oImg[j], {
                    opacity: 0
                }) //先让它隐藏
        }
        animate(oImg[i], {
            opacity: 1
        }); //然后点击哪个小圆点让相对应的图片显示出来

        pageName = i; //因为点击小圆点改变了页码，页码是可以被改变的，把页码设为i，必须得加上
        changeColor(pageName);
    }
}
var rightBtn = document.querySelector('.rightBtn');
var oImg = document.querySelectorAll('.item li');
//          console.log(oImg[0])

//先将页数为0，从第一张开始
var pageName = 0;
//调用数字列表显示样式的函数
changeColor(pageName);

//给右按钮添加点击事件
rightBtn.onclick = function() {

    //使轮播图隐藏
    animate(oImg[pageName], {
        opacity: 0
    });
    pageName++; //页数加1 ，页数++必须写在这两个animate中间（先让上一张图片隐藏再让下一张图片显示出来）

    //当页数为5的时候，也就是滚动到了第6张，图片已经没有了
    if (pageName == 5) {
        pageName = 0; //这个时候让页数从0（第一张继续开始）
    }
    changeColor(pageName);
    animate(oImg[pageName], {
        opacity: 1
    }); //使图片显示出来

}

var leftBtn = document.querySelector('.leftBtn');
leftBtn.onclick = function() { //给左侧大按钮添加点击事件

    animate(oImg[pageName], {
        opacity: 0
    }); //左侧同右侧
    pageName--;
    changeColor(pageName);

    if (pageName == -1) {
        pageName = 4;
    }
    changeColor(pageName);
    animate(oImg[pageName], {
        opacity: 1
    });

}

var timer = setInterval(function() { //设定一个定时器，每隔两秒钟调用一次右侧点击函数
    rightBtn.onclick();
}, 2000);

var box = document.querySelector('.box');
box.onmouseenter = function() { //当鼠标划入的时候，让定时器停止
    clearInterval(timer);

}
box.onmouseleave = function() { //当鼠标划出的时候让定时器继续
    timer = setInterval(function() { //注意：timer前面不能用var关键字，用了之后就和上面全局变量重名
        rightBtn.onclick();
    }, 2000);
}


function changeColor(name) { //更改数字列表的颜色
    var pages = document.querySelectorAll('.page li');
    for (var i = 0; i < pages.length; i++) { //遍历出所有的li，
        pages[i].style.background = '#ccc'; //一开始颜色为#ccc

    }
    pages[name].style.background = '#ff0'; //改变后颜色为#ff0
}


//给数字列表小圆点加点击事件
var oPages = document.querySelectorAll('.page li')
for (let i = 0; i < oPages.length; i++) { //遍历出所有的li
    oPages[i].onclick = function() { //给每个li添加点击事件
        console.log(i);
        for (let j = 0; j < oImg.length; j++) { //循环遍历出所有放的图片的li
            animate(oImg[j], {
                    opacity: 0
                }) //先让它隐藏
        }
        animate(oImg[i], {
            opacity: 1
        }); //然后点击哪个小圆点让相对应的图片显示出来

        pageName = i; //因为点击小圆点改变了页码，页码是可以被改变的，把页码设为i，必须得加上
        changeColor(pageName);
    }
}
// 登陆模态框
function b() {
    //创建遮罩层div并插入body
    var mask = document.createElement("div");
    mask.id = "mask";
    //窗口可视区域高度
    var cheight = document.documentElement.clientHeight || document.body.clientHeight;
    mask.style.height = cheight + "px";
    //宽度直接用100%在样式里
    document.body.appendChild(mask);
    //创建登录层div并插入body
    var login = document.createElement("div");
    login.id = "login";
    login.innerHTML = '<div class="title" id="title">登录账号' + '<a href="#" class="close">X</a>' + '</div>' + '<div class="content">' + '<div class="user">' + '<input type="text" class="pt" placeholder="手机/邮箱/用户名" value="" name="">' + '</div>' + '<div class="password">' + '<input type="password" class="pt" placeholder="请输入密码" value="" name="">' + '</div>' + '<div class="submit">' + '<input type="button" class="sm" value="登陆">' + '</div>' + '</div>';
    document.body.appendChild(login);
    //窗口可视区域宽度
    var cwidth = document.documentElement.clientWidth || document.body.clientWidth;
    //登录框宽度
    var lwidth = login.offsetWidth;
    //登录框高度
    var lheight = login.offsetHeight;
    //设置登录框的居中显示
    login.style.left = (cwidth - lwidth) / 2 + "px";
    login.style.top = (cheight - lheight) / 2 + "px";
    //设置遮罩层的高度
    mask.style.height = cheight + "px";
    //改变窗口大小后依然居中显示
    //  onresize 事件会在窗口或框架被调整大小时发生。
    window.onresize = function() {
            if (document.compatMode == "CSS1Compat") {
                cwidth = document.documentElement.clientWidth;
                cheight = document.documentElement.clientHeight;
            } else {
                cwidth = document.body.clientWidth;
                cheight = document.body.clientHeight;
            }
            login.style.left = (cwidth - lwidth) / 2 + "px";
            login.style.top = (cheight - lheight) / 2 + "px";
            mask.style.height = cheight + "px";
        }
        //获取拖拽容器
    var title = document.getElementById("title");
    var isDraging = false; //以判断是可以进行拖拽
    var mouseOffsetX; //存放鼠标相对于登录框的位置
    var mouseOffsetY;
    //鼠标按下事件
    title.onmousedown = function(e) {
            var e = e || window.event;
            //鼠标相对于登录框的位置
            mouseOffsetX = e.pageX - login.offsetLeft;
            // e.pageX 是文档坐标而非窗口坐标
            // offsetLeft 鼠标相对于事件源元素（srcElement）的X,Y坐标
            mouseOffsetY = e.pageY - login.offsetTop;
            //鼠标摁下时为true
            isDraging = true;
        }
        //鼠标移动事件
    document.onmousemove = function(e) {
            var e = e || window.event;
            //鼠标移动时的坐标
            var newMX = e.pageX;
            var newMY = e.pageY;
            //判断为true时可以拖拽，
            if (isDraging === true) {
                //登录框的偏移值=当前位置-鼠标到登录框的距离
                var loginL = newMX - mouseOffsetX;
                var loginT = newMY - mouseOffsetY;
                //如果left top值超过边缘时就让他等于边缘
                if (loginL < 0) {
                    loginL = 0;
                } else if (loginL > (cwidth - lwidth)) {
                    loginL = cwidth - lwidth;
                }
                if (loginT < 0) {
                    loginT = 0;
                } else if (loginT > (cheight - lheight)) {
                    loginT = cheight - lheight;
                }
                login.style.left = loginL + "px";
                login.style.top = loginT + "px";
            }
        }
        //鼠标弹起时设置为不可拖拽
    document.onmouseup = function() {
            isDraging = false;
        }
        //点击X关闭登录框和弹出层
    var close = login.getElementsByClassName("close")[0];
    close.onclick = function() {
        document.body.removeChild(mask);
        document.body.removeChild(login);
    }
}
//点击登录弹出登录框和弹出层
window.onload = function() {
    var btn = document.getElementById("btn");
    btn.onclick = function() {
        b();
    }
}