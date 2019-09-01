package LC088_merge_sorted_array;

public class Solution1 {

	/**
	 * 
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public int[] merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;

		int[] res = new int[k];

		while (i >= 0 && j >= 0) {
			nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
		}
		while (i >= 0) {
			nums1[k--] = nums2[i--];
		}
		while (j >= 0) {
			nums1[k--] = nums2[j--];
		}
		return res;
	}

}
