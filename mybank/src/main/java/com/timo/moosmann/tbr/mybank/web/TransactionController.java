package com.timo.moosmann.tbr.mybank.web;

import com.timo.moosmann.tbr.mybank.dto.TransactionRequestBody;
import com.timo.moosmann.tbr.mybank.model.Transaction;
import com.timo.moosmann.tbr.mybank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> index() {
        return transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionRequestBody transactionRequestBody) {
        return transactionService.createTransaction(
                transactionRequestBody.getAmount(),
                transactionRequestBody.getReference()
        );
    }
}
