package P99_99__LC_minimum_distance_in_arrays;

import java.util.Arrays;

/*
Examples :
Input : A[] = {1, 3, 15, 11, 2}
        B[] = {23, 127, 235, 19, 8} 
Output : 3  
That is, the pair (11, 8) 

Input : A[] = {l0, 5, 40}
        B[] = {50, 90, 80} 
Output : 10
That is, the pair (40, 50)

 */
public class Solution1 {

	public static int getMinDistance(int[] array1, int[] array2) {
		Arrays.sort(array1);
		Arrays.sort(array2);

		int m = array1.length;
		int n = array2.length;

		int minDist = Integer.MAX_VALUE;

		int i = 0;
		int j = 0;
		while (i < m && j < n) {
			int val1 = array1[i];
			int val2 = array2[j];

			int dist = Math.abs(val1 - val2);
			if (dist < minDist) {
				minDist = dist;
			}

			if (val1 < val2) {
				i++;
			} else {
				j++;
			}
		}

		return minDist;
	}

	public static void main(String[] args) {
		{
			int[] array1 = new int[] { 3, 5, 7 };
			int[] array2 = new int[] { 5, 9, 12 };
			int expected = 0;
			System.out.println("Input: ");
			System.out.println(Arrays.toString(array1));
			System.out.println(Arrays.toString(array2));
			int minDist = getMinDistance(array1, array2);
			System.out.println("Output: " + minDist + " //" + expected);
			System.out.println();
		}

		// Examples :
		// Input : A[] = {1, 3, 15, 11, 2}
		// B[] = {23, 127, 235, 19, 8}
		// Output : 3
		// That is, the pair (11, 8)
		//
		// Input : A[] = {10, 5, 40}
		// B[] = {50, 90, 80}
		// Output : 10
		// That is, the pair (40, 50)
		{
			int[] array1 = new int[] { 1, 3, 15, 11, 2 };
			int[] array2 = new int[] { 23, 127, 235, 19, 8 };
			int expected = 3;
			System.out.println("Input: ");
			System.out.println(Arrays.toString(array1));
			System.out.println(Arrays.toString(array2));
			int minDist = getMinDistance(array1, array2);
			System.out.println("Output: " + minDist + " //" + expected);
			System.out.println();
		}

		{
			int[] array1 = new int[] { 10, 5, 40 };
			int[] array2 = new int[] { 50, 90, 80 };
			int expected = 10;
			System.out.println("Input: ");
			System.out.println(Arrays.toString(array1));
			System.out.println(Arrays.toString(array2));
			int minDist = getMinDistance(array1, array2);
			System.out.println("Output: " + minDist + " //" + expected);
			System.out.println();
		}

		{
			int[] array1 = new int[] { 10, 20, 30, 110 };
			int[] array2 = new int[] { 100, 200, 300 };
			int expected = 10;
			System.out.println("Input: ");
			System.out.println(Arrays.toString(array1));
			System.out.println(Arrays.toString(array2));
			int minDist = getMinDistance(array1, array2);
			System.out.println("Output: " + minDist + " //" + expected);
			System.out.println();
		}
	}

}
