package best_time_to_buy_and_sell_stock_iii;

public class Solution2 {

	public int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int n = prices.length;

		int[] leftMaxProfit = new int[n];
		leftMaxProfit[0] = 0;

		int maxLeftProfit = 0;
		int minPrice = prices[0];
		for (int i = 1; i < n; i++) {
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else {
				int profit = prices[i] - minPrice;
				if (profit > maxLeftProfit) {
					maxLeftProfit = profit;
				}
			}
			leftMaxProfit[i] = maxLeftProfit;
		}

		int[] rightMaxProfit = new int[n];
		rightMaxProfit[n - 1] = 0;

		int maxRightProfit = 0;
		int maxPrice = prices[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			if (prices[i] > maxPrice) {
				maxPrice = prices[i];
			} else {
				int profit = maxPrice - prices[i];
				if (profit > maxRightProfit) {
					maxRightProfit = profit;
				}
			}
			rightMaxProfit[i] = maxRightProfit;
		}

		int maxProfitSum = 0;
		for (int i = 0; i < n; i++) {
			int profitSum = leftMaxProfit[i] + rightMaxProfit[i];
			if (profitSum > maxProfitSum) {
				maxProfitSum = profitSum;
			}
		}
		return maxProfitSum;
	}

}
