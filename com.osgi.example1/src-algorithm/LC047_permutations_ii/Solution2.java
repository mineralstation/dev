package LC047_permutations_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution2 {

	static List<List<Integer>> results = new ArrayList<List<Integer>>();

	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permuteUnique(int[] nums) {
		int length = nums.length;
		if (nums == null || length == 0) {
			return results;
		}

		Arrays.sort(nums);

		boolean[] inStack = new boolean[length];
		Stack<Integer> stack = new Stack<Integer>();
		collect(nums, inStack, stack, length);

		return results;
	}

	/**
	 * 
	 * @param nums
	 * @param inStack
	 * @param stack
	 * @param length
	 */
	public static void collect(int[] nums, boolean[] inStack, Stack<Integer> stack, int length) {
		if (stack.size() == length) {
			results.add(new ArrayList<Integer>(stack));
			return;
		}

		for (int i = 0; i < length; i++) {
			// 当前位置的数已经在Stack中了
			if (inStack[i]) {
				continue;
			}

			// 当前元素与其前一个元素值相同 且 前元素未被加到stack中，跳过该元素
			if (i > 0 && nums[i] == nums[i - 1] && !inStack[i - 1]) {
				continue;
			}

			// DFS(深度优先)搜索遍历
			inStack[i] = true;
			stack.push(nums[i]);

			collect(nums, inStack, stack, length);

			stack.pop();
			inStack[i] = false;
		}
	}

	public static void main(String[] args) {
		{
			int[] input = new int[] { 1, 2, 3 };
			System.out.println("input:");
			System.out.println("    " + Arrays.toString(input));

			List<List<Integer>> results = permuteUnique(input);
			System.out.println("output:");
			for (List<Integer> result : results) {
				System.out.println("    " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
		}

		{
			int[] input = new int[] { 1, 1, 2 };
			System.out.println("input:");
			System.out.println(" " + Arrays.toString(input));

			List<List<Integer>> results = permuteUnique(input);
			System.out.println("output:");
			for (List<Integer> result : results) {
				System.out.println(" " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
		}
	}

}
