package org.xaxox;

import javax.websocket.server.ServerEndpointConfig;

public class Conf extends ServerEndpointConfig.Configurator {




    public Conf() {
        int i=0;
        i++;

    }

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T) new MyEndPoint();
    }
}
