package com.mini.erp.user.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException() {
        super("User already exists");
    }
}
