package LC300_longest_increasing_subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

	public static int getLongestIncreasingSubsequence(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 1;
		}

		// list with increasing order
		List<Integer> list = new ArrayList<Integer>();
		list.add(nums[0]);

		for (int num : nums) {
			if (num < list.get(0)) {
				// num is smaller than first element
				// - replace first element with num
				list.set(0, num);

			} else if (num > list.get(list.size() - 1)) {
				// num is larger than last element
				// - append num to the list
				list.add(num);

			} else {
				int left = 0;
				int right = list.size();

				// num is in the list values range
				// - binary search to find first value in list which is larger than the current num and replace that value with the num.
				while (left < right) {
					int mid = left + (right - left) / 2;
					if (list.get(mid) < num) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}

				list.set(left, num);
			}
		}

		return list.size();
	}

	public static void main(String[] args) {
		// Input: [10,9,2,5,3,7,101,18]
		// Output: 4
		// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
		{
			int[] input = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestIncreasingSubsequence(input);
			System.out.println("Output: " + output);
		}

		// For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
		{
			int[] input = new int[] { 5, 4, 1, 2, 3 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestIncreasingSubsequence(input);
			System.out.println("Output: " + output);
		}

		// For [4, 2, 4, 5, 3, 7], the LIS is [4, 4, 5, 7], return 4
		{
			int[] input = new int[] { 4, 2, 4, 5, 3, 7 };
			System.out.println("Input: " + Arrays.toString(input));
			int output = getLongestIncreasingSubsequence(input);
			System.out.println("Output: " + output);
		}
	}

}
