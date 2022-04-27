package com.machd.strategypattern;

/**
 * 返现优惠
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/26
 */
public class CashBackStrategy implements IPromotion{
    @Override
    public void doPromotion() {
        System.out.println("返现优惠");
    }
}
