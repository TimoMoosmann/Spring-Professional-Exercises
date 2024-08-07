package local.timo.mybank_spring_boot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import local.timo.mybank_spring_boot.web.validation.user_exists.UserExists;

import java.util.Objects;

public final class UserTransactionForm {
    @NotBlank
    @UserExists
    private String receivingUserId;
    @NotNull
    @Min(1)
    private Integer amount;
    @NotBlank
    @Size(min = 2, max = 50)
    private String reference;

    public UserTransactionForm() {
    }

    public UserTransactionForm(
            String receivingUserId,
            Integer amount,
            String reference
    ) {
        this.receivingUserId = receivingUserId;
        this.amount = amount;
        this.reference = reference;
    }

    public String getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(String receivingUserId) {
        this.receivingUserId = receivingUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserTransactionForm) obj;
        return Objects.equals(this.receivingUserId, that.receivingUserId) &&
                Objects.equals(this.amount, that.amount) &&
                Objects.equals(this.reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receivingUserId, amount, reference);
    }

    @Override
    public String toString() {
        return "UserTransaction[" +
                "receivingUser=" + receivingUserId + ", " +
                "amount=" + amount + ", " +
                "reference=" + reference + ']';
    }

}
