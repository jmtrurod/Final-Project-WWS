package com.ironhack.postservice.exception;

public class InputNotAllowed extends RuntimeException{
    public InputNotAllowed(String message) {
        super(message);
    }
}