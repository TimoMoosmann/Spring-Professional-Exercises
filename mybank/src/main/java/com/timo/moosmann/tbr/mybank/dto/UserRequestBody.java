package com.timo.moosmann.tbr.mybank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestBody(
        @NotBlank @Size(min = 2, max = 10) String id,
        @JsonProperty("first_name") @NotBlank @Size(min = 2, max = 100) String firstName,
        @JsonProperty("last_name") @NotBlank @Size(min = 2, max = 100) String lastName
) {
}
