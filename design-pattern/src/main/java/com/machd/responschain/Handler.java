package com.machd.responschain;

/**
 * 处理器类的抽象父类
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/27
 */
public abstract class Handler {
    protected Handler next;

    protected void next(Handler next) {
        this.next = next;
    }
    public abstract void doHandler(Member member);

}
