package LC047_permutations_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();

		int length = nums.length;
		if (nums == null || length == 0) {
			return results;
		}

		Arrays.sort(nums);

		boolean[] used = new boolean[length];
		List<Integer> list = new ArrayList<Integer>();
		collect(nums, used, list, length, results);
		return results;
	}

	/**
	 * 
	 * @param nums
	 * @param used
	 * @param list
	 * @param length
	 * @param results
	 */
	public static void collect(int[] nums, boolean[] used, List<Integer> list, int length, List<List<Integer>> results) {
		if (list.size() == length) {
			results.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < length; i++) {
			// 当前位置的数已经在List中了
			if (used[i]) {
				continue;
			}

			// 当前元素与其前一个元素值相同 且 前元素未被加到list中，跳过该元素
			if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
				continue;
			}

			// 深度优先搜索遍历
			used[i] = true;
			list.add(nums[i]);

			collect(nums, used, list, length, results);

			list.remove(list.size() - 1);
			used[i] = false;
		}
	}

	public static void main(String[] args) {
		// {
		// int[] input = new int[] { 1, 2, 3 };
		// System.out.println("input:");
		// System.out.println(" " + Arrays.toString(input));
		//
		// List<List<Integer>> results = permuteUnique(input);
		// System.out.println("output:");
		// for (List<Integer> result : results) {
		// System.out.println(" " + Arrays.toString(result.toArray(new Integer[result.size()])));
		// }
		// }

		{
			int[] input = new int[] { 1, 1, 2 };
			System.out.println("input:");
			System.out.println("    " + Arrays.toString(input));

			List<List<Integer>> results = permuteUnique(input);
			System.out.println("output:");
			for (List<Integer> result : results) {
				System.out.println("    " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
		}
	}

}
