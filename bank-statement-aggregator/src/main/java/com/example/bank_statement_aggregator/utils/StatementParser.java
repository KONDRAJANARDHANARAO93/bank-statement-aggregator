package com.example.bank_statement_aggregator.utils;

import com.example.bank_statement_aggregator.models.BankStatement;
import com.example.bank_statement_aggregator.models.Transaction;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class StatementParser {

    public static List<Transaction> parseTransactions(String filePath, BankStatement statement) {

        List<Transaction> transactions = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {

            List<String[]> rows = reader.readAll();

            // skip header row
            for (int i = 1; i < rows.size(); i++) {

                String[] row = rows.get(i);

                Transaction transaction = new Transaction();

                transaction.setDate(java.time.LocalDate.parse(row[1]));
                transaction.setAmount(Double.parseDouble(row[2]));
                transaction.setDescription(row[3]);
                transaction.setBankStatement(statement);

                transactions.add(transaction);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parsing statement file", e);
        }

        return transactions;
    }
}