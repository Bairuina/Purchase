var websocket=null;

if('WebSocket' in  window){
    websocket=new WebSocket('ws://localhost:10508/WebSocketServer');
}else{
    layer.msg('该浏览器不支持websocket',{icon:2,time:1000});
}
websocket.onopen=function(event){
    console.info('建立连接');
}
websocket.onclose=function(event){
    console.info('关闭连接');
}
websocket.onmessage=function(event) {
    if (event.data != null) {
        alert("订单信息已经更新！")
    }
    console.info('收到消息' + event.data);
}
websocket.onerror=function(){
    console.info('websocket通讯发生错误！');
}
websocket.onbeforeunload=function(){
    websocket.close();
}



