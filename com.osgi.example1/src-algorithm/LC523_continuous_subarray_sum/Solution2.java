package LC523_continuous_subarray_sum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 这个算法不对
 * 
 */
public class Solution2 {

	public static boolean checkSubarraySum(int[] nums, int k) {
		Set<Integer> rSet = new HashSet<Integer>();

		int sum = 0;
		for (int i = 0; i < nums.length; ++i) {
			sum += nums[i];
			int r = sum % k;

			if (rSet.contains(r)) {
				return true;
			}

			rSet.add(r);
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
