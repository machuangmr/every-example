package com.machd;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class TestIntern {
    public static void main(String[] args) {
        String str = new StringBuilder().append("计算机").append("技术").toString();
        // 在jdk1.7以后，字符串常量池转移到堆中，
        // intern()方法 返回常量池中的字符串的引用，
        // 如果常量池中没有，那么就返回在堆中创建的字符创的引用。
        System.out.println(str == str.intern());

        // 因为java是 java中指令的关键字，在字符串常量池中可能早已缓存，
        // 所以java调用 intern 方法比较是false
        String str2 = new StringBuilder("jav").append("a").toString();
        System.out.println(str2 == str2.intern());
    }
}
