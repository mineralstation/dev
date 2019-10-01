package p07.LC674_longest_continuous_increasing_subsequence;

import java.util.Arrays;

/*
Given an unsorted array of integers, find the length of longest continuous increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
 
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
 
Note: Length of the array will not exceed 10,000.

 */
public class Solution {

	public static int getLongestContinuousIncreasingSubsequence(int[] nums) {
		if (nums == null) {
			return 0;
		} else if (nums.length == 1) {
			return 1;
		}
		int maxLen = 1;

		int n = nums.length;

		int startIndex = 0;
		for (int i = 1; i < n; i++) {
			if (nums[i] > nums[i - 1]) {
				int len = i - startIndex + 1;
				if (len > maxLen) {
					maxLen = len;
				}
			} else {
				startIndex = i;
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		{
			int[] input = new int[] { 1, 3, 5, 4, 7 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestContinuousIncreasingSubsequence(input);
			System.out.println("Output: " + output);
			System.out.println();
		}
		{
			int[] input = new int[] { 2, 2, 2, 2, 2 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestContinuousIncreasingSubsequence(input);
			System.out.println("Output: " + output);
			System.out.println();
		}
	}

}
