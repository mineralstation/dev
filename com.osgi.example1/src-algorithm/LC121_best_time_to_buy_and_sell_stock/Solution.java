package LC121_best_time_to_buy_and_sell_stock;

import java.util.Arrays;

/*
Example 1:
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
             
Example 2:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

 */
public class Solution {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int maxGain = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];

			} else if (prices[i] > min) {
				int gain = prices[i] - min;
				if (gain > maxGain) {
					maxGain = gain;
				}
			}
		}
		return maxGain;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 7, 1, 5, 3, 6, 4 };
		System.out.println("Input: " + Arrays.toString(input));
		int output = maxProfit(input);
		System.out.println("Output: " + output);
	}

}
