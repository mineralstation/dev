package LC896_monotonic_array;

import java.util.Arrays;

public class Solution {

	/**
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isMonotone(int[] array) {
		Boolean isIncreasing = null;
		for (int i = 0; i < array.length - 1; i++) {
			int currValue = array[i];
			int nextValue = array[i + 1];

			if (currValue > nextValue) {
				if (isIncreasing == null) {
					isIncreasing = Boolean.TRUE;
				} else {
					if (!isIncreasing) {
						return false;
					}
				}
			} else if (currValue < nextValue) {
				if (isIncreasing == null) {
					isIncreasing = Boolean.FALSE;
				} else {
					if (isIncreasing) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// Example 1:
		//
		// Input: [1,2,2,3]
		// Output: true
		// Example 2:
		//
		// Input: [6,5,4,4]
		// Output: true
		// Example 3:
		//
		// Input: [1,3,2]
		// Output: false
		// Example 4:
		//
		// Input: [1,2,4,5]
		// Output: true
		// Example 5:
		//
		// Input: [1,1,1]
		// Output: true

		{
			int[] input = new int[] { 1, 2, 2, 3 };
			System.out.println("Input: " + Arrays.toString(input));
			boolean output = isMonotone(input);
			System.out.println("Output: " + output);
		}

		{
			int[] input = new int[] { 6, 5, 4, 4 };
			System.out.println("Input: " + Arrays.toString(input));
			boolean output = isMonotone(input);
			System.out.println("Output: " + output);
		}

		{
			int[] input = new int[] { 1, 3, 2 };
			System.out.println("Input: " + Arrays.toString(input));
			boolean output = isMonotone(input);
			System.out.println("Output: " + output);
		}

		{
			int[] input = new int[] { 1, 2, 4, 5 };
			System.out.println("Input: " + Arrays.toString(input));
			boolean output = isMonotone(input);
			System.out.println("Output: " + output);
		}

		{
			int[] input = new int[] { 1, 1, 1 };
			System.out.println("Input: " + Arrays.toString(input));
			boolean output = isMonotone(input);
			System.out.println("Output: " + output);
		}
	}

}
