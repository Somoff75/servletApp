package com.example.demo.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if (user == null || pwd == null || user.trim().isEmpty() || pwd.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please enter both username and password.");
            return;
        }

        Map<String, String> users = UserMap.getUsers();

        try (PrintWriter out = response.getWriter()) {
            if (users.containsKey(user) && users.get(user).equals(pwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", user);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                out.println("Welcome back to the team, " + user + "!");
            } else {
                out.println("Either user name or password is wrong!");
            }
        }
    }
}





