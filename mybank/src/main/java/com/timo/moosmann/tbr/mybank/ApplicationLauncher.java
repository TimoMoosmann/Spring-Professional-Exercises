package com.timo.moosmann.tbr.mybank;

import com.timo.moosmann.tbr.mybank.context.MyBankConfiguration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationLauncher {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = getTomcat(getPort());
        Context context = tomcat.addContext("", null);

        AnnotationConfigWebApplicationContext springContext = loadSpringWebApplicationConfiguration();
        registerDispatcherServlet(springContext, context);

        tomcat.start();
    }

    private static void registerDispatcherServlet(
            AnnotationConfigWebApplicationContext springContext,
            Context context
    ) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
        Wrapper wrapper = Tomcat.addServlet(context, "app", dispatcherServlet);
        wrapper.setLoadOnStartup(1);
        wrapper.addMapping("/*");
    }

    private static int getPort() {
        String portString = System.getProperty("server.port");

        return portString == null
                ? 8080
                : Integer.parseInt(portString);
    }

    private static Tomcat getTomcat(int port) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        return tomcat;
    }

    private static AnnotationConfigWebApplicationContext loadSpringWebApplicationConfiguration() {
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(MyBankConfiguration.class);

        return springContext;
    }
}
