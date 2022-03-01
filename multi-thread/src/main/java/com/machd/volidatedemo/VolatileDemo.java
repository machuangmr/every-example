package com.machd.volidatedemo;

/**
 * 线程的可见性
 *
 * @author machd
 * @version 3.0
 * @date 2022/3/1
 */
public class VolatileDemo {
    /**
     * 如果这里没有使用到volidate 关键字，那么子线程是不会停下来的,
     * 我们可以简单的理解： 虽然主线程中对于flag变量进行了更改，但是对于子线程中是不可见的
     * 我们称之为线程的可见性
     *
     * 假如在flag 变量上增加了volatile 关键字后，那么子线程就可以正常的结束，我们可以简单的认为
     * 加了volatile后，这个flag对于子线程是可见的。
     */


    private static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {

        Thread t1  = new Thread(() -> {
            int i = 0;
            while(!flag){
                i++;
                //System.out.println("current i = " + i);
            }
        });

        t1.start();
        System.out.println("main thread is working....");
        Thread.sleep(1000);
        flag = true;
    }
}
