package LC349_intersection_of_two_arrays;

import java.util.HashSet;
import java.util.Set;

public class Solution1 {

	/**
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> hash = new HashSet<Integer>();
		Set<Integer> inter = new HashSet<Integer>();

		for (int num : nums1) {
			hash.add(num);
		}

		for (int num : nums2) {
			if (hash.contains(num)) {
				inter.add(num);
			}
		}

		int[] result = new int[inter.size()];
		int i = 0;
		for (int num : inter) {
			result[i++] = num;
		}
		return result;
	}

}
