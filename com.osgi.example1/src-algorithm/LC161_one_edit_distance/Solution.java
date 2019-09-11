package LC161_one_edit_distance;

public class Solution {

	/**
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isOneEditDistance(String s, String t) {
		if (s == null || t == null) {
			return false;
		}

		if (s.equals(t)) {
			return false; // or true depending on rules
		}

		int length1 = s.length();
		int length2 = t.length();

		if (Math.abs(length1 - length2) == 1) {
			// One character difference
			String longer = length1 > length2 ? s : t;
			String shorter = length1 > length2 ? t : s;

			for (int i = 0; i < longer.length(); i++) {
				// remove current character from longer string and compare the remaining string with shorter string.
				// if equal, return true
				String part1 = "";
				String part2 = "";
				if (i > 0) {
					part1 = s.substring(0, i);
				}
				if (i < s.length() - 1) {
					part2 = s.substring(i + 1);
				}

				String newS = part1 + part2;
				if (newS.equals(shorter)) {
					return true;
				}
			}

		} else if (length1 == length2) {
			// Same length
			// - replace current character with character of same position from shorter string.
			// - if equal, return true
			for (int i = 0; i < s.length(); i++) {
				String c1 = s.substring(i, i + 1);
				String c2 = t.substring(i, i + 1);
				if (c1.equals(c2)) {
					continue;
				}

				String part1 = "";
				String part2 = c2;
				String part3 = "";
				if (i > 0) {
					part1 = s.substring(0, i);
				}
				if (i < s.length() - 1) {
					part3 = s.substring(i + 1);
				}

				String newS = part1 + part2 + part3;
				if (newS.equals(t)) {
					return true;
				}
			}
		}

		return false;
	}

}
