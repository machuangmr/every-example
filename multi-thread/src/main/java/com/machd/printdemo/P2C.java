package com.machd.printdemo;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *  生产者消费者模型
 * @author <a href="mailto:macd@163.com">macd</a>
 * @version 2.0
 * @since 2.0
 */
public class P2C {
    /**
     * 思路：1、共享的内存队列，阻塞队列，用于充当两个线程之间的介质
     *      2、同一个内存队列对象，可以做同一把锁，
     *      3、生产者一直offer对象，直到内存队列满，然后阻塞，释放锁，唤醒消费者线程，获取内存对象
     *      4、消费者一直take对象，直到内存队列为空，然后阻塞，释放锁，唤醒生产者线程
     */
    private static final BlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<Integer>(10);
    private static final Integer CAPACITY = 10;
    public static void main(String[] args) {
        Thread p = new Thread(new P());
        Thread c = new Thread(new C());
        p.start();
        c.start();

    }
    static class P implements Runnable{

        @Override
        public void run() {
            while(true){
                synchronized (QUEUE) {
                    if(QUEUE.size() < CAPACITY) {
                        QUEUE.offer(1);

                        System.out.println("生产者生成了一个文件,当前队列中的容量是=" +QUEUE.size());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        QUEUE.notifyAll();// 唤醒消费者，开始消费
                    } else {
                        System.out.println("队列中的容量已经满了，生产者停止生产");
                        try {
                            QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    static class C implements Runnable{

        @Override
        public void run() {
            while (true) {
                synchronized (QUEUE) {
                   if(QUEUE.isEmpty()){
                       System.out.println("队列已经空了，消费者停止消费");
                       try {
                           QUEUE.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   } else {
                       try {
                           QUEUE.take();
                           System.out.println("从队列中拿走了一个元素，队列剩余容量为=" + QUEUE.size());
                           TimeUnit.SECONDS.sleep(1);
                           QUEUE.notifyAll();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }
        }
    }

}
