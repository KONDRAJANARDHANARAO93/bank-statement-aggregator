package com.example.bank_statement_aggregator.repositories;

import com.example.bank_statement_aggregator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}