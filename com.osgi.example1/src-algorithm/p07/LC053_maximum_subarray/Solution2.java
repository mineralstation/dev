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
public class Solution2 {

	public static int maxSubArray(int[] nums) {
		int result = maxSubArray(nums, 0, nums.length - 1);
		return result;
	}

	protected static int maxSubArray(int[] nums, int left, int right) {
		if (left >= right) {
			return nums[left];
		}
		int mid = left + (right - left) / 2;

		int maxLeftOnly = maxSubArray(nums, left, mid - 1);
		int maxRightOnly = maxSubArray(nums, mid + 1, right);

		int leftMax = 0;
		if (mid > left) {
			leftMax = Integer.MIN_VALUE;
			int sum = 0;
			for (int i = mid - 1; i >= left; i--) {
				sum += nums[i];
				if (sum > leftMax) {
					leftMax = sum;
				}
			}
		}

		int rightMax = 0;
		if (mid < right) {
			rightMax = Integer.MIN_VALUE;
			int sum = 0;
			for (int i = mid + 1; i <= right; i++) {
				sum += nums[i];
				if (sum > rightMax) {
					rightMax = sum;
				}
			}
		}

		int maxWithMid = leftMax + nums[mid] + rightMax;

		int result = maxWithMid;
		if (maxLeftOnly > result) {
			result = maxLeftOnly;
		}
		if (maxRightOnly > result) {
			result = maxRightOnly;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int result = maxSubArray(nums);
		System.out.println(result);
	}

}
