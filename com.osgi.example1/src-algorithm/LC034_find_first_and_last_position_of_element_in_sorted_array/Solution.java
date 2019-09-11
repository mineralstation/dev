package LC034_find_first_and_last_position_of_element_in_sorted_array;

import java.util.Arrays;

/*

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

 */
public class Solution {

	public static int[] searchRange(int[] nums, int target) {
		int[] result = new int[] { -1, -1 };

		// find first value equal to target
		int leftIndex = 0;
		int rightIndex = nums.length;

		while (leftIndex < rightIndex) {
			int mid = leftIndex + (rightIndex - leftIndex) / 2;
			if (nums[mid] < target) {
				leftIndex = mid + 1;
			} else {
				rightIndex = mid;
			}
		}
		if (leftIndex < nums.length && nums[leftIndex] == target) {
			result[0] = rightIndex;
		} else {
			// target val is not found
			return result;
		}

		// find first value less than or equal to target+1
		int newTarget = target + 1;

		leftIndex = rightIndex;
		rightIndex = nums.length;
		while (leftIndex < rightIndex) {
			int mid = leftIndex + (rightIndex - leftIndex) / 2;
			if (nums[mid] < newTarget) {
				leftIndex = mid + 1;
			} else {
				rightIndex = mid;
			}
		}

		if (rightIndex - 1 >= 0) {
			result[1] = rightIndex - 1;
		}

		return result;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { 5, 7, 7, 8, 8, 10 };
			int target = 8;
			System.out.println("Input: " + Arrays.toString(nums));
			System.out.println("Target: " + target);
			int[] result = searchRange(nums, target);
			System.out.println("result: " + Arrays.toString(result));
		}

		{
			int[] nums = new int[] { 5, 7, 7, 8, 8, 10 };
			int target = 6;
			System.out.println("Input: " + Arrays.toString(nums));
			System.out.println("Target: " + target);
			int[] result = searchRange(nums, target);
			System.out.println("result: " + Arrays.toString(result));
		}
	}

}
