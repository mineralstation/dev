package p07.LC057_insert_interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]

Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 */
public class Solution {

	public static List<Integer[]> insertInterval(List<Integer[]> intervals, Integer[] newInterval) {
		List<Integer[]> results = new ArrayList<Integer[]>();

		int n = intervals.size();

		int i = 0;
		while (i < n) {
			Integer[] currInterval = intervals.get(i);

			if (currInterval[1] < newInterval[0]) {
				// [curr] [new]; new is after curr; no overlap
				results.add(currInterval);
				i++;

			} else if (newInterval[1] < currInterval[0]) {
				// [new] [curr]; new is before curr; no overlap
				break;

			} else {
				// [new] and [curr] overlap
				int start = Math.min(currInterval[0], newInterval[0]);
				int end = Math.max(currInterval[1], newInterval[1]);
				newInterval = new Integer[] { start, end };
				i++;
			}
		}

		results.add(newInterval);

		while (i < n) {
			results.add(intervals.get(i));
			i++;
		}
		return results;
	}

	public static void main(String[] args) {
		// Example 1:
		// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
		// Output: [[1,5],[6,9]]
		{
			System.out.println("--- --- --- --- --- --- --- --- --- --- --- ---");
			List<Integer[]> intervals = new ArrayList<Integer[]>();
			intervals.add(new Integer[] { 1, 3 });
			intervals.add(new Integer[] { 6, 9 });
			Integer[] newInterval = new Integer[] { 2, 5 };
			List<Integer[]> results = insertInterval(intervals, newInterval);
			for (Integer[] result : results) {
				System.out.println(Arrays.toString(result));
			}
		}

		// Example 2:
		// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
		// Output: [[1,2],[3,10],[12,16]]
		{
			System.out.println("--- --- --- --- --- --- --- --- --- --- --- ---");
			List<Integer[]> intervals = new ArrayList<Integer[]>();
			intervals.add(new Integer[] { 1, 2 });
			intervals.add(new Integer[] { 3, 5 });
			intervals.add(new Integer[] { 6, 7 });
			intervals.add(new Integer[] { 8, 10 });
			intervals.add(new Integer[] { 12, 16 });
			Integer[] newInterval = new Integer[] { 4, 8 };
			List<Integer[]> results = insertInterval(intervals, newInterval);
			for (Integer[] result : results) {
				System.out.println(Arrays.toString(result));
			}
		}
	}

}
