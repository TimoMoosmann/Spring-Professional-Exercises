package local.timo.mybank_spring_boot.dto;

import jakarta.validation.constraints.*;
import local.timo.mybank_spring_boot.web.validation.user_exists.UserExists;

public class TransactionRequestBody {

    @NotBlank
    @UserExists
    private String sendingUserId;

    @NotBlank
    @UserExists
    private String receivingUserId;

    @NotNull
    @Size(min= 2, max = 50)
    private String reference;

    @Min(1)
    @NotNull
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

    public String getSendingUserId() {
        return sendingUserId;
    }

    public void setSendingUserId(String sendingUserId) {
        this.sendingUserId = sendingUserId;
    }

    public String getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(String receivingUserId) {
        this.receivingUserId = receivingUserId;
    }
}