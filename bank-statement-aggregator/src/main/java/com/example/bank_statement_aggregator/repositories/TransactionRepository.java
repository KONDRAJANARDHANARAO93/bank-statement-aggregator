package com.example.bank_statement_aggregator.repositories;

import com.example.bank_statement_aggregator.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByBankStatementStatementId(Long statementId);
}