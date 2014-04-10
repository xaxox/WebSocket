package org.xaxox.powerMap;


import flexjson.JSONDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.xaxox.scaner.PowerMapScaner;
import org.xaxox.serverObject.MethodControl;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class PowerMapService {

    @Autowired
    private JSONDeserializer deserializer;


    @Autowired
    private ApplicationContext context;

    private EntityFactory entityFactory = new EntityFactory(this);

    private List<PowerMapListenerModel> listenerModels;



    @PostConstruct
    public void init(){
        PowerMapScaner scaner = new PowerMapScaner(context);
        listenerModels = scaner.scanFor(PowerMapListener.class, new Class[]{Init.class});
        scaner = null;
    }

    public void onMessage(Session session, String message) {


       //message to event


    }

    void process(Event event) {


        Event.From from = event.getFrom();

        switch (from) {

            case SERVER:
                //узнать что за данные, сформировать их и отправить на клиент
                break;
            case CLIENT:
                //взять данные, засетить куда надо, и повызыать листнеры
                break;


        }


    }


    void onEntityChanged(Object o, Method method, Object[] args) {

        //get methodName in powermap


        //get propertyName methodName
        String propertyName = getPropertyNameByMethodName(method.getName());


        //get value
        Object passedArgument = args[0];


    }

    private String getPropertyNameByMethodName(String methodName) {
        String propertyName = methodName.substring(3);
        String firstSign = propertyName.substring(0, 1).toLowerCase();
        String lastSigns = propertyName.substring(1, propertyName.length());
        return firstSign + lastSigns;
    }
}
