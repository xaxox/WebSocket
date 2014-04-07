package org.xaxox.config;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class BeansRepo {

    @Autowired
    private ApplicationContext context;

    @Bean
    public JSONSerializer getSerializer() {
        return new JSONSerializer();
    }

    @Bean
    public JSONDeserializer getDeserializer() {
        return new JSONDeserializer();
    }

    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod(SpringUtil.class.getName() + ".setContext");
        methodInvokingFactoryBean.setArguments(new Object[]{context});
        return methodInvokingFactoryBean;
    }
}
