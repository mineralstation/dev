package LC278_first_bad_version;

public class Solution {

	public static boolean isBadVersion(int n) {
		// existing API
		return false;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static int firstBadVersion(int n) {
		int left = 1;
		int right = n;

		boolean found = false;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (isBadVersion(mid)) {
				right = mid;
				found = true;
			} else {
				left = mid + 1;
			}
		}
		if (!found) {
			return -1;
		}
		return left;
	}

}
