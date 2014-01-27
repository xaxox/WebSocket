package org.xaxox.web.socket.server;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;


@Component
public class WebSocketServlet extends org.apache.catalina.websocket.WebSocketServlet {

    private long count = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {



//        org.apache.tomcat.websocket

        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Autowired
    private WebSocketClientManager webSocketClientManager;

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
        return new InboundMessage();
    }

    class InboundMessage extends MessageInbound {

        long id;

        InboundMessage() {
            id = count++;
        }

        @Override
        protected void onOpen(WsOutbound outbound) {
            webSocketClientManager.add(outbound,id);
        }

        @Override
        protected void onClose(int status) {

            webSocketClientManager.removed(id);

        }

        @Override
        protected void onBinaryMessage(ByteBuffer message) throws IOException {

        }

        @Override
        protected void onTextMessage(CharBuffer message) throws IOException {
            webSocketClientManager.onInputMessage(id, message.toString());
        }
    }
}
