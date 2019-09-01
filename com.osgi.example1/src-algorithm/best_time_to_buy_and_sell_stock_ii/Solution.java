package best_time_to_buy_and_sell_stock_ii;

public class Solution {

	/**
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			profit += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
		}
		return profit;
	}

}
