package LC301_remove_invalid_parentheses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution1 {

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<String>();
		List<String> visited = new ArrayList<String>();
		visited.add(s);

		Queue<String> queue = new LinkedList<String>();

		while (!queue.isEmpty()) {
			String t = queue.poll();
			if (isValid(t)) {
				res.add(t);

			} else {
				for (int i = 0; i < t.length(); ++i) {
					if (t.charAt(i) == '(' || t.charAt(i) == ')') {
						String str = t.substring(0, i) + t.substring(i + 1);
						if (!visited.contains(str)) {
							queue.add(str);
							visited.add(str);
						}
					}
				}
			}
		}
		return res;
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
