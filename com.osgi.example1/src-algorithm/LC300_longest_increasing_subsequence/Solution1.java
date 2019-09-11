package LC300_longest_increasing_subsequence;

import java.util.Arrays;

/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 */
public class Solution1 {

	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static int getLongestIncreasingSubsequence(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 1;
		}

		int[] memo = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			memo[i] = 1;
		}

		int max = 0;
		for (int i = 0; i < nums.length; ++i) {
			// 对于每个数字，枚举前面所有小于自己的数字 j，
			// memo[i] = max(memo[j]) + 1. 如果没有比自己小的，memo[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					if (memo[j] + 1 > memo[i]) {
						memo[i] = memo[j] + 1;
					}
				}
			}

			if (memo[i] > max) {
				max = memo[i];
			}
		}

		return max;
	}

	public static void main(String[] args) {
		// Input: [10,9,2,5,3,7,101,18]
		// Output: 4
		// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
		{
			int[] input = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestIncreasingSubsequence(input);
			System.out.println("Output: " + output);
		}

		// For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
		{
			int[] input = new int[] { 5, 4, 1, 2, 3 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestIncreasingSubsequence(input);
			System.out.println("Output: " + output);
		}

		// For [4, 2, 4, 5, 3, 7], the LIS is [4, 4, 5, 7], return 4
		{
			int[] input = new int[] { 4, 2, 4, 5, 3, 7 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestIncreasingSubsequence(input);
			System.out.println("Output: " + output);
		}
	}

}
