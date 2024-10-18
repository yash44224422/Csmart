package org.yashrajguru.csmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yashrajguru.csmart.model.Users;
import org.yashrajguru.csmart.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService service;


    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        //        System.out.println(users);
        return service.verify(user);              //        return "Success";
    }
}