package org.xaxox;

import org.xaxox.config.SpringUtil;
import org.xaxox.dispatcher.Dispatcher;
import org.xaxox.serverObject.ServerObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/ws")
public class MyEndPoint {


    private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Callback hook for Connection w events. This method will be invoked when a
     * client requests for a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {

        userSessions.add(userSession);

        ServerObject bean = SpringUtil.getBean(ServerObject.class);

        bean.onConnect(userSession);
    }

    /**
     * Callback hook for Connection close events. This method will be invoked when a
     * client closes a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnClose
    public void onClose(Session userSession) {
        userSessions.remove(userSession);
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client
     * send a message.
     * @param message The text message
     * @param userSession The session of the client
     */
    @OnMessage
    public void onMessage(String message, Session userSession) {
        ServerObject bean = SpringUtil.getBean(ServerObject.class);
        bean.onMessage(userSession, message);
    }
}
