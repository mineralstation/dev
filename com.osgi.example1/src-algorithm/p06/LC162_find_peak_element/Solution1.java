package p06.LC162_find_peak_element;

import java.util.Arrays;

/*
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.

 */
public class Solution1 {

	public static int findPickElement(int[] nums) {
		int result = -1;
		if (nums == null || nums.length < 2) {
			return result;
		}

		int n = nums.length;

		if (nums[0] > nums[1]) {
			return nums[0];
		}
		if (nums[n - 1] > nums[n - 2]) {
			return nums[0];
		}

		for (int i = 0; i < n - 2; i++) {
			if (nums[i] < nums[i + 1] && nums[i + 1] > nums[i + 2]) {
				result = i + 1;
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		{
			int[] nums1 = new int[] { 1, 2, 3, 1 };
			System.out.println("Inupt: ");
			System.out.println(Arrays.toString(nums1));
			int result1 = findPickElement(nums1);
			System.out.println("Output: ");
			System.out.println(result1);
		}

		{
			int[] nums2 = new int[] { 1, 2, 1, 3, 5, 6, 4 };
			System.out.println("Inupt: ");
			System.out.println(Arrays.toString(nums2));
			int result2 = findPickElement(nums2);
			System.out.println("Output: ");
			System.out.println(result2);
		}
	}

}
