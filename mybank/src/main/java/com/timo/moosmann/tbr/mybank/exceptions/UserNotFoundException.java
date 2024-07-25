package com.timo.moosmann.tbr.mybank.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userId) {
        super("Couldn't find user with id: " + userId);
    }
}
