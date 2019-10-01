package p07.LC039_combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.
Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

 */
public class Combination_Sum_IV_Solution {

	public static List<List<Integer>> getCombinationSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Stack<Integer> stack = new Stack<Integer>();

		getCombinationSum(nums, target, 0, results, stack);

		return results;
	}

	protected static void getCombinationSum(int[] nums, int target, int start, List<List<Integer>> results, Stack<Integer> stack) {
		if (target < 0) {
			return;
		}
		if (target == 0) {
			List<Integer> result = new ArrayList<Integer>(stack);
			results.add(result);
			return;
		}

		for (int i = start; i < nums.length; i++) {
			int num = nums[i];
			stack.push(num);

			int remainingTarget = target - num;
			getCombinationSum(nums, remainingTarget, start, results, stack);

			stack.pop();
		}
	}

	public static void main(String[] args) {
		// Example:
		// nums = [1, 2, 3]
		// target = 4
		//
		// The possible combination ways are:
		// (1, 1, 1, 1)
		// (1, 1, 2)
		// (1, 2, 1)
		// (1, 3)
		// (2, 1, 1)
		// (2, 2)
		// (3, 1)
		{
			int[] nums = new int[] { 1, 2, 3 };
			int target = 4;
			System.out.println("Input:");
			System.out.println("nums = " + Arrays.toString(nums));
			System.out.println("target = " + target);
			List<List<Integer>> results = getCombinationSum(nums, target);
			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println(" " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
			System.out.println();
		}

		// {
		// int[] nums = new int[] { -1, 1, 2, 3, 5 };
		// int target = 4;
		// System.out.println("Input:");
		// System.out.println("nums = " + Arrays.toString(nums));
		// System.out.println("target = " + target);
		// List<List<Integer>> results = getCombinationSum(nums, target);
		// System.out.println("Output:");
		// System.out.println("[");
		// for (List<Integer> result : results) {
		// System.out.println(" " + Arrays.toString(result.toArray(new Integer[result.size()])));
		// }
		// System.out.println("]");
		// System.out.println();
		// }
	}

}
