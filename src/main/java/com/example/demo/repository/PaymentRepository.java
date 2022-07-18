package com.example.demo.repository;

import com.example.demo.dto.Payment;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String>{

    Optional<Payment> findById(String id);
}
