package p07.LC053_maximum_subarray;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6

Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 */
public class Solution1 {

	public static int maxSubArray(int[] nums) {
		int result = Integer.MIN_VALUE;

		int sum = 0;
		for (int num : nums) {
			sum = (sum + num > num) ? sum + num : num;

			if (sum > result) {
				result = sum;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int result = maxSubArray(nums);
		System.out.println(result);
	}

}
