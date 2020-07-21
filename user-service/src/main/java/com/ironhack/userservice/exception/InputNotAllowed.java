package com.ironhack.userservice.exception;

public class InputNotAllowed extends RuntimeException{
    public InputNotAllowed(String message) {
        super(message);
    }
}