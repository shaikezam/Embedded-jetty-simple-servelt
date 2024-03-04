package io.shaikezam.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class RequestSizeLimitFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(RequestSizeLimitFilter.class.getName());
    private static final int MAX_REQUEST_SIZE = 1024; // 1KB

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getContentLength() > MAX_REQUEST_SIZE) {
            LOGGER.warning("Request size exceeds limit");
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, "Request entity too large");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
