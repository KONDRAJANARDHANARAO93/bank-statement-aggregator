package com.example.bank_statement_aggregator.repositories;

import com.example.bank_statement_aggregator.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}