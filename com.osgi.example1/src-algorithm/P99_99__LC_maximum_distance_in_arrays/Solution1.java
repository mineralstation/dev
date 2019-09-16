package P99_99__LC_maximum_distance_in_arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import common.Pair;

/*
Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays 
(each array picks one) and calculate the distance. We define the distance between two integers a and b to be their 
absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4

Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].

 */
public class Solution1 {

	public static int getMaxDistance(int[][] arrays) {
		if (arrays == null || arrays.length <= 1) {
			return -1;
		}

		PriorityQueue<Pair> minQueue = new PriorityQueue<Pair>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				return p1.value1 - p2.value1;
			}
		});

		PriorityQueue<Pair> maxQueue = new PriorityQueue<Pair>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				return p2.value1 - p1.value1;
			}
		});

		// log(m) * 2
		int max = -1;
		int m = arrays.length;
		for (int i = 0; i < m; i++) {
			int[] array = arrays[i];
			minQueue.add(new Pair(array[0], i));
			maxQueue.add(new Pair(array[array.length - 1], i));
		}

		// log(m) * 2
		Pair mainPair = minQueue.poll();
		Pair maxPair = maxQueue.poll();

		int firstMin = mainPair.value1;
		int index1 = mainPair.value2;
		int firstMax = maxPair.value1;
		int index2 = maxPair.value2;

		if (index1 != index2) {
			// min1 and max1 from different array
			max = firstMax - firstMin;

		} else {
			// min1 and max1 from same array
			int nextMin = minQueue.poll().value1;
			int nextMax = maxQueue.poll().value1;

			int dist1 = Math.abs(nextMax - firstMin);
			int dist2 = Math.abs(firstMax - nextMin);

			max = Math.max(dist1, dist2);
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] arrays = new int[3][];
		arrays[0] = new int[] { 1, 2, 3 };
		arrays[1] = new int[] { 4, 5 };
		arrays[2] = new int[] { 1, 2, 3 };
		int expected = 4;

		System.out.println("Input:");
		System.out.println("[");
		for (int[] array : arrays) {
			System.out.println("  " + Arrays.toString(array));
		}
		System.out.println("]");

		int output = getMaxDistance(arrays);
		System.out.println("Output: " + output + " //" + expected);
	}

}
