package com.example.bank_statement_aggregator.controllers;

import com.example.bank_statement_aggregator.dto.TransactionResponse;
import com.example.bank_statement_aggregator.models.Transaction;
import com.example.bank_statement_aggregator.services.StatementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController
{
    private final StatementService statementService;
    public TransactionController(StatementService statementService)
    {
        this.statementService=statementService;
    }
    @GetMapping("/statement/{statementId}")
    public List<TransactionResponse> getTransactions(@PathVariable Long statementId) {

        List<Transaction> transactions =
                statementService.getTransactionByStatement(statementId);
    return transactions.stream().map(t -> {
            TransactionResponse response = new TransactionResponse();
            response.setTransactionId(t.getTransactionId());
            response.setDate(t.getDate().toString());
            response.setAmount(t.getAmount());
            response.setDescription(t.getDescription());
            return response;
        }).toList();
    }

}
