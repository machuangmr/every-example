package com.machd.proxy.dynamicproxy;

import java.util.function.IntPredicate;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/24
 */
public class TestProxy {
    public static void main(String[] args) {
        ZhangSan zhangSan = new ZhangSan();
        DynamicProxxy proxy = new DynamicProxxy();
       // proxy.setTarget(zhangSan); 通过set方法注入，
        IPerson instance = (IPerson) proxy.getInstance(zhangSan); // 通过构造器注入
        instance.findLove();
    }
}
