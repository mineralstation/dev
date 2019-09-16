package P06_17__LC018_4sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.

For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

A solution set is:
(-1,  0, 0, 1)
(-2, -1, 1, 2)
(-2,  0, 0, 2)
    
 */
public class Solution1 {

	public static List<List<Integer>> get4sums(int[] nums, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 4) {
			return results;
		}

		Arrays.sort(nums);
		System.out.println("Sorted input: " + Arrays.toString(nums));

		int n = nums.length;
		for (int i = 0; i < n - 3; i++) {
			int num1 = nums[i];
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			for (int j = i + 1; j < n - 2; j++) {
				int num2 = nums[j];
				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}

				int left = j + 1;
				int right = n - 1;
				while (left < right) {
					int sum = num1 + num2 + nums[left] + nums[right];
					if (sum == target) {
						List<Integer> result = new ArrayList<Integer>();
						result.add(num1);
						result.add(num2);
						result.add(nums[left]);
						result.add(nums[right]);
						results.add(result);

						if (left + 1 < right && nums[left + 1] == nums[left]) {
							left++;
						}
						if (left < right - 1 && nums[right - 1] == nums[right]) {
							right--;
						}

						left++;
						right--;

					} else if (sum < target) {
						// make sum larger
						left++;
					} else {
						// sum > target
						// make sum smaller
						right--;
					}
				}
			}
		}

		return results;
	}

	public static void main(String[] args) {
		// For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
		// A solution set is:
		// (-1, 0, 0, 1)
		// (-2, -1, 1, 2)
		// (-2, 0, 0, 2)
		{
			int[] nums = new int[] { 1, 0, -1, 0, -2, 2 };
			int target = 0;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			List<List<Integer>> results = get4sums(nums, target);
			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
			System.out.println();
		}

		// For example, given array S = {0 0 0 0}, and target = 0.
		// A solution set is:
		// (0, 0, 0, 0)
		{
			int[] nums = new int[] { 0, 0, 0, 0 };
			int target = 0;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			List<List<Integer>> results = get4sums(nums, target);
			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
			System.out.println();
		}
	}

}
