package com.example.bank_statement_aggregator.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {

    private String username;
    private String email;
    private String password;
    private Long companyId;

}