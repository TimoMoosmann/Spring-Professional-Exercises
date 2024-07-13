package com.timo.moosmann.tbr.mybank;

import com.timo.moosmann.tbr.mybank.context.Application;
import com.timo.moosmann.tbr.mybank.web.MyBankServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {
    public static void main(String[] args) throws LifecycleException {
        String portString = System.getProperty("server.port");

        int port = portString == null
                ? 8080
                : Integer.parseInt(portString);

        Application.init();

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        Context context = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(context, "MyBankServlet", new MyBankServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }
}
