/**
 * @(#)User.java V1.2.0 16-8-16 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.Bean;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-16
 */
public class User {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String id){
        this.id = id;
        name = "haha";
    }


}
