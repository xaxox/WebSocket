package org.xaxox.powerMap;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class EntityFactory {

    private PowerMapService service;

    public EntityFactory(PowerMapService service) {
        this.service = service;
    }

    public <T> Object instantiateEntity(Class<T> clazz){

        ClassLoader classLoader = Object.class.getClassLoader();
        Class[] interfaces = new Class[]{clazz, PowerMapable.class};
        Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, (o, method, objects) -> {

            String name = method.getName();
            if (name.startsWith("set")) {
                service.onEntityChanged(o, method, objects);
            }

            return method.invoke(o, objects);
        });

        return (T) proxyInstance;

    }
}
