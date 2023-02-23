package com.example.demo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException{
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource:: localhost:8080 " + uri);

        HttpSession session = req.getSession(false);

        if (session == null && !isAllowedUri(uri)) {
            this.context.log("Unauthorized access request");
            res.getWriter().write("No access!!!");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAllowedUri(String uri) {
        return uri.endsWith("demo/saveServlet") || uri.endsWith("demo/loginServlet") || uri.endsWith("demo/viewServlet");
    }

    public void destroy() {
        //close any resources here
    }
}
