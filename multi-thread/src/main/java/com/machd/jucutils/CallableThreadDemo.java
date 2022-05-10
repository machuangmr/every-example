package com.machd.jucutils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/5/10
 */
public class CallableThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallDemo());
        new Thread(futureTask).start();
        System.out.println("主线程开始运行");
        int res = futureTask.get(); // 这里会一直等待子线程的执行结果返回。
        System.out.println("子线程的结果=" + res);
    }

    public static class CallDemo implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程id=" + Thread.currentThread().getId());
            int res = 10 /2;
            return res;
        }
    }
}
