package com.machd.proxy.dynamicproxy;

/**
 * 具体实现
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/24
 */
public class ZhangSan implements IPerson{

    @Override
    public void findLove() {
        System.out.println("张三需要进行相亲");
    }
}
