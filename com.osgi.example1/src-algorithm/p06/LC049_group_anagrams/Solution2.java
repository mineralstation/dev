package p06.LC049_group_anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution2 {

	/**
	 * 
	 * @param words
	 * @return
	 */
	public static List<List<String>> getAnagrams(String[] words) {
		List<List<String>> results = new ArrayList<List<String>>();
		if (words == null || words.length == 0) {
			return results;
		}

		Map<String, List<String>> keyToWordsMap = new TreeMap<String, List<String>>();

		for (String word : words) {
			char[] charArray = new char[26];
			char[] chars = word.toCharArray();
			for (char c : chars) {
				int index = c - 'a';
				charArray[index] = c;
			}

			String key = "";
			for (char c : charArray) {
				if ((int) c > 0) {
					key += String.valueOf(c) + "::";
				}
			}

			List<String> currWords = keyToWordsMap.get(key);
			if (currWords == null) {
				currWords = new ArrayList<String>();
				keyToWordsMap.put(key, currWords);
			}
			if (!currWords.contains(word)) {
				currWords.add(word);
			}
		}

		for (Iterator<String> itor = keyToWordsMap.keySet().iterator(); itor.hasNext();) {
			String key = itor.next();
			List<String> currWords = keyToWordsMap.get(key);
			results.add(currWords);
		}

		return results;
	}

	public static void main(String[] args) {
		String[] input = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> output = getAnagrams(input);

		System.out.println("Output:");
		System.out.println("[");
		for (List<String> words : output) {
			String str = Arrays.toString(words.toArray(new String[words.size()]));
			System.out.println("  " + str);
		}
		System.out.println("]");
		System.out.println();
	}

}
