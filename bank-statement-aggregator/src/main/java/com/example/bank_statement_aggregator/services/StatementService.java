package com.example.bank_statement_aggregator.services;

import com.example.bank_statement_aggregator.models.BankStatement;
import com.example.bank_statement_aggregator.models.Transaction;
import com.example.bank_statement_aggregator.models.User;
import com.example.bank_statement_aggregator.repositories.BankStatementRepository;
import com.example.bank_statement_aggregator.repositories.TransactionRepository;
import com.example.bank_statement_aggregator.repositories.UserRepository;
import com.example.bank_statement_aggregator.utils.StatementGenerator;
import com.example.bank_statement_aggregator.utils.StatementParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StatementService
{

    private final UserRepository userRepository;
    private final BankStatementRepository bankStatementRepository;
    private final TransactionRepository transactionRepository;

    public StatementService(UserRepository userRepository,
                            BankStatementRepository bankStatementRepository,TransactionRepository transactionRepository)
    {
        this.userRepository = userRepository;
        this.bankStatementRepository = bankStatementRepository;
        this.transactionRepository=transactionRepository;
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

        BankStatement savedStatement = bankStatementRepository.save(statement);

        List<Transaction> transactions =
                StatementParser.parseTransactions(filePath, savedStatement);

        transactionRepository.saveAll(transactions);

        return savedStatement;
    }
    public List<BankStatement> getStatementsByUser(Long userId) {
        return bankStatementRepository.findByUserUserId(userId);
    }



}