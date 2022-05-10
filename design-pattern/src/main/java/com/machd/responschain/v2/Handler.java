package com.machd.responschain.v2;

import com.machd.responschain.Member;

/**
 * 通常在指责链模式中，由于对于指责链之间的关联顺序不好控制
 * 我们会使用
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public abstract class Handler<T> {
    protected Handler<T> next;
    public void next(Handler<T> next) {
        this.next = next;
    }
    public abstract void doHandler(Member member);


    static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;
        public Builder<T> add(Handler<T> handler) {
            if(this.head == null){
                this.head = this.tail = handler;
                return this;
            }
            this.tail.next = handler;
            this.tail = handler;
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }
    }
}
