package com.machd;

/**
 * 使用java中的特殊语法: java 的静态内部类
 * 1、java类中的静态成员变量，在类初始化的时候就会加载
 * 2、java中的静态内部类，只有在使用的时候才会去加载，不会造成内存泄漏
 * 3、缺点：能够被反射破坏
 * @author machd
 * @version 3.0
 * @date 2022/4/23
 */
public class LazyStaticInnerClassSingleTon {

    private LazyStaticInnerClassSingleTon(){
        // 使用单例可以进行破坏。继续进行改造，但是写法不够优雅
        if(LazyHolder.INSTANCE != null) {
            throw new RuntimeException("不允许非法访问");
        }
        System.out.println();

    };

    public LazyStaticInnerClassSingleTon getInstance() {
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final LazyStaticInnerClassSingleTon INSTANCE = new LazyStaticInnerClassSingleTon();
    }
}
