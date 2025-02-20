package com.example.demo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/*")
public class RequestLoggingFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException{
        this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (!uri.startsWith("/api")) {
            chain.doFilter(request, response);
            return;
        }

        Map<String, String[]> paramMap = req.getParameterMap();
        if (!paramMap.isEmpty()) {
            this.context.log(req.getRemoteAddr() + "::Request Params::" + paramMap);
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                this.context.log(req.getRemoteAddr() + "::Cookie::{" + cookie.getName() + "," + cookie.getValue() + "}");
            }
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}

