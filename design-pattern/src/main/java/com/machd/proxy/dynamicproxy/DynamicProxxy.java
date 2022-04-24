package com.machd.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/24
 */
public class DynamicProxxy implements InvocationHandler {
    // 被代理的目标类
    private Object target;

//    public void setTarget(Object target) {
//        this.target = target;
//    }

    public Object getInstance(Object target) {
        this.target = target;
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 此时我们可以在反射的方法执行之前和之后做相应的改动
        System.out.println("我是代理类，我开始做我的事情---");
        Object invoke = method.invoke(target, args);
        System.out.println("我把我该做的事情都已经做完了·····");
        return invoke;
    }
}
