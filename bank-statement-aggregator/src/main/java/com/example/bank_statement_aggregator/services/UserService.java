package com.example.bank_statement_aggregator.services;

import com.example.bank_statement_aggregator.dto.UserLoginRequest;
import com.example.bank_statement_aggregator.dto.UserRegisterRequest;
import com.example.bank_statement_aggregator.models.Company;
import com.example.bank_statement_aggregator.models.User;
import com.example.bank_statement_aggregator.repositories.CompanyRepository;
import com.example.bank_statement_aggregator.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       CompanyRepository companyRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegisterRequest request) {

        Optional<Company> companyOptional = companyRepository.findById(request.getCompanyId());

        if (companyOptional.isEmpty()) {
            throw new RuntimeException("Company not found");
        }

        Company company = companyOptional.get();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCompany(company);

        return userRepository.save(user);
    }

    public String loginUser(UserLoginRequest request) {

        Optional<User> userOptional = userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .findFirst();

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Login successful";
        }

        return "Invalid password";
    }
}