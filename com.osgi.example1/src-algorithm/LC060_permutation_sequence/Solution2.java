package LC060_permutation_sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import LC047_permutations_ii.SolutionV2;

public class Solution2 {
	static List<List<Integer>> results = new ArrayList<List<Integer>>();

	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static List<Integer> permuteUnique(int[] nums, int k) {
		int length = nums.length;
		if (nums == null || length == 0) {
			return new ArrayList<Integer>();
		}

		Arrays.sort(nums);

		boolean[] inStack = new boolean[length];
		Stack<Integer> stack = new Stack<Integer>();
		collect(nums, inStack, stack, length, k);

		if (k <= results.size()) {
			return results.get(k - 1);
		}
		return null;
	}

	/**
	 * 
	 * @param nums
	 * @param inStack
	 * @param stack
	 * @param length
	 */
	public static void collect(int[] nums, boolean[] inStack, Stack<Integer> stack, int length, int k) {
		if (results.size() == k) {
			return;
		}

		if (stack.size() == length) {
			results.add(new ArrayList<Integer>(stack));
			return;
		}

		for (int i = 0; i < length; i++) {
			// 当前位置的数已经在Stack中了
			if (inStack[i]) {
				continue;
			}

			// DFS(深度优先)搜索遍历
			inStack[i] = true;
			stack.push(nums[i]);

			collect(nums, inStack, stack, length, k);

			if (results.size() == k) {
				break;
			}

			stack.pop();
			inStack[i] = false;
		}
	}

	public static void main(String[] args) {
		{
			int[] input = new int[] { 1, 2, 3 };

			System.out.println("input:");
			System.out.println("    " + Arrays.toString(input));

			List<List<Integer>> results = SolutionV2.permuteUnique(input);
			System.out.println("output:");
			for (List<Integer> result : results) {
				System.out.println("    " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}

			System.out.println();
			System.out.println();

			int k = 3;
			System.out.println("input:");
			System.out.println("    " + Arrays.toString(input));
			System.out.println("    k = " + k);

			List<Integer> result = permuteUnique(input, k);
			System.out.println("output:");
			System.out.println("    " + Arrays.toString(result.toArray(new Integer[result.size()])));
		}
	}

}
