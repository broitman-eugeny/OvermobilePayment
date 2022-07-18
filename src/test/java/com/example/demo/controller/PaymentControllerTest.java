package com.example.demo.controller;

import com.example.demo.dto.Payment;
import com.example.demo.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class PaymentControllerTest {

    PaymentController controller;

    @Mock
    PaymentService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        controller = new PaymentController(service);
    }

    @Test
    void addPayment_serviceAddPaymentInvoked() {

        controller.addPayment(new Payment());

        Mockito.verify(service).addPayment(Mockito.any());
    }
}
