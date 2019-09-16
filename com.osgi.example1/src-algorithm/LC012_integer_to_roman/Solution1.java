package LC012_integer_to_roman;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.


Example 1:
Input: 3
Output: "III"

Example 2:
Input: 4
Output: "IV"

Example 3:
Input: 9
Output: "IX"

Example 4:
Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.

Example 5:
Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

 */
public class Solution1 {

	protected static String[] thousands = new String[] { "", "M", "MM", "MMM" };
	protected static String[] hundreds = new String[] { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
	protected static String[] tens = new String[] { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
	protected static String[] ones = new String[] { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

	/**
	 * 
	 * @param num
	 * @return
	 */
	public static String intToRoman(int num) {
		int d4 = num / 1000; // thousands

		num = num % 1000; // hundreds
		int d3 = num / 100;

		num = num % 100; // tens
		int d2 = num / 10;

		num = num % 10; // ones
		int d1 = num;

		String result = thousands[d4] + hundreds[d3] + tens[d2] + ones[d1];
		return result;
	}

	public static void main(String[] args) {
		// Example 1:
		// Input: 3
		// Output: "III"
		//
		// Example 2:
		// Input: 4
		// Output: "IV"
		//
		// Example 3:
		// Input: 9
		// Output: "IX"
		//
		// Example 4:
		// Input: 58
		// Output: "LVIII"
		// Explanation: L = 50, V = 5, III = 3.
		//
		// Example 5:
		// Input: 1994
		// Output: "MCMXCIV"
		// Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

		Map<Integer, String> inputMap = new LinkedHashMap<Integer, String>();
		inputMap.put(3, "III");
		inputMap.put(4, "IV");
		inputMap.put(9, "IX");
		inputMap.put(58, "LVIII");
		inputMap.put(1994, "MCMXCIV");

		for (Iterator<Integer> itor = inputMap.keySet().iterator(); itor.hasNext();) {
			int input = itor.next();
			String expected = inputMap.get(input);

			System.out.println("Input: " + input);
			String output = intToRoman(input);
			System.out.println("Output: " + output + " //" + expected);
		}
	}

}
