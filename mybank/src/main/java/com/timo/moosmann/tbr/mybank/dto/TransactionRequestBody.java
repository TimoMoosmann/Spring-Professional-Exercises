package com.timo.moosmann.tbr.mybank.dto;

import com.timo.moosmann.tbr.mybank.web.validation.user_exists_constraint.UserExists;
import jakarta.validation.constraints.*;

public class TransactionRequestBody {

    @NotBlank
    @UserExists
    private String sendingUserId;

    @NotBlank
    @UserExists
    private String receivingUserId;

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

    public @NotBlank @Size(min = 2, max = 10) String getSendingUserId() {
        return sendingUserId;
    }

    public void setSendingUserId(@NotBlank @Size(min = 2, max = 10) String sendingUserId) {
        this.sendingUserId = sendingUserId;
    }

    public @NotBlank String getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(@NotBlank String receivingUserId) {
        this.receivingUserId = receivingUserId;
    }
}
