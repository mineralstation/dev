package p07.LC026_remove_duplicates_from_sorted_array;

import java.util.Arrays;

/*

Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the returned length.

Example 2:
Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
It doesn't matter what values are set beyond the returned length.

Clarification:
Confused why the returned value is an integer but your answer is an array?
Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}

 */
public class Solution {

	public static int removeDuplicates(int[] nums) {
		int result = 1;

		int n = nums.length;
		int prevNum = nums[0];

		for (int i = 1; i < n; i++) {
			int currNum = nums[i];

			if (currNum == prevNum) {
				// same num

			} else {
				// a new num starts
				nums[result] = currNum;
				result++;
			}

			prevNum = currNum;
		}

		return result;
	}

	public static void main(String[] args) {
		{
			// Example 1:
			// Given nums = [1,1,2],
			// Your function should return length = 2
			int[] nums = new int[] { 1, 1, 2 };
			System.out.println("Input:");
			System.out.println(Arrays.toString(nums));
			System.out.println();

			int result = removeDuplicates(nums);
			int[] nums2 = new int[result];
			System.arraycopy(nums, 0, nums2, 0, result);
			System.out.println("Input:");
			System.out.println(result);
			System.out.println(Arrays.toString(nums2));
			System.out.println();
		}

		{
			// Example 2:
			// Given nums = [0,0,1,1,1,2,2,3,3,4],
			// Your function should return length = 5
			int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
			System.out.println("Input:");
			System.out.println(Arrays.toString(nums));
			System.out.println();

			int result = removeDuplicates(nums);
			int[] nums2 = new int[result];
			System.arraycopy(nums, 0, nums2, 0, result);
			System.out.println("Input:");
			System.out.println(result);
			System.out.println(Arrays.toString(nums2));
			System.out.println();
		}
	}

}
