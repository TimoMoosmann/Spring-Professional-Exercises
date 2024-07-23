package com.timo.moosmann.tbr.mybank.dto;

import com.timo.moosmann.tbr.mybank.web.validation.user_exists_constraint.UserExists;
import jakarta.validation.constraints.*;

public class TransactionRequestBody {

    @NotBlank
    @UserExists
    private String userId;

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

    public @NotBlank @Size(min = 2, max = 10) String getUserId() {
        return userId;
    }

    public void setUserId(@NotBlank @Size(min = 2, max = 10) String userId) {
        this.userId = userId;
    }
}
