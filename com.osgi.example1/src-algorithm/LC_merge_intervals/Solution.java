package LC_merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

	public static class Interval {
		protected int start;
		protected int end;
	}

	/**
	 * 
	 * @param intervals
	 * @return
	 */
	public static List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1) {
			return intervals;
		}

		List<Interval> results = new ArrayList<Interval>();

		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});

		Interval tail = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);

			if (curr.start <= tail.end) {
				// can merge
				// - merge curr into tail; tail remains tail
				tail.end = curr.end;
			} else {
				// cannot merge; curr becomes new tail
				tail = curr;
			}
		}

		return results;
	}

}
