package com.timo.moosmann.tbr.mybank.service;

import com.timo.moosmann.tbr.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private final String slogan;

    public TransactionService(@Value("${bank.slogan}") String slogan) {
        this.slogan = slogan;
    }

    public Transaction createTransaction(
            String userId,
            Integer amount,
            String reference
    ) {
        ZonedDateTime timestamp = ZonedDateTime.now();

        Transaction transaction = new Transaction(
                userId,
                amount,
                timestamp,
                reference,
                slogan
        );
        transactions.add(transaction);

        return transaction;
    }

    public List<Transaction> findAll() {
        return this.transactions;
    }

    public List<Transaction> findAll(String userId) {
        return this.transactions.stream().filter(
                transaction -> transaction.getUserId().equals(userId)
        ).toList();
    }
}
