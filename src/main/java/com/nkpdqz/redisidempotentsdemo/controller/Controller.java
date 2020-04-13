package com.nkpdqz.redisidempotentsdemo.controller;

import com.nkpdqz.redisidempotentsdemo.anno.AutoIdempotent;
import com.nkpdqz.redisidempotentsdemo.pojo.User;
import com.nkpdqz.redisidempotentsdemo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private TokenService service;

    @AutoIdempotent
    @GetMapping("/user/")
    public User getUser(){
        return new User("111","nl");
    }

    @GetMapping("/token/")
    public String getToken(){
        return service.createToken();
    }
}
