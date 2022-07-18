package com.example.demo.controller;

import com.example.demo.dto.AlertResponse;
import com.example.demo.exception.PaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<AlertResponse> handleException(PaymentException ex) {

        AlertResponse response = new AlertResponse(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
