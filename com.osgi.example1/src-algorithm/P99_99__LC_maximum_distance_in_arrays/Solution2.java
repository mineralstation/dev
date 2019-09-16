package P99_99__LC_maximum_distance_in_arrays;

import java.util.Arrays;

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
public class Solution2 {

	public static int getMaxDistance(int[][] arrays) {
		if (arrays == null || arrays.length <= 1) {
			return -1;
		}

		int maxDist = -1;

		int m = arrays.length;
		int minStart = Integer.MAX_VALUE;
		int maxEnd = Integer.MIN_VALUE;

		for (int i = 0; i < m; i++) {
			int[] array = arrays[i];
			int n = array.length;

			if (i == 0) {
				minStart = array[0];
				maxEnd = array[n - 1];

			} else {
				int dist1 = Math.abs(maxEnd - array[0]);
				int dist2 = Math.abs(array[n - 1] - minStart);
				if (dist1 > maxDist) {
					maxDist = dist1;
				}
				if (dist2 > maxDist) {
					maxDist = dist2;
				}

				if (array[0] < minStart) {
					minStart = array[0];
				}
				if (array[n - 1] > maxEnd) {
					maxEnd = array[n - 1];
				}
			}
		}

		return maxDist;
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
