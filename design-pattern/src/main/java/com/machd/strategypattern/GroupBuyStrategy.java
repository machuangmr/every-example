package com.machd.strategypattern;

/**
 * 团购优惠
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/26
 */
public class GroupBuyStrategy implements IPromotion{
    @Override
    public void doPromotion() {
        System.out.println("团购优惠");
    }
}
