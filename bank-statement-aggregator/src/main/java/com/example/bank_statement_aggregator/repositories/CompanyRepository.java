package com.example.bank_statement_aggregator.repositories;

import com.example.bank_statement_aggregator.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}