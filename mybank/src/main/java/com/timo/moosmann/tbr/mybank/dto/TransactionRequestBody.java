package com.timo.moosmann.tbr.mybank.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransactionRequestBody {

    @NotNull
    @Size(min= 2, max = 20)
    private String reference;

    @Min(1)
    @Max(1000)
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
