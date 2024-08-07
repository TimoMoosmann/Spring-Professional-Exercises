package local.timo.mybank_spring_boot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValidationError {
    private String message;

    @JsonProperty("invalid_fields")
    private List<String> invalidFields;

    public ValidationError(String message, List<String> invalidFields) {
        this.message = message;
        this.invalidFields = invalidFields;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(List<String> invalidFields) {
        this.invalidFields = invalidFields;
    }
}
