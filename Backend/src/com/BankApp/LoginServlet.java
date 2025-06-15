package com.bankapp.servlet;

import com.bankapp.service.BankService;
import com.bankapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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

        if (bankService.authenticate(username, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("dashboard.html");
        } else {
            req.setAttribute("error", "Invalid username or password.");
            req.getRequestDispatcher("index.html").forward(req, resp);
        }
    }
}
