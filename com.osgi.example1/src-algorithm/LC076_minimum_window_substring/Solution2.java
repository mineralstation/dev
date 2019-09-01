package LC076_minimum_window_substring;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

	public static class Window {
		protected Map<Character, Integer> map = new HashMap<Character, Integer>();
		protected boolean match = false;
		protected int length = 0;
		protected int matchedLength = 0;

		public Window(String T) {
			for (char c : T.toCharArray()) {
				this.map.put(c, new Integer(0));
			}
			this.length = map.size();
		}

		public int length() {
			return this.length;
		}

		protected boolean match() {
			return this.match;
		}

		protected void add(char c) {
			if (!this.map.containsKey(c)) {
				return;
			}

			int oldCount = this.map.get(c);
			int newCount = oldCount + 1;
			this.map.put(c, newCount);

			if (!this.match) {
				if (oldCount == 0) {
					this.matchedLength++;
				}
				if (this.matchedLength == this.length) {
					this.match = true;
				}
			}
		}

		protected void remove(char c) {
			if (!this.map.containsKey(c)) {
				return;
			}

			int oldCount = this.map.get(c);
			int newCount = oldCount - 1;
			if (newCount < 0) {
				newCount = 0;
			}
			this.map.put(c, newCount);

			if (this.match) {
				if (newCount == 0) {
					this.matchedLength--;
					if (this.matchedLength < 0) {
						this.matchedLength = 0;
					}
				}
				if (this.matchedLength < this.length) {
					this.match = false;
				}
			}
		}
	}

	/**
	 * 
	 * @param S
	 * @param T
	 * @return
	 */
	public static String minWindow(String S, String T) {
		String result = "";
		int minLength = Integer.MAX_VALUE;
		int leftIndex = 0;

		Window win = new Window(T);

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			win.add(c);

			while (win.match()) {
				int currLength = i - leftIndex + 1;
				if (currLength < minLength) {
					minLength = currLength;
					result = S.substring(leftIndex, i + 1);
				}

				win.remove(S.charAt(leftIndex));
				leftIndex++;
			}
		}

		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String S = "ADOBECODEBANC", T = "ABC";
		String result = minWindow(S, T);
		System.out.println("result = " + result);
	}

}
