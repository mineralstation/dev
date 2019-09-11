package LC300_longest_increasing_subsequence;

import java.util.Arrays;

public class Solution2B {

	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static int getLongestIncreasingSubsequence(int[] nums) {
		int[] minLast = new int[nums.length + 1];
		minLast[0] = Integer.MIN_VALUE;
		for (int i = 1; i <= nums.length; i++) {
			minLast[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < nums.length; i++) {
			// find the first number in minLast >= nums[i]
			int index = binarySearch(minLast, nums[i]);
			minLast[index] = nums[i];
		}

		for (int i = nums.length; i >= 1; i--) {
			if (minLast[i] != Integer.MAX_VALUE) {
				return i;
			}
		}
		return 0;
	}

	// find the first number > num
	private static int binarySearch(int[] minLast, int num) {
		int start = 0, end = minLast.length - 1;
		while (start + 1 < end) {
			int mid = (end - start) / 2 + start;
			if (minLast[mid] < num) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return end;
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
