package local.timo.mybank_spring_boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import local.timo.mybank_spring_boot.web.validation.user_not_exists.UserNotExists;

public record UserRequestBody(
        @NotBlank @Size(min = 2, max = 10) @UserNotExists String id,
        @JsonProperty("first_name") @NotBlank @Size(min = 2, max = 100) String firstName,
        @JsonProperty("last_name") @NotBlank @Size(min = 2, max = 100) String lastName
) {
}
