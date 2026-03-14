package com.example.bank_statement_aggregator.dto;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String email;
    private String password;

}