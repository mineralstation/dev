package LC438_find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	public static class Window {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		boolean match = false;
		int length = 0;
		int matchedLength = 0;

		public Window(String p) {
			for (char c : p.toCharArray()) {
				this.map.put(c, new Integer(0));
			}
			this.length = this.map.size();
		}

		public void add(char c) {
			if (!this.map.containsKey(c)) {
				return;
			}

			int oldCount = this.map.get(c);
			int newCount = oldCount + 1;
			this.map.put(c, newCount);

			if (newCount == 1) {
				this.matchedLength++;
			}
			this.match = (this.matchedLength == this.length) ? true : false;
		}

		public void remove(char c) {
			if (!this.map.containsKey(c)) {
				return;
			}

			int oldCount = this.map.get(c);
			int newCount = oldCount - 1;
			if (newCount < 0) {
				newCount = 0;
			}
			this.map.put(c, newCount);

			if (newCount == 0) {
				this.matchedLength--;
			}
			this.match = (this.matchedLength == this.length) ? true : false;
		}

		public boolean match() {
			return this.match;
		}
	}

	/**
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static List<Integer> findAnagrams(String s, String p) {
		List<Integer> results = new ArrayList<Integer>();
		Window win = new Window(p);

		int lIndex = 0;
		int rIndex = p.length() - 1;

		for (int i = 0; i < rIndex; i++) {
			win.add(s.charAt(i));
		}

		while (rIndex < s.length()) {
			win.add(s.charAt(rIndex));
			if (win.match()) {
				results.add(lIndex);
			}
			win.remove(s.charAt(lIndex));
			lIndex++;
			rIndex++;
		}

		return results;
	}

	public static void main(String[] args) {
		{
			// Input:
			// s: "cbaebabacd" p: "abc"
			// Output:
			// [0, 6]
			String s = "cbaebabacd";
			String p = "abc";
			List<Integer> results = findAnagrams(s, p);
			System.out.println("s = " + s);
			System.out.println("p = " + p);
			System.out.println("results = " + Arrays.toString(results.toArray(new Integer[results.size()])));
		}

		{
			// Input:
			// s: "abab" p: "ab"
			// Output:
			// [0, 1, 2]
			String s = "abab";
			String p = "ab";
			List<Integer> results = findAnagrams(s, p);
			System.out.println("s = " + s);
			System.out.println("p = " + p);
			System.out.println("results = " + Arrays.toString(results.toArray(new Integer[results.size()])));
		}
	}

}
