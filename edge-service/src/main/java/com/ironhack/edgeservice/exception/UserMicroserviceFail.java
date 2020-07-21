package com.ironhack.edgeservice.exception;

public class UserMicroserviceFail extends RuntimeException{
    public UserMicroserviceFail(String message) {
        super(message);
    }
}