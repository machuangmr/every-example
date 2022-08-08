package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@Controller
public class LoginController {

    @PostMapping("login")
    public String login(String username, String password){
        System.out.println("name=" + username + "passord=" + password);
        return "home";
    }
}
