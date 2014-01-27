package org.xaxox;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class RunServer {
    public static void main(String[] args) throws LifecycleException {
        String webappDirLocation = "src/main/webapp/";

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        try {
            tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
