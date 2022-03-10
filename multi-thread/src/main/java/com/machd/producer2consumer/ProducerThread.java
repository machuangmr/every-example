package com.machd.producer2consumer;

import java.util.Queue;

/**
 * 生产者线程
 *
 * @author machd
 * @version 3.0
 * @date 2022/3/10
 */
public class ProducerThread extends Thread{
    private Queue<String> queue;
    private int size;
    public ProducerThread(Queue queue, int size) {
        this.queue = queue;
        this.size = size;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                if (queue.size() < size){
                    queue.offer("bag");
                    System.out.println("生产者生产了一个文件,队列还存在的bag数量" + queue.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.notify();
                } else {
                    System.out.println("当前队列中的数量=" + queue.size() + "生产者需要休息");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
