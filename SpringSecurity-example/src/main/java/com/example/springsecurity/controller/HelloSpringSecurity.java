package com.example.springsecurity.controller;

import com.example.springsecurity.service.impl.FactoryOcrStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@RestController
public class HelloSpringSecurity {
    @GetMapping("/hello")
    public String helloSpringSecurity() {
        return "hello Spring Security!!!";
    }

    @Autowired
    private FactoryOcrStrategy factoryOcrStrategy;
    @GetMapping("/testOcr")
    public String testOcr(String ocrType) {
        return factoryOcrStrategy.getType(ocrType).execute("123123");
    }
}
