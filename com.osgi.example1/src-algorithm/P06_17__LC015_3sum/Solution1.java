package P06_17__LC015_3sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
 

For example, given array S = {-1 0 1 2 -1 -4},

A solution set is:
(-1, 0, 1)
(-1, -1, 2)

 */
public class Solution1 {

	public static List<List<Integer>> get3Sums(int[] nums, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 3) {
			return results;
		}

		Arrays.sort(nums);
		// System.out.println("Sorted input: " + Arrays.toString(nums));

		int n = nums.length;
		for (int i = 0; i < n - 2; ++i) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			int num = nums[i];
			int remaining = target - num;

			int left = i + 1;
			int right = n - 1;

			while (left < right) {
				int twoSum = nums[left] + nums[right];
				if (twoSum > remaining) {
					right--;

				} else if (twoSum < remaining) {
					left++;

				} else {
					List<Integer> result = new ArrayList<Integer>();
					result.add(num);
					result.add(nums[left]);
					result.add(nums[right]);

					if (!results.contains(result)) {
						results.add(result);
					}

					left++;
					right--;

					while (left < right && nums[left - 1] == nums[left]) {
						left++;
					}
					while (left < right && nums[right + 1] == nums[right]) {
						right--;
					}
				}
			}
		}

		return results;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
			int target = 0;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			List<List<Integer>> results = get3Sums(nums, target);
			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
		}
	}

}
