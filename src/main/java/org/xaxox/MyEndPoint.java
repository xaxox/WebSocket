package org.xaxox;

import org.xaxox.config.SpringUtil;
import org.xaxox.dispatcher.GeneralDispatcher;
import org.xaxox.serverObject.ServerObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class MyEndPoint {

    @OnOpen
    public void onOpen(Session userSession) {
        GeneralDispatcher dispatcher = getDispatcher();
        dispatcher.add(userSession);
    }

    @OnClose
    public void onClose(Session userSession) {
        GeneralDispatcher dispatcher = getDispatcher();
        dispatcher.remove(userSession);
    }

    @OnMessage
    public void onMessage(String message, Session userSession) {
        GeneralDispatcher dispatcher = getDispatcher();
        dispatcher.onMessage(userSession, message);
    }

    private GeneralDispatcher getDispatcher(){
        return SpringUtil.getBean(ServerObject.class);
    }
}
