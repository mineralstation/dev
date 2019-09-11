package LC387_first_unique_character_in_a_string;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {

	public static int getFirstUniqChar(String input) {
		if (input == null || input.isEmpty()) {
			return -1;
		}
		if (input.length() == 1) {
			return 0;
		}

		int result = -1;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			String s = String.valueOf(c);
			Integer count = map.get(s);
			if (count == null) {
				count = new Integer(0);
			}
			map.put(s, new Integer(count + 1));
		}

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			String s = String.valueOf(c);
			Integer count = map.get(s);
			if (count != null && count == 1) {
				result = i;
				break;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// Examples:
		// s = "leetcode"
		// return 0.
		// s = "loveleetcode",
		// return 2.
		{
			String input = "leetcode";
			System.out.println("Input: " + input);
			int output = getFirstUniqChar(input);
			System.out.println("Output: " + output);
		}

		{
			String input = "loveleetcode";
			System.out.println("Input: " + input);
			int output = getFirstUniqChar(input);
			System.out.println("Output: " + output);
		}
	}

}
