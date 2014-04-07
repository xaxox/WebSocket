package org.xaxox.serverObject;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

@Component
public class ServerObject {

    private Map<String, MethodEntry> methodMap = new HashMap<>();

    @Autowired
    private JSONDeserializer deserializer;

    @Autowired
    private JSONSerializer serializer;


    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void scan() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = context.getBean(beanDefinitionName);
            if (bean != null) {
                Class<?> beanClass = bean.getClass();
                ServerControl serverControl = beanClass.getAnnotation(ServerControl.class);
                if (serverControl != null) {

                    //get all methods
                    Method[] methods = beanClass.getMethods();
                    for (Method method : methods) {
                        MethodControl methodControl = method.getAnnotation(MethodControl.class);
                        if (methodControl != null) {
                            String value = methodControl.value();
                            if (value.equals("")) {
                                value = method.getName();
                            }
                            MethodEntry methodEntry = new MethodEntry();
                            methodEntry.bean = bean;
                            methodEntry.method = method;
                            methodMap.put(value, methodEntry);
                        }
                    }
                }
            }
        }
    }

    public void onConnect(Session session){
        Set<String> strings = methodMap.keySet();
        HashMap hashMap = new HashMap();
        hashMap.put("methods", strings);
        String serialize = serializer.deepSerialize(hashMap);
        session.getAsyncRemote().sendText(serialize);
    }


    public void onMessage(Session session, String message) {


        Map deserialize = (Map) deserializer.deserialize(message);

        String id = String.valueOf((Long) deserialize.get("id"));
        String method = (String) deserialize.get("method");
        List arguments = (List) deserialize.get("args");

        MethodEntry me = methodMap.get(method);

        Type[] genericParameterTypes = me.method.getGenericParameterTypes();

        int c = 0;

        Object[] argsToInvoke = new Object[genericParameterTypes.length];

        for (Type genericParameterType : genericParameterTypes) {
            Object arg;
            if (genericParameterType.getTypeName().equals("int")) {
                arg = Integer.valueOf((String) arguments.get(c));
            } else {
                arg = arguments.get(c);
            }

            argsToInvoke[c] = arg;
            c++;
        }

        Object result = null;

        try {
            result = me.method.invoke(me.bean, argsToInvoke);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("result", result);
        hashMap.put("id", id);


        HashMap hashMap1 = new HashMap();
        hashMap1.put("response", hashMap);
        String serialize = serializer.serialize(hashMap1);
        session.getAsyncRemote().sendText(serialize);
    }


    private class MethodEntry {
        Object bean;
        Method method;
    }


}
