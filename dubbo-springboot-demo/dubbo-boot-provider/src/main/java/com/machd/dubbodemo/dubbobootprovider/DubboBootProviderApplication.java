package com.machd.dubbodemo.dubbobootprovider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@DubboComponentScan("com.machd.dubbodemo.dubbobootprovider.com.machd.service")
@SpringBootApplication
public class DubboBootProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboBootProviderApplication.class, args);
    }

}
