package com.machd;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class Solution {
        public static int maxScore(int[] cardPoints, int k) {
            int len = cardPoints.length;
            if(len < k) return 0;
            int sum = 0;
            for(int i = 0;i < len;i++){
                sum += cardPoints[i];
            }
            int winSize = len - k;
            int temp = 0;
            for(int i = 0;i < winSize;i++){
                temp += cardPoints[i];
            }
            int total = temp;
            for(int i = 1; i < k;i++){
                total += cardPoints[i+winSize -1] - cardPoints[i];
                temp = Math.min(total, temp);
            }
            return sum - temp;
        }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6};
        int i = maxScore(arr, 3);
        System.out.println(i);
    }
}
