package com.bankapp.servlet;

import com.bankapp.service.BankService;
import com.bankapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private BankService bankService;

    @Override
    public void init() {
        bankService = new BankService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("index.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        String action = req.getParameter("action");

        switch (action) {
            case "deposit":
                double depositAmount = Double.parseDouble(req.getParameter("amount"));
                bankService.deposit(username, depositAmount);
                break;

            case "withdraw":
                double withdrawAmount = Double.parseDouble(req.getParameter("amount"));
                bankService.withdraw(username, withdrawAmount);
                break;

            case "transfer":
                String toUser = req.getParameter("to");
                double transferAmount = Double.parseDouble(req.getParameter("amount"));
                bankService.transfer(username, toUser, transferAmount);
                break;

            case "logout":
                session.invalidate();
                resp.sendRedirect("index.html");
                return;
        }

        resp.sendRedirect("dashboard.html");
    }
}
