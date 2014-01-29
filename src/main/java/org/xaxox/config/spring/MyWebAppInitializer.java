package org.xaxox.config.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ResourceServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();

        root.setServletContext(container);
        root.scan("org.xaxox");


        final ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(root));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}