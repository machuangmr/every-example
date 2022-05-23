package org.example.dubboconsumer.controller;

import com.machd.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
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
public class HelloController {

    @Reference(generic = true)//表示使用dubbo协议去构建这个代理对象
    private IHelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello("machd");
    }

}
