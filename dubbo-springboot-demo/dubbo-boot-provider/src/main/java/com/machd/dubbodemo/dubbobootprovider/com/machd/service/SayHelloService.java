package com.machd.dubbodemo.dubbobootprovider.com.machd.service;

import com.machd.ISay;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/6/21
 */
@DubboService
public class SayHelloService implements ISay {

    @Override
    public String sayHello(String name) {
        return "hello" + name + "!!!!";
    }
}
