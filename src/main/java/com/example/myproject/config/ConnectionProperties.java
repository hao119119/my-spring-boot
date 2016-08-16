/**
 * @(#)ConnectionProperties.java V1.2.0 16-8-16 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-16
 */
@ConfigurationProperties(prefix = "connection")
public class ConnectionProperties {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(InetAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    private String username;

    private InetAddress remoteAddress;
}
