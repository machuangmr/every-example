package com.machd.producer2consumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 测试生产者消费者模型
 *
 * @author machd
 * @version 3.0
 * @date 2022/3/10
 */
public class TestProducer2Consumer {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int size = 10;
        ProducerThread producerThread = new ProducerThread(queue, size);
        ConsumerThread consumerThread = new ConsumerThread(queue, size);
        producerThread.start();
        consumerThread.start();
    }
}
