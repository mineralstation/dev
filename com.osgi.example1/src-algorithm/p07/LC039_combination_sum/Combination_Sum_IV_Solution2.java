package p07.LC039_combination_sum;

import java.util.Arrays;

/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.
Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

 */
public class Combination_Sum_IV_Solution2 {

	public static int getCombinationSum(int[] nums, int target) {
		Arrays.sort(nums);

		int[] memo = new int[target + 1];
		memo[0] = 1;

		for (int currTarget = 1; currTarget <= target; ++currTarget) {
			for (int num : nums) {
				if (currTarget >= num) {
					memo[currTarget] += memo[currTarget - num];
				}
			}
		}
		return memo[memo.length - 1];
	}

	public static void main(String[] args) {
		// Example:
		// nums = [1, 2, 3]
		// target = 4
		//
		// The possible combination ways are:
		// (1, 1, 1, 1)
		// (1, 1, 2)
		// (1, 2, 1)
		// (1, 3)
		// (2, 1, 1)
		// (2, 2)
		// (3, 1)
		{
			int[] nums = new int[] { 1, 2, 3 };
			int target = 4;
			System.out.println("Input:");
			System.out.println("nums = " + Arrays.toString(nums));
			System.out.println("target = " + target);

			int result = getCombinationSum(nums, target);
			System.out.println("Output:");
			System.out.println("result = " + result);
		}
	}

}
