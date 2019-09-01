package LC560_subarray_sum_equals_k;

public class Solution1 {

	/**
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int subarraySum(int[] nums, int k) {
		int number = 0;
		int size = nums.length;

		for (int i = 0; i < size; i++) {
			int sum = nums[i];
			if (sum == k) {
				++number;
			}
			for (int j = i + 1; j < size; j++) {
				sum += nums[j];
				if (sum == k) {
					++number;
				}
			}
		}
		return number;
	}

}
