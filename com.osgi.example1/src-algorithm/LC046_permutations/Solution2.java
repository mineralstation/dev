package LC046_permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution2 {

	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return results;
		}

		Stack<Integer> stack = new Stack<Integer>();
		boolean[] inStack = new boolean[nums.length];
		collect(nums, stack, inStack, results);

		return results;
	}

	/**
	 * 
	 * @param nums
	 * @param stack
	 * @param inStack
	 * @param results
	 */
	private static void collect(int[] nums, Stack<Integer> stack, boolean[] inStack, List<List<Integer>> results) {
		if (stack.size() == nums.length) {
			List<Integer> result = new ArrayList<Integer>(stack);
			results.add(result);
		}

		for (int i = 0; i < nums.length; i++) {
			if (inStack[i]) {
				continue;
			}

			int num = nums[i];
			stack.push(num);
			inStack[i] = true;

			collect(nums, stack, inStack, results);

			stack.pop();
			inStack[i] = false;
		}
	}

	public static void main(String[] args) {
		int[] input = new int[] { 1, 2, 3 };
		System.out.println("input:");
		System.out.println("    " + Arrays.toString(input));

		List<List<Integer>> results = permute(input);
		System.out.println("output:");
		for (List<Integer> result : results) {
			System.out.println("    " + Arrays.toString(result.toArray(new Integer[result.size()])));
		}
	}

}
