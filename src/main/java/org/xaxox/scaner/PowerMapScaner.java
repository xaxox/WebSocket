package org.xaxox.scaner;


import org.springframework.context.ApplicationContext;
import org.xaxox.powerMap.PowerMapListenerModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class PowerMapScaner extends ScanService<PowerMapListenerModel>{

    public PowerMapScaner(ApplicationContext context) {
        super(context);
    }

    @Override
    protected PowerMapListenerModel setToMapper(Object bean, Method method, Annotation anno, Annotation methodAnno) {










        return null;
    }
}
