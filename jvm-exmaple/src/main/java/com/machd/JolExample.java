package com.machd;

import org.openjdk.jol.info.ClassLayout;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class JolExample {
    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());
        //
        // -XX:-UseCompressedClassPointers
        // ‐XX:‐UseCompressedOops
        // -XX:-UseCompressedOops
        System.out.println("---》");
        ClassLayout classLayout = ClassLayout.parseInstance(new A());
        System.out.println(classLayout.toPrintable());
    }
    public static class A {
        String name; // ‐XX:‐UseCompressedOops 关闭指针压缩。则占用8Byte,开启指针压缩占用4个Byte
        Object o;
    }
}
