package com.example.demo.service;

import com.example.demo.dto.Payment;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.exception.PaymentException;
import com.example.demo.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public PaymentResponse addPayment(Payment payment) {

        PaymentResponse status = new PaymentResponse();

        try {

            synchronized (PaymentService.class) {

                Optional<Payment> dbPayment = repository.findById(payment.getId());

                if (dbPayment.isPresent()){

                    status.setStatus(1);
                } else {

                    repository.save(payment);
                    status.setStatus(0);
                }
            }

        } catch (Exception ex) {
            log.error("Failed to receive or save payment: " + payment + "\n" + ex.getMessage());
            throw new PaymentException("Failed to receive or save payment: " + payment);
        }

        return status;
    }
}
