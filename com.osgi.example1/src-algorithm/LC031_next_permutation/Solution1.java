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
public class Solution1 {

	/**
	 * 
	 * @param nums
	 */
	public static void nextPermutation(int[] nums) {
		int length = nums.length;
		if (length < 2) {
			return;
		}

		/*
		 * 从倒数第二个元素开始，从后向前，找到第一个满足 后元素>前元素 的情况. k结果值为前元素的索引
		 * 
		 * 此时，记录前元素下标k，则[k+1,n-1]为一个单调非增子序列
		 * 
		 * 那么，这里只需要将一个比nums[k]大的最小数与nums[k]交换
		 */
		int lastElement = nums[length - 1];
		int k = length - 2;
		for (; k >= 0; k--) {
			if (lastElement > nums[k]) {
				break;
			} else {
				lastElement = nums[k];
				continue;
			}
		}

		// 当前排列为最大排列，逆序之
		if (k < 0) {
			// for (int i = 0; i < (length + 1) / 2; i++) {
			// swap(nums, i, length - 1 - i);
			// }
			reverse(nums, 0, length - 1);

		} else {
			// 在nums[k+1,n-1]中寻找大于nums[k]的最小数
			// int index = 0;
			for (int i = length - 1; i > k; i--) {
				if (nums[i] > nums[k]) {
					swap(nums, i, k);
					// index = i;
					break;
				}
			}

			// index为0，表示当前nums[k]小于其后任意一个数，直接交换k与len-1
			// if (index == 0) {
			// swap(nums, k, length - 1);
			// }

			// 将nums[k+1,n-1]逆序
			// for (int i = k + 1; i < (k + length + 2) / 2; i++) {
			// swap(nums, i, k + length - i);
			// }
			reverse(nums, k + 1, length - 1);
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
		// int[] nums1 = new int[] { 1, 2, 3 };
		// String bf1 = Arrays.toString(nums1);
		// nextPermutation(nums1);
		// System.out.println(bf1 + " -> " + Arrays.toString(nums1));
		//
		// int[] nums2 = new int[] { 3, 2, 1 };
		// String bf2 = Arrays.toString(nums2);
		// nextPermutation(nums2);
		// System.out.println(bf2 + " -> " + Arrays.toString(nums2));
		//
		// int[] nums3 = new int[] { 1, 1, 5 };
		// String bf3 = Arrays.toString(nums3);
		// nextPermutation(nums3);
		// System.out.println(bf3 + " -> " + Arrays.toString(nums3));

		// {
		// int[] nums = new int[] { 9, 7, 5, 2, 1 };
		// String bf = Arrays.toString(nums);
		// nextPermutation(nums);
		// System.out.println(bf + " -> " + Arrays.toString(nums));
		// }

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

		// {
		// int[] nums = new int[] { 1, 2, 7, 9, 5 };
		// String bf = Arrays.toString(nums);
		// nextPermutation(nums);
		// System.out.println(bf + " -> " + Arrays.toString(nums));
		// }
	}

}

// ————————————————
// 版权声明：本文为CSDN博主「chenjieping1995」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
// 原文链接：https://blog.csdn.net/Jacky_chenjp/article/details/66477538