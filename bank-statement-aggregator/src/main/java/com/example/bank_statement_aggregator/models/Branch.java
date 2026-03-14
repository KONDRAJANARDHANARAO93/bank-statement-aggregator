package com.example.bank_statement_aggregator.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    private String branchName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}