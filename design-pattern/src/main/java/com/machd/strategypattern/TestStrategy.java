package com.machd.strategypattern;

import java.util.Set;

/**
 * 测试策略模式
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/26
 */
public class TestStrategy {
    public static void main(String[] args) {
        // 一般会将所有的策略返回给前端，让用户进行选择
        Set<String> allStrategy = PromotionFactory.getAllStrategy();
        String strategy = "CASHBACK";
        // 此时根据用户选择的对应的策略，执行相应的请求，从而避免了使用大量的if else操作。
        IPromotion promotion = PromotionFactory.getStrategy(strategy);
        promotion.doPromotion();
    }
}
