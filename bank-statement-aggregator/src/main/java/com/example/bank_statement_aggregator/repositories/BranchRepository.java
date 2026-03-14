package com.example.bank_statement_aggregator.repositories;

import com.example.bank_statement_aggregator.models.BankStatement;
import com.example.bank_statement_aggregator.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}