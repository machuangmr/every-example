package com.machd.threaddemo;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/2/28
 */
public class InterruptedDemo implements Runnable{
    public static void main(String[] args) {
      Thread t1 = new Thread(new InterruptedDemo());
      t1.start();
      //System.out.println("z");
      System.out.println("主线程已经运行结束");
      // 主线程发送一个中断信号到子线程
      t1.interrupt();
    }


    @Override
    public void run() {
        // 如果让线程友好的结束， 只有当前的run方法中的程序知道，
        // Thread.currentThreadd().isInterrupted() 获取中断状态
        while(!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 子线程抛出这个中断异常，然后可以选择继续下一步的操作
                Thread.currentThread().interrupt();// 捕捉到获取到这个中断后，可以交由自己进行处理。
            }
            System.out.println("Thread.name==" + Thread.currentThread().getName());
        }
    }
}
