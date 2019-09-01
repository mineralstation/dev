package LC283_move_zeroes;

import java.util.Arrays;

public class Solution0 {

	public static void moveZeroes(int[] nums) {
		int left = 0;
		int right = 0;
		while (right < nums.length) {
			if (nums[right] > 0) {
				swap(nums, left++, right);
			}
			++right;
		}
	}

	protected static void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 0, 1, 0, 3, 12 };
		System.out.println("Input: " + Arrays.toString(input));
		moveZeroes(input);
		System.out.println("Output: " + Arrays.toString(input));
		System.out.println();
	}

}
