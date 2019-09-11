package LC398_random_pick_index;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution {

	protected int[] nums;

	public Solution(int[] nums) {
		this.nums = nums;
	}

	public int pick(int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		for (int i = 0; i < this.nums.length; i++) {
			int value = this.nums[i];
			if (value == target) {
				map.put(count++, i);
			}
		}

		int index = new Random().nextInt(count);
		int result = map.get(index);

		return result;
	}

	public static void main(String[] args) {
		// Example:
		//
		// int[] nums = new int[] {1,2,3,3,3};
		// Solution solution = new Solution(nums);
		//
		// // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
		// solution.pick(3);
		//
		// // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
		// solution.pick(1);

		{
			int[] nums = new int[] { 1, 2, 3, 3, 3 };
			System.out.println("Input: " + Arrays.toString(nums));
			Solution solution = new Solution(nums);
			System.out.println("Output: ");
			for (int i = 0; i < 20; i++) {
				int index = solution.pick(3);
				System.out.println(index);
			}
		}

		{
			int[] nums = new int[] { 1, 2, 3, 3, 3 };
			System.out.println("Input: " + Arrays.toString(nums));
			Solution solution = new Solution(nums);
			System.out.println("Output: ");
			for (int i = 0; i < 20; i++) {
				int index = solution.pick(1);
				System.out.println(index);
			}
		}
	}

}
