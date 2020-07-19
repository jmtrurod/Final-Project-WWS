package com.ironhack.edgeservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(UserMicroserviceFail.class)
    public void handleUserMicroserviceFail(UserMicroserviceFail e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getMessage());
    }

    @ExceptionHandler(PostMicroserviceFail.class)
    public void handlePostMicroserviceFail(PostMicroserviceFail e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getMessage());
    }

    @ExceptionHandler(CityMicroserviceFail.class)
    public void handleCityMicroserviceFail(CityMicroserviceFail e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getMessage());
    }
}
