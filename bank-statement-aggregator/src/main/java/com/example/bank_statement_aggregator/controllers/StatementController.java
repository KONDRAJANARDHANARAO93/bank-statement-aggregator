package com.example.bank_statement_aggregator.controllers;

import com.example.bank_statement_aggregator.dto.StatementResponse;
import com.example.bank_statement_aggregator.models.BankStatement;
import com.example.bank_statement_aggregator.services.StatementService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/statements")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping("/generate/{userId}")
    public StatementResponse generateStatement(@PathVariable Long userId) throws IOException {
        BankStatement statement=statementService.generateStatement(userId);
        StatementResponse response=new StatementResponse();
        response.setStatementId(statement.getStatementId());
        response.setStatementDate(statement.getStatementDate().toString());
        response.setFilePath(statement.getFilePath());
        response.setUserId(statement.getUser().getUserId());
        response.setCompanyId(statement.getCompany().getCompanyId());
        return response;

    }
}