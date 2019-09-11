package LC269.alien_dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"

 */
public class Solution1 {

	public static class Pair {
		String s1;
		String s2;

		public Pair(String s1, String s2) {
			this.s1 = s1;
			this.s2 = s2;
		}

		@Override
		public int hashCode() {
			int result = 1;
			result = result * 17 + s1.hashCode();
			result = result * 17 + s2.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof Pair)) {
				return false;
			}
			Pair other = (Pair) obj;
			if (s1.equals(other.s1) && s2.equals(other.s2)) {
				return true;
			}
			return false;
		}
	}

	public static class Letter {
		String s;
		int incomingCount = 0;

		public Letter(String s) {
			this.s = s;
		}

		@Override
		public int hashCode() {
			return s.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof Letter)) {
				return false;
			}
			Letter other = (Letter) obj;
			if (s.equals(other.s)) {
				return true;
			}
			return false;
		}
	}

	public static List<String> alienOrder(List<String> words) {
		List<String> result = new ArrayList<String>();

		Map<String, Letter> lettersMap = new HashMap<String, Letter>();
		Set<Pair> pairs = new HashSet<Pair>();

		for (int i = 0; i < words.size(); i++) {
			String currWord = words.get(i);
			int len = currWord.length();
			for (int j = 0; j < len; j++) {
				String s = currWord.substring(j, j + 1);

				Letter letter = lettersMap.get(s);
				if (letter == null) {
					letter = new Letter(s);
					lettersMap.put(s, letter);
				}
			}
		}

		for (int i = 0; i < words.size() - 1; i++) {
			String currWord = words.get(i);
			String nextWord = words.get(i + 1);

			int len1 = currWord.length();
			int len2 = nextWord.length();
			int len = (len1 < len2) ? len1 : len2;

			for (int j = 0; j < len; j++) {
				String s1 = currWord.substring(j, j + 1);
				String s2 = nextWord.substring(j, j + 1);

				if (!s1.contains(s2)) {
					Pair pair = new Pair(s1, s2);
					pairs.add(pair);
				}
			}
		}

		for (Pair pair : pairs) {
			Letter letter = lettersMap.get(pair.s2);
			letter.incomingCount++;
		}

		Stack<String> stack = new Stack<String>();

		for (Iterator<String> itor = lettersMap.keySet().iterator(); itor.hasNext();) {
			String s = itor.next();
			Letter letter = lettersMap.get(s);

			if (letter.incomingCount == 0) {
				stack.push(s);
				result.add(s);
			}
		}

		while (!stack.isEmpty()) {
			String s = stack.pop();

			for (Pair pair : pairs) {
				if (s.equals(pair.s1)) {
					Letter letter = lettersMap.get(pair.s2);
					letter.incomingCount--;

					if (letter.incomingCount == 0) {
						stack.push(pair.s2);
						result.add(pair.s2);
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		/*
		 * Input:["wrt","wrf","er","ett","rftt"]
		 * 
		 * Output:"wertf"
		 */
		{
			List<String> input = new ArrayList<String>();
			input.add("wrt");
			input.add("wrf");
			input.add("er");
			input.add("ett");
			input.add("rftt");
			System.out.println("Input: " + Arrays.toString(input.toArray(new String[input.size()])));

			List<String> output = alienOrder(input);
			System.out.println("Output: " + Arrays.toString(output.toArray(new String[output.size()])));
		}

		/*
		 * Example 2: Input: [ "z", "x" ]
		 * 
		 * Output: "zx"
		 */
		{
			List<String> input = new ArrayList<String>();
			input.add("z");
			input.add("x");
			System.out.println("Input: " + Arrays.toString(input.toArray(new String[input.size()])));

			List<String> output = alienOrder(input);
			System.out.println("Output: " + Arrays.toString(output.toArray(new String[output.size()])));
		}

		/*
		 * Example 3: Input: [ "z", "x", "z" ]
		 * 
		 * Output: ""
		 */
		{
			List<String> input = new ArrayList<String>();
			input.add("z");
			input.add("x");
			input.add("z");
			System.out.println("Input: " + Arrays.toString(input.toArray(new String[input.size()])));

			List<String> output = alienOrder(input);
			System.out.println("Output: " + Arrays.toString(output.toArray(new String[output.size()])));
		}
	}

}
