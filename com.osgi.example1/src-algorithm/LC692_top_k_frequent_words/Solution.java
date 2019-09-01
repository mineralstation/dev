package LC692_top_k_frequent_words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

	public static class Word {
		public String word;
		public int count;

		public Word(String word, int count) {
			this.word = word;
			this.count = count;
		}
	}

	public static List<String> topKFrequentWords(String[] words, int k) {
		// larger count first
		PriorityQueue<Word> queue = new PriorityQueue<Word>(new Comparator<Word>() {
			@Override
			public int compare(Word o1, Word o2) {
				if (o1.count == o2.count) {
					return o1.word.compareTo(o2.word);
				}
				return o2.count - o1.count;
			}
		});

		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		for (String word : words) {
			Integer count = wordCountMap.get(word);
			if (count == null) {
				count = new Integer(0);
			}
			wordCountMap.put(word, new Integer(count.intValue() + 1));
		}

		for (Iterator<String> itor = wordCountMap.keySet().iterator(); itor.hasNext();) {
			String word = itor.next();
			Integer count = wordCountMap.get(word);
			Word wordObj = new Word(word, count);
			queue.offer(wordObj);
		}

		List<String> results = new ArrayList<String>();
		int i = 0;
		while (!queue.isEmpty()) {
			Word word = queue.poll();
			results.add(word.word);
			i++;
			if (i == k) {
				break;
			}
		}
		return results;
	}

	/*
	 * Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2 Output: ["i", "love"] Explanation: "i" and "love" are the two most frequent
	 * words. Note that "i" comes before "love" due to a lower alphabetical order.
	 * 
	 * 
	 * Example 2: Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4 Output: ["the", "is", "sunny", "day"] Explanation:
	 * "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
	 * 
	 * 
	 * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements. Input words contain only lowercase letters.
	 * 
	 * 
	 * Follow up: Try to solve it in O(n log k) time and O(n) extra space. Can you solve it in O(n) time with only O(k) extra space?
	 */
	public static void main(String[] args) {
		{
			String[] input = new String[] { "i", "love", "leetcode", "i", "love", "coding" };
			int k = 2;
			System.out.println("input = " + Arrays.toString(input));
			System.out.println("k = " + k);
			List<String> result = topKFrequentWords(input, k);
			String resultStr = Arrays.toString(result.toArray(new String[result.size()]));
			System.out.println("result = " + resultStr);
		}

		{
			String[] input = new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
			int k = 4;
			System.out.println("input = " + Arrays.toString(input));
			System.out.println("k = " + k);
			List<String> result = topKFrequentWords(input, k);
			String resultStr = Arrays.toString(result.toArray(new String[result.size()]));
			System.out.println("result = " + resultStr);
		}
	}

}
