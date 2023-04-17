
package com.machd.sentineldemo.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @author
 * @version 2.0
 * @since 2.0
 */
public class RateLimiteSample {

    // 每秒钟创建10个令牌
    RateLimiter rateLimiter = RateLimiter.create(10);

    public static void main(String[] args) {
        Deque<Integer> path = new ArrayDeque<>();
        for (Integer integer : path) {
            
        }
    }
}
