package com.example.myproject;

import com.example.myproject.config.ConnectionProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by nobody on 2016/8/10.
 */

@Configuration
//@EnableAutoConfiguration
@EnableConfigurationProperties(ConnectionProperties.class)
public class MyConfiguration {
}
