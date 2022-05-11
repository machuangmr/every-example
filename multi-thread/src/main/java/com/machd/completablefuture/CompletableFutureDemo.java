package com.machd.completablefuture;

import javax.lang.model.element.VariableElement;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class CompletableFutureDemo {
    // 异步任务

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      // CompletableFuture.runAsync() 创建一个异步任务去执行，无返回结果
      // CompletableFuture.runAsync(Runnable, Executor) 创建一个异步任务去执行，无返回结果，可以指定自己的线程池
      //  CompletableFuture.supplyAsync(Supply) 创建一个异步任务去执行，有返回结果；
      // CompletableFuture.supplyAsync(Supply, Executor) // 创建一个异步任务去执行，有返回结果，可以指定自己的线程池
      // CompletableFuture.whenComplete(Res, Exception) //  可以支持链式调用，当上述步骤完成后，继续执行后面的操作，可以返回结果或者是抛出异常
      // ComletableFuture.exceptionally(Throwable) 直接抛出异常，可以感知到异常，并返回一个默认值
     // handler(Res, throwable) 可以对异常进行处理，如果有异常，那么可以指定返回的结果

      CompletableFuture.runAsync(()->{
          System.out.println("哈哈异步任务，thread_id=" + Thread.currentThread().getId());
      });

        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是带返回值的异步任务");
            return 123;
        }).handle((res, thrw) -> {
            if (res != null) {
                return res;
            }
            if (thrw != null) {
                return 10;
            }
            ;
            return 0;
        });


        CompletableFuture.runAsync(()->{
            System.out.println("哈哈异步任务，thread_id=" + Thread.currentThread().getId());
        });
        Integer integer = supplyAsync.get();
        System.out.println("我是主线程--> 返回值=" + integer);
    }
}
