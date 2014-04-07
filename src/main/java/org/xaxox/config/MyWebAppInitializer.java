package org.xaxox.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();

        root.scan("org.xaxox");

        root.setServletContext(container);

        final ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(root));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/api/*");


    }
}