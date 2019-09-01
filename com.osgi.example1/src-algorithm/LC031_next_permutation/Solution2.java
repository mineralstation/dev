package LC031_next_permutation;

import java.util.Arrays;

/**
 * 原题链接：31. Next Permutation (https://leetcode.com/problems/next-permutation/#/description)
 * 
 * 给定任一非空正整数序列，生成这些数所能排列出的下一个较大序列。若给出的序列为最大序列，则生成最小序列。
 * 
 * 输入 → 输出
 * 
 * 1,2,3 → 1,3,2
 * 
 * 3,2,1 → 1,2,3
 * 
 * 1,1,5 → 1,5,1
 */
public class Solution2 {

	/**
	 * 
	 * @param nums
	 */
	public static void nextPermutation(int[] nums) {
		int length = nums.length;
		if (length <= 1) {
			return;
		}

		int endIndex = length - 1;

		// 从倒数第二个元素开始，从后向前，找到第一个满足 后元素 > 前元素 的情况。
		// 此时，记录前元素下标k，则[k+1,n-1]一定是一个非增子序列。
		// 那么，这里只需要将一个比nums[k]大的最小数与nums[k]交换
		int nextElement = nums[endIndex];
		int k = length - 2;
		for (; k >= 0; k--) {
			int currElement = nums[k];
			if (currElement < nextElement) {
				break;
			}
			nextElement = currElement;
		}

		if (k < 0) {
			// 整个排列为降序，逆序之
			reverse(nums, 0, endIndex);

		} else {
			// 在nums[k+1,endIndex]中寻找大于nums[k]的最小数
			// Note: nums[k+1,endIndex]现在一定是降序， 所以从右边起第一个遇到的大于nums[k]的数，一定是所有大于nums[k]中的最小的那个
			for (int i = endIndex; i > k; i--) {
				if (nums[i] > nums[k]) {
					swap(nums, i, k);
					break;
				}
			}

			// nums[k+1,endIndex]现在一定是降序，逆序之
			reverse(nums, k + 1, endIndex);
		}
		return;
	}

	public static void reverse(int[] nums, int i, int j) {
		while (i < j) {
			swap(nums, i++, j--);
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { 1, 2, 3, 7, 5 };
			String bf = Arrays.toString(nums);
			System.out.print(bf + " -> ");
			nextPermutation(nums);
			System.out.println(Arrays.toString(nums));
		}
		{
			int[] nums = new int[] { 5, 6, 7, 3, 2 };
			String bf = Arrays.toString(nums);
			System.out.print(bf + " -> ");
			nextPermutation(nums);
			System.out.println(Arrays.toString(nums));
		}
		{
			int[] nums = new int[] { 5, 1, 7, 3, 2 };
			String bf = Arrays.toString(nums);
			System.out.print(bf + " -> ");
			nextPermutation(nums);
			System.out.println(Arrays.toString(nums));
		}
	}

}
