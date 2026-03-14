package com.example.bank_statement_aggregator.dto;

import lombok.Data;

@Data
public class StatementResponse {

    private Long statementId;
    private String statementDate;
    private String filePath;
    private Long userId;
    private Long companyId;
}