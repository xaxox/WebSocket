package org.xaxox.config;


import org.springframework.context.ApplicationContext;

public class SpringUtil {

    private static ApplicationContext context;

    public static void setContext(ApplicationContext context) {
        SpringUtil.context = context;
    }

    public static <T> T getBean(String beanName){
        return (T)context.getBean(beanName);
    }

    public static <T> T getBean(Class clazz){
        return (T)context.getBean(clazz);
    }


}
