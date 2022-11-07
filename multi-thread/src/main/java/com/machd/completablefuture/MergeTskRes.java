/**************************************************************************
 * Copyright (c) 2006-2017 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 *
 * 项目名称：义乌国际贸易
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.machd.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author macd
 * @version 2.0
 * @since 2.0
 * 合并多个任务的结果
 */
public class MergeTskRes {
    public static void main(String[] args) {
        CompletableFuture<List<Integer>> res1 = CompletableFuture.supplyAsync(()-> {
            List<Integer> result = new ArrayList<>();
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            result.add(1);
            result.add(2);
            result.add(3);
            return result;
        });


        CompletableFuture<List<Integer>> res2 = CompletableFuture.supplyAsync(()-> {
            List<Integer> result = new ArrayList<>();
            result.add(4);
            result.add(5);
            result.add(6);
            return result;
        });

        CompletableFuture<Object> voidCompletableFuture = CompletableFuture.anyOf(res1, res2);
//        try {
//            Object o = voidCompletableFuture.get();
//            System.out.println(o);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        try {
            System.out.println(voidCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
