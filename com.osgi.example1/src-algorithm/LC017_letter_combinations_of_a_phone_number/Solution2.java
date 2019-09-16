package LC017_letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 */
public class Solution2 {

	public static String[][] DIGIT_TO_LETTERS = new String[10][4];
	static {
		DIGIT_TO_LETTERS[0] = new String[] {};
		DIGIT_TO_LETTERS[1] = new String[] {};
		DIGIT_TO_LETTERS[2] = new String[] { "a", "b", "c" };
		DIGIT_TO_LETTERS[3] = new String[] { "d", "e", "f" };
		DIGIT_TO_LETTERS[4] = new String[] { "g", "h", "i" };
		DIGIT_TO_LETTERS[5] = new String[] { "j", "k", "l" };
		DIGIT_TO_LETTERS[6] = new String[] { "m", "n", "o" };
		DIGIT_TO_LETTERS[7] = new String[] { "p", "q", "r", "s" };
		DIGIT_TO_LETTERS[8] = new String[] { "t", "u", "v" };
		DIGIT_TO_LETTERS[9] = new String[] { "w", "x", "y", "z" };
	}

	public static String[] getLetterCombinations(String digits) {
		if (digits == null || digits.length() == 0) {
			return new String[] {};
		}

		List<String> results = null;

		int n = digits.length();
		for (int i = 0; i < n; i++) {
			int digitNum = digits.charAt(i) - '0';

			List<String> newResults = new ArrayList<String>();

			String[] letters = DIGIT_TO_LETTERS[digitNum];

			if (results == null) {
				for (String letter : letters) {
					newResults.add(letter);
				}
			} else {
				for (String result : results) {
					for (String letter : letters) {
						newResults.add(result + letter);
					}
				}
			}

			results = newResults;
		}

		return results.toArray(new String[results.size()]);
	}

	public static void main(String[] args) {
		// Example:
		// Input: "23"
		// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
		String digits = "23";
		String expected = "[ad, ae, af, bd, be, bf, cd, ce, cf]";
		System.out.println("Input: " + digits);
		System.out.println("Expected: " + expected);

		String[] output = getLetterCombinations(digits);
		System.out.println("Output  : " + Arrays.toString(output));
	}

}
