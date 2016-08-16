/**
 * @(#)MyService.java V1.2.0 16-8-16 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.service;

import com.example.myproject.config.ConnectionProperties;
import com.example.myproject.config.ProductionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-16
 */

@Service
public class MyService {
    private final ConnectionProperties connectionProperties;

    private final ProductionConfiguration productionConfiguration;



    @Autowired
    public MyService(ConnectionProperties connectionProperties, ProductionConfiguration productionConfiguration){
        this.connectionProperties = connectionProperties;
        this.productionConfiguration = productionConfiguration;
        System.out.println(connectionProperties.getUsername());
        System.out.println("production" + productionConfiguration.getName());
    }
}
