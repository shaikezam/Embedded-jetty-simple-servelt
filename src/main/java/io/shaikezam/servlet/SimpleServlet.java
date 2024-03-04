package io.shaikezam.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class SimpleServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SimpleServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Received GET request");
        resp.getWriter().println("Hello, GET request received");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Received POST request");
        resp.getWriter().println("Hello, POST request received");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Received PUT request");
        resp.getWriter().println("Hello, PUT request received");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Received DELETE request");
        resp.getWriter().println("Hello, DELETE request received");
    }
}
