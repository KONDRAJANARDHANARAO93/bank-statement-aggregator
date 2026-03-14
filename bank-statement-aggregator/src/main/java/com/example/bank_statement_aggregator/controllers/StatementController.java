package com.example.bank_statement_aggregator.controllers;

import com.example.bank_statement_aggregator.dto.StatementResponse;
import com.example.bank_statement_aggregator.models.BankStatement;
import com.example.bank_statement_aggregator.services.StatementService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/statements")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping("/generate/{userId}")
    public BankStatement generateStatement(@PathVariable Long userId) throws IOException {
        return statementService.generateStatement(userId);
    }
    @GetMapping("/user/{userId}")
    public List<StatementResponse> getStatements(@PathVariable Long userId) {

        List<BankStatement> statements = statementService.getStatementsByUser(userId);

        return statements.stream().map(statement -> {
            StatementResponse response = new StatementResponse();
            response.setStatementId(statement.getStatementId());
            response.setStatementDate(statement.getStatementDate().toString());
            response.setFilePath(statement.getFilePath());
            response.setUserId(statement.getUser().getUserId());
            response.setCompanyId(statement.getCompany().getCompanyId());
            return response;
        }).toList();
    }
}