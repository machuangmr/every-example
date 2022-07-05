package com.machd.dubbodemo.dubbospringbootdemo.com.machd.controller;

import com.machd.ISay;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/6/21
 */
@Controller
public class HelloController {

    @DubboReference
    private ISay sayService;

    @RequestMapping("/say")
    @ResponseBody
    public String say(@RequestParam("msg") String msg) {
        return sayService.sayHello(msg);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String msg() {
        return "1232";
    }
}
