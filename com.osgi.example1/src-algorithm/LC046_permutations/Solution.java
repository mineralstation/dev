package LC046_permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

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

		// 前后元素交换 dfs解题
		collect(nums, 0, nums.length - 1, results);

		return results;
	}

	/**
	 * 
	 * @param nums
	 * @param startIndex
	 * @param endIndex
	 * @param results
	 */
	public static void collect(int[] nums, int startIndex, int endIndex, List<List<Integer>> results) {
		// startIndex是最后一个数字
		if (startIndex == endIndex) {
			List<Integer> result = new ArrayList<>();
			for (int i = 0; i <= endIndex; i++) {
				result.add(nums[i]);
			}
			results.add(result);
			// System.out.println(" ---> " + Arrays.toString(result.toArray(new Integer[result.size()])));
			return;
		}

		// nums[startIndex]逐个和后面的nums[]交换
		// System.out.println(" i: [" + startIndex + ", " + endIndex + "]");
		for (int i = startIndex; i <= endIndex; i++) {
			swap(nums, i, startIndex);
			collect(nums, startIndex + 1, endIndex, results);
			swap(nums, i, startIndex);
		}
	}

	/**
	 * 
	 * @param nums
	 * @param i
	 * @param j
	 */
	public static void swap(int[] nums, int i, int j) {
		if (i == j) {
			return;
		}
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
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
