package com.machd.producer2consumer;

import java.util.Queue;

/**
 * 消费者线程
 *
 * @author machd
 * @version 3.0
 * @date 2022/3/10
 */
public class ConsumerThread extends Thread{
    private Queue<String> queue;
    private int size;
    public ConsumerThread(Queue queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    System.out.println("队列是空的，生产者需要暂时休息");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String str = queue.poll();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("从队列中拿出数据"+ str + "还剩余=" + queue.size());
                queue.notify();
            }
        }
    }
}
