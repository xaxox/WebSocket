package org.xaxox.web.socket.server;

import org.apache.catalina.websocket.WsOutbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketClientManager {

    @Autowired
    private Dispatcher dispatcher;

    Map<Long, WebSocketSession> clients = new HashMap<Long, WebSocketSession>();

    void add(WsOutbound outbound, long id){
        WebSocketSession webSocketClient = new WebSocketSession(id, outbound, new Date());
        clients.put(id, webSocketClient);
    }

    void onInputMessage(long clientId, String message){

        System.out.println("Info: clientId = " + clientId + " message = \"" + message + "\" Thread = " + Thread.currentThread().getName());

//        dispatcher.dispatch(clients.get(clientId), message);
    }

    void removed(long id){

        clients.remove(id);

    }

}
