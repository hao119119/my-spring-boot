package com.example.myproject.service;

import com.example.myproject.domain.Customer;
import com.example.myproject.domain.CustomerSortRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by nobody on 2016/8/10.
 */
@Service
public class CustomerService {

    CustomerSortRepository repository;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerService(CustomerSortRepository repository){
        this.repository = repository;
        Page<Customer> customers = repository.findAll(new PageRequest(1,3));

        log.info("---customer service---");
        for(Customer customer:customers){
            log.info(customer.toString());
        }

    }
}
