package LC560_subarray_sum_equals_k;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

	/**
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int subarraySum(int[] nums, int k) {
		int result = 0;
		int sum = 0;
		int size = nums.length;

		Map<Integer, Integer> sumCountMap = new HashMap<Integer, Integer>();
		sumCountMap.put(0, 1);

		for (int i = 0; i < size; ++i) {
			sum += nums[i];

			int targetNum = sum - k;
			if (sumCountMap.containsKey(targetNum)) {
				result += sumCountMap.get(targetNum);
			}

			int count = 0;
			if (sumCountMap.containsKey(sum)) {
				count = sumCountMap.get(sum);
			}
			sumCountMap.put(sum, count + 1);
		}
		return result;
	}

}
