package LC523_continuous_subarray_sum;

import java.util.Arrays;

public class Solution1 {

	/**
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static boolean checkSubarraySum(int[] nums, int k) {
		if (k == 0) {
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i] == 0 && nums[i + 1] == 0) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < nums.length - 1; i++) {
				int currSum = nums[i];
				for (int j = i + 1; j < nums.length; j++) {
					currSum += nums[j];
					if (currSum % k == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { 23, 2, 6, 4, 7 };
			int k = 6;
			System.out.println("Input: nums=" + Arrays.toString(nums) + ", k=" + k);
			boolean output = checkSubarraySum(nums, k);
			System.out.println("Output: " + output);
		}

		{
			int[] nums = new int[] { 15, 16, 11 };
			int k = 31;
			System.out.println("Input: nums=" + Arrays.toString(nums) + ", k=" + k);
			boolean output = checkSubarraySum(nums, k);
			System.out.println("Output: " + output);
		}
	}

}
