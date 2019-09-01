package LC076_minimum_window_substring;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * Input: S = "ADOBECODEBANC", T = "ABC" Output: "BANC" Note:
 * 
 * If there is no such window in S that covers all characters in T, return the empty string "". If there is such window, you are guaranteed that there will
 * always be only one unique minimum window in S.
 */
public class Solution1 {

	/**
	 * 
	 * @param S
	 * @param T
	 * @return
	 */
	public String minWindow(String S, String T) {
		String result = "";

		int sourceSize = S.length();
		int targetSize = T.length();

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int leftIndex = 0;
		int count = 0;
		int minLen = Integer.MAX_VALUE;

		// 因为要统计T串中字母的个数，而不是仅仅看某个字母是否在T串中出现。
		for (char c : T.toCharArray()) {
			int currCount = 0;
			if (map.containsKey(c)) {
				currCount = map.get(c);
			}
			currCount++;
			map.put(c, currCount);
		}

		for (int i = 0; i < sourceSize; ++i) {
			char c = S.charAt(i);

			// 说明当前遍历到的字母是T串中的字母，使用一个计数器 cnt，使其自增1。
			// 当 cnt 和T串字母个数相等时，说明此时的窗口已经包含了T串中的所有字母
			int currCount = 0;
			if (map.containsKey(c)) {
				currCount = map.get(c);
			}
			map.put(c, currCount - 1);
			if (currCount >= 0) {
				++count;
			}

			while (count == targetSize) {
				if (minLen > i - leftIndex + 1) {
					minLen = i - leftIndex + 1;
					result = S.substring(leftIndex, minLen);
				}

				char charAtLeft = S.charAt(leftIndex);
				int currCount2 = 0;
				if (map.containsKey(charAtLeft)) {
					currCount2 = map.get(charAtLeft);
				}
				currCount2++;
				map.put(charAtLeft, currCount2);

				// 此时如果加1后的值大于0了，说明此时少了一个T中的字母，那么 cnt 值就要减1了，然后移动左边界left。
				if (currCount2 > 0) {
					--count;
				}
				++leftIndex;
			}
		}
		return result;
	}

}
