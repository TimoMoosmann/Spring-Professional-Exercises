package com.timo.moosmann.tbr.mybank.model;

import java.sql.Timestamp;

public class Transaction {
    private String id;
    private Integer amount;
    private Timestamp timestamp;
    private String reference;

    public Transaction(
            String id,
            Integer amount,
            Timestamp timestamp,
            String reference
    ) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
