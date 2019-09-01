package LC347_top_k_frequent_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

	public static class Number {
		public int num;
		public int count;

		public Number(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	public static List<Integer> topKFrequentElement(int[] nums, int k) {
		Map<Integer, Integer> numToCountMap = new HashMap<Integer, Integer>();
		for (int num : nums) {
			Integer count = numToCountMap.get(num);
			if (count == null) {
				count = new Integer(0);
			}
			numToCountMap.put(num, new Integer(count.intValue() + 1));
		}

		// larger count first
		PriorityQueue<Number> heap = new PriorityQueue<Number>(new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				return o2.count - o1.count;
			}
		});

		for (Iterator<Integer> itor = numToCountMap.keySet().iterator(); itor.hasNext();) {
			Integer num = itor.next();
			Integer count = numToCountMap.get(num);
			Number number = new Number(num, count);
			heap.offer(number);
		}

		List<Integer> results = new ArrayList<Integer>();
		int i = 0;
		while (!heap.isEmpty()) {
			Number number = heap.poll();
			results.add(number.num);
			i++;
			if (i == k) {
				break;
			}
		}
		return results;
	}

	// Example 1:
	// Input: nums = [1,1,1,2,2,3], k = 2
	// Output: [1,2]

	// Example 2:
	// Input: nums = [1], k = 1
	// Output: [1]
	public static void main(String[] args) {
		{
			int[] input = new int[] { 1, 1, 1, 2, 2, 3 };
			int k = 2;
			System.out.println("input = " + Arrays.toString(input));
			System.out.println("k = " + k);
			List<Integer> result = topKFrequentElement(input, k);
			String resultStr = Arrays.toString(result.toArray(new Integer[result.size()]));
			System.out.println("result = " + resultStr);
		}

		{
			int[] input = new int[] { 1 };
			int k = 1;
			System.out.println("input = " + Arrays.toString(input));
			System.out.println("k = " + k);
			List<Integer> result = topKFrequentElement(input, k);
			String resultStr = Arrays.toString(result.toArray(new Integer[result.size()]));
			System.out.println("result = " + resultStr);
		}
	}

}
