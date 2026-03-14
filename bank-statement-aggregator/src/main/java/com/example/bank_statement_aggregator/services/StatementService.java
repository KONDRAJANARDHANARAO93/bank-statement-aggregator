package com.example.bank_statement_aggregator.services;

import com.example.bank_statement_aggregator.models.BankStatement;
import com.example.bank_statement_aggregator.models.User;
import com.example.bank_statement_aggregator.repositories.BankStatementRepository;
import com.example.bank_statement_aggregator.repositories.UserRepository;
import com.example.bank_statement_aggregator.utils.StatementGenerator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class StatementService {

    private final UserRepository userRepository;
    private final BankStatementRepository bankStatementRepository;

    public StatementService(UserRepository userRepository,
                            BankStatementRepository bankStatementRepository) {
        this.userRepository = userRepository;
        this.bankStatementRepository = bankStatementRepository;
    }

    public BankStatement generateStatement(Long userId) throws IOException {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        String companyName = user.getCompany().getCompanyName();

        String filePath = StatementGenerator.generateStatement(companyName, userId);

        BankStatement statement = new BankStatement();
        statement.setUser(user);
        statement.setCompany(user.getCompany());
        statement.setBranch(null);
        statement.setStatementDate(LocalDate.now());
        statement.setFilePath(filePath);

        return bankStatementRepository.save(statement);
    }
}