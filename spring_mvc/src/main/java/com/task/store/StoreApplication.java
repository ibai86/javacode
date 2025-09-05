package com.task.store;

import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class StoreApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.task.store.config");

        DispatcherServlet servlet = new DispatcherServlet(context);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        tomcat.getConnector();

        org.apache.catalina.Context servletContext = tomcat.addContext("", null);

        Tomcat.addServlet(servletContext, "dispatcherServlet", servlet).setLoadOnStartup(1);
        servletContext.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
