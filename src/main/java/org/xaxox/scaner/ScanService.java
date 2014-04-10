package org.xaxox.scaner;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.xaxox.powerMap.PowerMapListener;
import org.xaxox.serverObject.MethodControl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class ScanService<T> {

    private ApplicationContext context;

    protected ScanService(ApplicationContext context) {
        this.context = context;
    }

    public List<T> scanFor(Class classAnnotation, Class[] methodAnnotations){

        ArrayList<T> ts = new ArrayList<>();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = context.getBean(beanDefinitionName);
            if (bean != null) {
                Class<?> beanClass = bean.getClass();
                Annotation annotationServer = beanClass.getAnnotation(classAnnotation);
                if (annotationServer != null) {
                    //get all methods
                    Method[] methods = beanClass.getMethods();
                    for (Method method : methods) {
                        for (Class methodAnnotation : methodAnnotations) {
                            Annotation annotationMethod = method.getAnnotation(methodAnnotation);
                            if(annotationMethod != null){
                                T t = setToMapper(bean, method, annotationServer, annotationMethod);
                                ts.add(t);
                            }
                        }
                    }
                }
            }
        }

        return ts;
    }

    protected abstract T setToMapper(Object bean, Method method, Annotation anno, Annotation methodAnno);
}
