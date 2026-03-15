package com.example.bank_statement_aggregator.dto;

import lombok.Data;

@Data
public class TransactionResponse {

    private Long transactionId;
    private String date;
    private Double amount;
    private String description;

}