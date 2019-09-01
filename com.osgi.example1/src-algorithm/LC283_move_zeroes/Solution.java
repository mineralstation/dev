package LC283_move_zeroes;

import java.util.Arrays;

public class Solution {

	public static void moveZeroes(int[] nums) {
		int zeroIndex = 0;
		boolean hasZero = false;
		while (zeroIndex < nums.length) {
			if (nums[zeroIndex] == 0) {
				hasZero = true;
				break;
			}
			zeroIndex++;
		}
		if (!hasZero) {
			return;
		}

		int index = zeroIndex + 1;
		while (index < nums.length && hasZero) {
			if (nums[index] > 0) {
				swap(nums, zeroIndex, index);

				zeroIndex++;
				hasZero = false;
				while (zeroIndex < nums.length) {
					if (nums[zeroIndex] == 0) {
						hasZero = true;
						break;
					}
					zeroIndex++;
				}
				if (hasZero) {
					index = zeroIndex + 1;
					continue;
				}
			}
			++index;
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
