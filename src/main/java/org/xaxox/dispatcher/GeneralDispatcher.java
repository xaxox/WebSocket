package org.xaxox.dispatcher;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class GeneralDispatcher {


    private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());


    public void onMessage(Session session, String message){

    }

    public void add(Session session){
        userSessions.add(session);
    }

    public void remove(Session session){
        userSessions.remove(session);
    }




}
