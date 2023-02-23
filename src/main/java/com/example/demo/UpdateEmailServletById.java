package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdateEmailServletById")
public class UpdateEmailServletById extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            out.println("Invalid ID provided");
            return;
        }

        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            out.println("Email is required");
            return;
        }

        int status = EmployeeRepository.updateEmailById(id, email);

        if (status > 0) {
            out.println("Record saved successfully!");
        } else {
            out.println("Sorry! Unable to save record");
        }
        out.close();
    }
}

