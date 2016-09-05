/**
 * @(#)City.java V1.2.0 16-8-31 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-31
 */

@Entity
public class City implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected City() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly

    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }



}
