package com.ironhack.cityservice.exception;

public class InputNotAllowed extends RuntimeException{
    public InputNotAllowed(String message) {
        super(message);
    }
}