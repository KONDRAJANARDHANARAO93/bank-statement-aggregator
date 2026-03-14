package com.example.bank_statement_aggregator.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDate date;

    private Double amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "statement_id")
    private BankStatement bankStatement;

}