package com.example.leetcode.hot;

/**
 * @author tianzhoubing
 * @date 2021/7/26 21:22
 * @description
 *
 * 买卖股票的最佳时机
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 **/
public class GuPao {
    public int maxProfit(int[] prices) {
        int minPrice=prices[0];
        int maxPrice=0;
        for (int i = 0; i < prices.length; i++) {
            if (minPrice>prices[i]){
                minPrice = prices[i];
            }
            if ((prices[i]-minPrice)>maxPrice) {
                maxPrice=prices[i]-minPrice;
            }
        }
        return maxPrice;
    }
}
