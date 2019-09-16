package P06_17__LC001_two_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 */
public class Solution2 {

	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> twoSum(int[] nums, int target) {
		int n = nums.length;

		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Map<Integer, Integer> numToIndexMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < n; i++) {
			int num = nums[i];
			int num0 = target - num;

			if (numToIndexMap.containsKey(num0)) {
				int j = numToIndexMap.get(num0);
				if (j != i) {
					List<Integer> result = new ArrayList<Integer>();
					result.add(j);
					result.add(i);
					results.add(result);
				}
			}

			numToIndexMap.put(num, i);
		}

		return results;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { 2, 7, 11, 15 };
			int target = 9;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			List<List<Integer>> results = twoSum(nums, target);
			System.out.println("Output: [");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
		}

		{
			int[] nums = new int[] { 2, 7, 11, 15, 4, 5, 3, 1 };
			int target = 9;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			List<List<Integer>> results = twoSum(nums, target);
			System.out.println("Output: [");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
		}
	}

}
