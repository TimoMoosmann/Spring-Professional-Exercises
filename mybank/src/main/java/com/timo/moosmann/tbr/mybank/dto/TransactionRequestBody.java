package com.timo.moosmann.tbr.mybank.dto;

public class TransactionRequestBody {
    private String reference;
    private Integer amount;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
