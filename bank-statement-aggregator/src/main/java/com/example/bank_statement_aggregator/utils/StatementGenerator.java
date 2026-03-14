package com.example.bank_statement_aggregator.utils;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class StatementGenerator {

    public static String generateStatement(String companyName, Long userId) throws IOException {

        String fileName = "statement_" + userId + "_" + UUID.randomUUID() + ".csv";

        CSVWriter writer = new CSVWriter(new FileWriter(fileName));

        String[] header = {"TransactionId", "Date", "Amount", "Description", "Company"};

        writer.writeNext(header);

        writer.writeNext(new String[]{"TXN001", "2026-03-01", "5000", "Salary", companyName});
        writer.writeNext(new String[]{"TXN002", "2026-03-02", "-1500", "Groceries", companyName});
        writer.writeNext(new String[]{"TXN003", "2026-03-03", "-700", "Electricity Bill", companyName});

        writer.close();

        return fileName;
    }
}