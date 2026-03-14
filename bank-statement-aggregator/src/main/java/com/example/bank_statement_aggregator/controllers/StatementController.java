package com.example.bank_statement_aggregator.controllers;

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
    public BankStatement generateStatement(@PathVariable Long userId) throws IOException {
        return statementService.generateStatement(userId);
    }
}