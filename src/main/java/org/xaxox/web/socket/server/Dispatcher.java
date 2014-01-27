package org.xaxox.web.socket.server;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class Dispatcher {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Gson gson;

    private Map<String, Entry> listeners = new HashMap<String, Entry>();

    private Map<String,Class> modelClasses = new HashMap<String, Class>();

    @PostConstruct
    public void scan() {
        String[] webSocketControllers = context.getBeanNamesForAnnotation(WebSocketController.class);
        for (String webSocketController : webSocketControllers) {
            Object bean = context.getBean(webSocketController);
            Class controllerClass = bean.getClass();
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                WebSocketHandler methodAnnotation = method.getAnnotation(WebSocketHandler.class);
                if (methodAnnotation != null) {
                    Entry entry = new Entry();
                    entry.bean = bean;
//                    entry.path = path;

                    entry.method = method;
                    entry.clazz = controllerClass;
//                    entries.put(path, entry);
                }
            }
        }
    }

    public void scanModels(){



    }


    public void dispatch(WebSocketSession session, String message){


        String[] statements = message.split("//;");

        for (String statement : statements) {


            String[] comData = statement.split("//=");


            String[] split = comData[0].split("//.");


            String nameOfModel = split[0];
            String nameOfField = split[1];

            Class model = modelClasses.get(nameOfModel);


//            session.





        }










        Entry entry = null;

        if(entry == null){


            return;
        }



//        build();

        Class<?>[] parameterTypes = entry.method.getParameterTypes();
        Object[] parmeters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            try {
//                Object object = gson.fromJson(split[1], parameterType);
//                parmeters[i] = object;
            } catch (Exception e){

            }
        }

        try {
            Object invoke = entry.method.invoke(entry.bean, parmeters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        int i=0;
        i++;

    }




    public Object build(Class clazz){
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                if(name.startsWith("set")){
                    onSet(method.getName().substring(3, method.getName().length()).toLowerCase(), args[0]);
                }
                return method.invoke(proxy, method, args);
            }


        };
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{clazz}, invocationHandler);
    }

    private void onSet(String methodName, Object arg) {

    }


    private class Entry {
        Object bean;
        Class clazz;
        Method method;
        String path;
        String callBackPath;
    }

    private class Models{

        String name;
        Method method;


    }
}