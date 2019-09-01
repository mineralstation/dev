package LC010_regular_expression_matching;

public class Solution {

	/**
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch(String s, String p) {
		if (p.isEmpty()) {
			return s.isEmpty();
		}

		if (p.length() > 1 && p.charAt(1) == '*') {
			// pattern is char*, where char could repeat 0 or more times
			boolean matchZeroCharCase = isMatch(s, p.substring(2));
			boolean matchOneOrMoreCharCase = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p);
			return matchZeroCharCase || matchOneOrMoreCharCase;
		} else {
			// match first character
			return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
		}
	}

}
