package com.timo.moosmann.tbr.mybank.web;

import com.timo.moosmann.tbr.mybank.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalControllerErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> invalidFields = methodArgumentNotValidException.getFieldErrors().stream().map(
                FieldError::getField
        ).toList();

        return new ValidationError(
                methodArgumentNotValidException.getMessage(),
                invalidFields
        );
    }
}
