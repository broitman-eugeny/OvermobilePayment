package com.example.demo.service;

import com.example.demo.dto.Payment;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.exception.PaymentException;
import com.example.demo.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

class PaymentServiceTest {

    PaymentService service;

    @Mock
    PaymentRepository repository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        service = new PaymentService(repository);
    }

    @Test
    void addPayment_dbPaymentIsPresent() {

        Payment payment = new Payment();
        payment.setId("1");
        payment.setUserId(1L);
        payment.setSum(BigDecimal.valueOf(125));

        Optional<Payment> dbPayment = Optional.of(payment);

        Mockito.when(repository.findById(payment.getId())).thenReturn(dbPayment);

        PaymentResponse response = service.addPayment(payment);

        Assertions.assertEquals(1, response.getStatus());

        Mockito.verify(repository).findById(payment.getId());
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void addPayment_dbPaymentIsNotPresent() {

        Payment payment = new Payment();
        payment.setId("1");
        payment.setUserId(1L);
        payment.setSum(BigDecimal.valueOf(125));
        Optional<Payment> dbPayment = Optional.empty();

        Mockito.when(repository.findById(payment.getId())).thenReturn(dbPayment);

        PaymentResponse response = service.addPayment(payment);

        Assertions.assertEquals(0, response.getStatus());

        Mockito.verify(repository).findById(payment.getId());
        Mockito.verify(repository).save(payment);
    }

    @Test
    void addPayment_exceptionProcessed() {

        Payment payment = new Payment();
        payment.setId("1");
        payment.setUserId(1L);
        payment.setSum(BigDecimal.valueOf(125));

        Mockito.doThrow(RuntimeException.class).when(repository).findById(payment.getId());

        Assertions.assertThrows(PaymentException.class, () -> service.addPayment(payment));

        Mockito.verify(repository).findById(payment.getId());
        Mockito.verify(repository, Mockito.never()).save(payment);
    }
}
