package LC680_valid_palindrome_ii;

/**
 * @see LC125
 * 
 */
public class Solution {

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean validPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				if (isValid(s, left, right - 1) || isValid(s, left + 1, right)) {
					return true;
				} else {
					return false;
				}
			}
			++left;
			--right;
		}
		return true;
	}

	/**
	 * 
	 * @param s
	 * @param left
	 * @param right
	 * @return
	 */
	public static boolean isValid(String s, int left, int right) {
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			++left;
			--right;
		}
		return true;
	}

}
