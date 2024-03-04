package io.shaikezam.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticatingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticatingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // Check if the sessionId cookie is present
        Cookie[] cookies = request.getCookies();
        boolean sessionIdCookiePresent = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    sessionIdCookiePresent = true;
                    break;
                }
            }
        }
        if (!sessionIdCookiePresent) {
            LOGGER.warning("sessionId cookie not present");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Set 401 Unauthorized status code
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized will redirect to login page...");
            return;
        }
        // Log the request details
        LOGGER.info("Request from " + request.getRemoteAddr() + " authorized");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
