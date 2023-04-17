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

import static java.lang.Thread.sleep;

/**
 * @author <a href="mailto:macd@163.com">macd</a>
 * @version 2.0
 * @since 2.0
 */
public class Test {
    public static void main(String[] args) throws Exception {
      CompletableFuture<List<Integer>> res = null;
      boolean flag = true;
      if(flag) {
          res = CompletableFuture.supplyAsync(() -> {
              return null;
          });
      } else {
          CompletableFuture.supplyAsync(() -> {
              List<Integer> ls = new ArrayList<>();
              ls.add(1);
              return ls;
          });
      }
        System.out.println(res.get());
    }
}
