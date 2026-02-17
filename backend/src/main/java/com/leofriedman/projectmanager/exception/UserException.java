package com.leofriedman.projectmanager.exception;

public class UserException extends RuntimeException {
    private String message;

    public UserException(String message) {
        super(message);
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
