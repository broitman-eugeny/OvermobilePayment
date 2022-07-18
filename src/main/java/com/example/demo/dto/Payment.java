package com.example.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@JsonDeserialize
public class Payment {

    @Id
    private String id;

    private Long userId;

    private BigDecimal sum;
}
