package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumOfSumSolution1 {

	public static int numOfSum(int[] nums, int target) {
		int n = nums.length;
		Map<String, Integer> memo = new HashMap<String, Integer>();
		int result = numOfSum(nums, n - 1, target, memo);
		return result;
	}

	public static int numOfSum(int[] nums, int i, int target, Map<String, Integer> memo) {
		String key = i + "::" + target;
		Integer value = memo.get(key);
		if (value != null) {
			return value;
		}

		int result = 0;

		if (target == 0) {
			return 1;

		} else if (target < 0) {
			return 0;

		} else {
			// target > 0
			if (i < 0) {
				return 0;
			}

			if (nums[i] > target) {
				result = numOfSum(nums, i - 1, target, memo); // nums[i] cannot be included
			} else {
				int r1 = numOfSum(nums, i - 1, target, memo); // without nums[i] in the sum
				int r2 = numOfSum(nums, i - 1, target - nums[i], memo); // with nums[i] as part of the sum
				result = r1 + r2;
			}
		}

		memo.put(key, result);
		return result;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { 2, 4, 6, 10 };
			int target = 16;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			int output = numOfSum(nums, target);
			System.out.println("Output: " + output);
		}

		{
			int[] nums = new int[] { 12, 2, 4, 6, 10 };
			int target = 16;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			int output = numOfSum(nums, target);
			System.out.println("Output: " + output);
		}
	}

}
