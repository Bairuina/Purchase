package net.wlgzs.purchase.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author  王言
 */
@Component
@ServerEndpoint("/WebSocketServer")
public class WebSocketServer {

    Logger logger= LoggerFactory.getLogger(WebSocketServer.class);
    // 静态变量，用来记录当前在线连接数,应该把它设计成线程安全的
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象，若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<WebSocketServer>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        logger.info("建立连接");
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        logger.info("关闭连接");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("【websocket发生错误】：" + error);
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        logger.info(message);
        try {
            for (WebSocketServer webSocket : webSockets) {
                logger.info("【websocket消息】发送消息：" + message);
                webSocket.session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
