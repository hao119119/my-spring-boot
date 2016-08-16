package com.example.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nobody on 2016/8/10.
 */
public class DatabaseAccountService implements AccountService {
    private final RiskAssessor riskAssessor;

    @Autowired
    public DatabaseAccountService(RiskAssessor riskAssessor){
        this.riskAssessor = riskAssessor;
    }
}
