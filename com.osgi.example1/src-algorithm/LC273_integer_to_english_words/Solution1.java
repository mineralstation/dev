package LC273_integer_to_english_words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution1 {

	protected static String[] GROUPS = new String[] { "", "Thousand", "Million", "Billion", "Trillion" };
	protected static String[] ONES = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
	protected static String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static String toWords(int n) {
		if (n == 0) {
			return "Zero";
		}

		List<String> words = new ArrayList<String>();

		boolean isNegative = false;
		if (n < 0) {
			isNegative = true;
			n = -n;
		}

		int g = 0;
		while (n > 0) {
			int part1 = n / 1000;
			int part2 = n % 1000;

			if (g > 0) {
				words.add(GROUPS[g]);
			}
			g++;

			int part2a = part2 / 100;
			int part2b = part2 % 100;

			// last 2 digits
			if (part2b == 0) {
			} else if (part2b < 20) {
				words.add(ONES[part2b]);
			} else {
				int part2b1 = part2b / 10;
				int part2b2 = part2b % 10;
				if (part2b2 > 0) {
					words.add(ONES[part2b2]);
				}
				words.add(TENS[part2b1]);
			}

			if (part2a > 0) {
				words.add("Hundred");
				words.add(ONES[part2a]);
			}

			n = part1;
		}

		if (isNegative) {
			words.add("Negative");
		}
		Collections.reverse(words);

		return Arrays.toString(words.toArray(new String[words.size()]));
	}

	public static void main(String[] args) {
		System.out.println(toWords(1));
		System.out.println(toWords(12));
		System.out.println(toWords(123));
		System.out.println(toWords(1234));
		System.out.println(toWords(12345));
		System.out.println(toWords(123456));
		System.out.println(toWords(1234567));
		System.out.println(toWords(12345678));
		System.out.println(toWords(123456789));
	}

}
