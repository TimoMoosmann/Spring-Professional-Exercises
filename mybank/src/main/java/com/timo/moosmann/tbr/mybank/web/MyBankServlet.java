package com.timo.moosmann.tbr.mybank.web;

import com.timo.moosmann.tbr.mybank.Application;
import com.timo.moosmann.tbr.mybank.model.Transaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyBankServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            resp.getWriter().print("TODO: Show transactions");
        } else {
            resp.setStatus(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            Integer amount = Integer.parseInt(req.getParameter("amount"));
            String reference = req.getParameter("reference");

            Transaction transaction = Application.transactionService.createTransaction(
                    amount,
                    reference
            );

            resp.getWriter().print(Application.objectMapper.writeValueAsString(transaction));
        } else {
            resp.setStatus(404);
        }
    }
}
