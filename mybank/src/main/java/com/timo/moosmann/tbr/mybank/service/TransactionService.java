package com.timo.moosmann.tbr.mybank.service;

import com.timo.moosmann.tbr.mybank.model.Transaction;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public Transaction createTransaction(
            Integer amount,
            String reference
    ) {
        String id = UUID.randomUUID().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Transaction transaction = new Transaction(
                id,
                amount,
                timestamp,
                reference
        );
        transactions.add(transaction);

        return transaction;
    }
}
