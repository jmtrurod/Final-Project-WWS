package com.ironhack.edgeservice.exception;

public class PostMicroserviceFail extends RuntimeException{
    public PostMicroserviceFail(String message) {
        super(message);
    }
}