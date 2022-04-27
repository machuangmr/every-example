package com.machd.strategypattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 策略的工厂类，通常策略会和工厂模式搭配使用
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/26
 */
public class PromotionFactory {
  private static final Map<String, IPromotion> strategyMap = new HashMap<>();
  static {
      strategyMap.put("EMPTY", new EmptyStrategy());
      strategyMap.put("CASHBACK", new CashBackStrategy());
      strategyMap.put("GROUPBUY", new GroupBuyStrategy());
  }

  public static IPromotion getStrategy(String strateKey) {
      return strategyMap.getOrDefault(strateKey, new EmptyStrategy());
  }

  public static Set<String> getAllStrategy() {
      return strategyMap.keySet();
  }
}
