package p07.LC039_combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

 */
public class Combination_Sum_Solution {

	public static List<List<Integer>> getCombinationSum(int[] nums, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Stack<Integer> stack = new Stack<Integer>();

		getCombinationSum(nums, target, 0, results, stack);

		return results;
	}

	public static void getCombinationSum(int[] nums, int target, int start, List<List<Integer>> results, Stack<Integer> stack) {
		if (target < 0) {
			return;

		} else if (target == 0) {
			List<Integer> result = new ArrayList<Integer>(stack);
			results.add(result);
			return;
		}

		int n = nums.length;
		for (int i = start; i < n; i++) {
			int num = nums[i];
			stack.push(num);

			getCombinationSum(nums, target - num, i, results, stack);

			stack.pop();
		}
	}

	public static void main(String[] args) {
		// For example, given candidate set [1, 2] and target 1,
		// A solution set is:
		// [1]
		{
			int[] nums = new int[] { 1, 2 };
			int target = 1;
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

		// Example 1:
		// Input: candidates = [2,3,6,7], target = 7,
		// A solution set is:
		// [
		// [7],
		// [2,2,3]
		// ]
		{
			int[] nums = new int[] { 2, 3, 6, 7 };
			int target = 7;
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

		// Example 2:
		// Input: candidates = [2,3,5], target = 8,
		// A solution set is:
		// [
		// [2,2,2,2],
		// [2,3,3],
		// [3,5]
		// ]
		{
			int[] nums = new int[] { 2, 3, 5 };
			int target = 8;
			System.out.println("Input:");
			System.out.println("nums = " + Arrays.toString(nums));
			System.out.println("target = " + target);
			List<List<Integer>> results = getCombinationSum(nums, target);
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
