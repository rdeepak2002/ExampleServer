package com.deepak.exampleserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by deepak on 6/4/17.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
                    reason = "one of the request parameters was invalid")
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadInput() {
        //do nothing
    }
}
