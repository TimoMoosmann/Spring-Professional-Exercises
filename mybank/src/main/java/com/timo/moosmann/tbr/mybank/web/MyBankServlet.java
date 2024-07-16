package com.timo.moosmann.tbr.mybank.web;

import com.timo.moosmann.tbr.mybank.context.Application;
import com.timo.moosmann.tbr.mybank.context.SpringConfiguration;
import com.timo.moosmann.tbr.mybank.model.Transaction;
import com.timo.moosmann.tbr.mybank.service.TransactionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class MyBankServlet extends HttpServlet {

    private TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfiguration.class);
        ctx.refresh();

        this.transactionService = ctx.getBean(TransactionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            List<Transaction> transactions = transactionService.findAll();

            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().print(Application.objectMapper.writeValueAsString(transactions));
        } else {
            resp.setStatus(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            Integer amount = Integer.parseInt(req.getParameter("amount"));
            String reference = req.getParameter("reference");

            Transaction transaction = transactionService.createTransaction(
                    amount,
                    reference
            );

            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().print(Application.objectMapper.writeValueAsString(transaction));
        } else {
            resp.setStatus(404);
        }
    }
}
