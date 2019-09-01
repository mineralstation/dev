package LC301_remove_invalid_parentheses;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<String>();
		int cnt1 = 0, cnt2 = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				cnt1++;
			} else if (c == ')') {
				if (cnt1 == 0) {
					cnt2++;
				} else {
					cnt1--;
				}
			}
		}
		helper(s, 0, cnt1, cnt2, res);
		return res;
	}

	/**
	 * 
	 * @param s
	 * @param start
	 * @param leftExtraNum
	 * @param rightExtraNum
	 * @param res
	 */
	protected static void helper(String s, int start, int leftExtraNum, int rightExtraNum, List<String> res) {
		if (leftExtraNum == 0 && rightExtraNum == 0) {
			if (isValid(s)) {
				res.add(s);
			}

		} else {
			for (int i = start; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (i > start && c == s.charAt(i - 1)) {
					continue;
				}
				if (c == '(') {
					if (leftExtraNum > 0) {
						helper(s.substring(0, i) + s.substring(i + 1), i, leftExtraNum - 1, rightExtraNum, res);
					}
				} else if (c == ')') {
					if (rightExtraNum > 0) {
						helper(s.substring(0, i) + s.substring(i + 1), i, leftExtraNum, rightExtraNum - 1, res);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	protected static boolean isValid(String str) {
		int count = 0;
		for (char c : str.toCharArray()) {
			if (c == '(') {
				count++;
			} else if (c == ')') {
				count--;
				if (count < 0) {
					// more ')'
					return false;
				}
			}
		}
		// if not 0, then more '('
		return (count == 0) ? true : false;
	}

}
