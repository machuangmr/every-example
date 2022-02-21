package com.machd;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class AllcateOnStack {
    // -Xms1m -Xmx1m -XX:-DoEscapeAnalysis -XX:+PrintGC
    // 关闭逃逸分析，则对象创建在对上，会频繁发生gc
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i = 0;i < 100000000;i++) {
            allo();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间=>" + (end - start) / 1000);
    }

    public static void allo() {
        User user = new User();
        user.setId(1L);
        user.setName("machd");
    }
}
