package com.bankapp.servlet;

import com.bankapp.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private BankService bankService;

    @Override
    public void init() {
        bankService = new BankService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (bankService.userExists(username)) {
            req.setAttribute("error", "Username already exists!");
            req.getRequestDispatcher("register.html").forward(req, resp);
            return;
        }

        bankService.registerUser(username, password);
        resp.sendRedirect("index.html");
    }
}
