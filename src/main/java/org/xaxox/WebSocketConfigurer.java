package org.xaxox;

import javax.websocket.server.ServerEndpointConfig;


public class WebSocketConfigurer extends ServerEndpointConfig.Configurator {


    private static MyEndPoint endPoint = new MyEndPoint();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T) endPoint;
    }
}
