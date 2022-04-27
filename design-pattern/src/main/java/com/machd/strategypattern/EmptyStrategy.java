package com.machd.strategypattern;

/**
 * 没有任何优惠
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/26
 */
public class EmptyStrategy implements IPromotion{
    @Override
    public void doPromotion() {
        System.out.println("没有任何优惠");
    }
}
