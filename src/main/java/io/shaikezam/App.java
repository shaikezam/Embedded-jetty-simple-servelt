package io.shaikezam;

import io.shaikezam.filter.AuthenticatingFilter;
import io.shaikezam.filter.RequestSizeLimitFilter;
import io.shaikezam.servlet.SimpleServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addFilter(AuthenticatingFilter.class, "/*", null);
        context.addFilter(RequestSizeLimitFilter.class, "/*", null);
        context.addServlet(new ServletHolder(new SimpleServlet()), "/my-servlet/*");

        Server server = new Server(8000);
        server.setHandler(context);
        server.start();
        LOGGER.info("Start server at port 8000");
        server.join();
    }
}
