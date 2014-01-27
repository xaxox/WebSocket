package org.xaxox.controller;

import org.xaxox.web.socket.server.WebSocketController;
import org.xaxox.web.socket.server.WebSocketHandler;

@WebSocketController
public class MenuController {




    @WebSocketHandler(listenTo = "player")
    public void view(Player player){




    }



}
