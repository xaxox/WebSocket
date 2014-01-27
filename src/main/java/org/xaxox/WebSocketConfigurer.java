package org.xaxox;

import javax.websocket.server.ServerEndpointConfig;


public class WebSocketConfigurer extends ServerEndpointConfig.Configurator {


    private static EndPoint endPoint = new EndPoint();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T) endPoint;
    }
}
