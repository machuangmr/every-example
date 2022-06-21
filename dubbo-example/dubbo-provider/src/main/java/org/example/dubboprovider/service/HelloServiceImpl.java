package org.example.dubboprovider.service;

import com.machd.IHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@Service// 这里的的注解是dubbo包下的service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello(String username) {
        return username + "hello Dubbo";
    }
}
