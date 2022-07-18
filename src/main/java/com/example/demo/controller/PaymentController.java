package com.example.demo.controller;

import com.example.demo.dto.Payment;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {

        this.service = service;
    }

    @PostMapping(value = "/payment/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PaymentResponse addPayment(@RequestBody Payment payment) {
        return service.addPayment(payment);
    }
}
