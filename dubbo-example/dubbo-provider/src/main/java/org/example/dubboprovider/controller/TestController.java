package org.example.dubboprovider.controller;

import com.machd.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class TestController {

    /**
     * 当前测试结果，如果一个bean被@DubboService标注，那么同样放在spring IoC容器中，同样可以被使用
     */
    @Autowired
    private IHelloService helloService;

    @GetMapping("/test")
    public String test() {
      return helloService.hello("hahahaha, just for test");
       // return "1111";
    }

    @GetMapping("/test2")
    public String test2() {
        return "hhhhh";
    }
}
