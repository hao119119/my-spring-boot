/**
 * @(#)MyRestController.java V1.2.0 16-8-16 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.web;

import com.example.myproject.bean.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-16
 */

@RestController
@RequestMapping(value="/users")
public class MyRestController {

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long user){
        return new User("" + user);
    }

    @RequestMapping(value = "/{user}/customers", method = RequestMethod.GET)
    public String getUserCustomers(@PathVariable Long user){
        return "customer";

    }

    @RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long user){
        System.out.println("delete user");
        return "" + user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    // the User must have the default constructor
    public String login(@RequestBody User user){
        System.out.println(user.getName());
        return user.getName();
    }
}
