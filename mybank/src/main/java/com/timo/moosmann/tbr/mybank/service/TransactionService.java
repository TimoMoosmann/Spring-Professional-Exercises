package com.timo.moosmann.tbr.mybank.service;

import com.timo.moosmann.tbr.mybank.model.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public Transaction createTransaction(
            Integer amount,
            String reference
    ) {
        ZonedDateTime timestamp = ZonedDateTime.now();

        Transaction transaction = new Transaction(
                amount,
                timestamp,
                reference
        );
        transactions.add(transaction);

        return transaction;
    }

    public List<Transaction> findAll() {
        return this.transactions;
    }
}
