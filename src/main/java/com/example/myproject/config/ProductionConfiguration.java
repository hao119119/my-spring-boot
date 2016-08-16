/**
 * @(#)ProductionConfiguration.java V1.2.0 16-8-16 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-16
 */
@Configuration
@Profile("production")
public class ProductionConfiguration {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Value("${name}")
    private String name;
}
