package LC349_intersection_of_two_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

	/**
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < nums1.length; i++) {
			if (binarySearch(nums2, nums1[i])) {
				result.add(nums1[i]);
			}
			while (i + 1 < nums1.length && nums1[i + 1] == nums1[i]) {
				i++;
			}
		}

		int[] rtn = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rtn[i] = result.get(i);
		}

		return rtn;
	}

	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private boolean binarySearch(int[] nums, int target) {
		if (nums.length == 0) {
			return false;
		}

		int lIndex = 0;
		int rIndex = nums.length - 1;

		while (lIndex <= rIndex) {
			int midIndex = (lIndex + rIndex) / 2;
			if (nums[midIndex] == target) {
				return true;

			} else if (nums[midIndex] < target) {
				// search right part
				lIndex = midIndex + 1;

			} else {
				// search left part
				rIndex = midIndex - 1;
			}
		}
		return false;
	}

}
