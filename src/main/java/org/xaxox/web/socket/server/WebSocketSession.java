package org.xaxox.web.socket.server;

import org.apache.catalina.websocket.WsOutbound;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebSocketSession {

    private long id;
    private Date connected;


    public WebSocketSession(long id, WsOutbound outbound, Date connected) {
        this.id = id;
        this.connected = connected;

    }

    private Map<String, Object> sessionObject = new HashMap<String, Object>();

    public <T> T getSessionObject(String name) {
        return (T)sessionObject.get(name);
    }

    public void setSessionObject(String name, Object obj) {
        sessionObject.put(name,obj);
    }

    public long getId() {
        return id;
    }

    public Date getConnected() {
        return connected;
    }
}
