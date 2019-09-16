package P06_17__LC015_3sum_smaller;

import java.util.Arrays;

public class Solution1 {

	public static int get3SumsSmaller(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int count = 0;
		int n = nums.length;
		for (int i = 0; i < n - 2; i++) {
			int num = nums[i];

			int left = i + 1;
			int right = n - 1;
			while (left < right) {
				if (num + nums[left] + nums[right] < target) {
					count += (right - left); // There are (right - left) paris of nums, where nums[left] plus nums[left+1, right] all smaller than target.

					// for (int r = left + 1; r <= right; r++) {
					// System.out.println(num + ", " + nums[left] + "," + nums[r]);
					// }

					left++;
				} else {
					right--;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { -2, -1, 0, 1 };
			int target = 0;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			int count = get3SumsSmaller(nums, target);
			System.out.println("Output: count = " + count);
			System.out.println();
		}

		{
			int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
			int target = 0;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			int count = get3SumsSmaller(nums, target);
			System.out.println("Output: count = " + count);
			System.out.println();
		}
	}

}
