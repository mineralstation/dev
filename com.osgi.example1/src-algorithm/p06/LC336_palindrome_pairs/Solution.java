package p06.LC336_palindrome_pairs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]

Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

 */
public class Solution {

	/**
	 * 
	 * @param words
	 * @return
	 */
	public static List<Integer[]> palindromePairs(String[] words) {
		List<Integer[]> results = new ArrayList<Integer[]>();

		int n = words.length;

		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		Set<Integer> lengthSet = new TreeSet<Integer>();
		for (int i = 0; i < n; ++i) {
			String word = words[i];
			wordMap.put(word, new Integer(i));
			lengthSet.add(word.length());
		}

		for (int i = 0; i < n; ++i) {
			String word = words[i];
			int len = word.length();

			word = reverse(word);

			Integer j = wordMap.get(word);
			if (j != null && j != i) {
				results.add(new Integer[] { i, j });
			}

			for (Iterator<String> itor = wordMap.keySet().iterator(); itor.hasNext();) {
				String currWord = itor.next();
				int k = wordMap.get(currWord);

				int currLen = currWord.length();
				if (len > currLen) {
					// abcdd -> ddcba
					// cba
					if (word.endsWith(currWord) && isPalindrome(word, 0, len - currLen)) {
						results.add(new Integer[] { i, k });
					}

					// ddabc -> cbadd
					// cba
					if (word.startsWith(currWord) && isPalindrome(word, currLen, len - 1)) {
						results.add(new Integer[] { k, i });
					}
				}
			}
		}

		return results;
	}

	/**
	 * 
	 * @param word
	 * @return
	 */
	protected static String reverse(String word) {
		StringBuilder sb = new StringBuilder();
		sb.append(word);
		sb.reverse();
		return sb.toString();
	}

	/**
	 * 
	 * @param word
	 * @param left
	 * @param right
	 * @return
	 */
	protected static boolean isPalindrome(String word, int left, int right) {
		while (left < right) {
			char c1 = word.charAt(left++);
			char c2 = word.charAt(right++);
			if (c1 != c2) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

	}

}
