package com.example.bank_statement_aggregator.repositories;

import com.example.bank_statement_aggregator.models.BankStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankStatementRepository extends JpaRepository<BankStatement, Long> {
}