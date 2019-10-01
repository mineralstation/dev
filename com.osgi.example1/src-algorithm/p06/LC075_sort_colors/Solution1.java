package p06.LC075_sort_colors;

import java.util.Arrays;

/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

 */
public class Solution1 {

	public static int[] sortColors(int[] colors) {
		int[] counts = new int[3];
		for (int color : colors) {
			counts[color]++;
		}

		boolean do1 = false;
		boolean do2 = true;

		if (do1) {
			// red
			int i = 0;
			for (; i < counts[0]; i++) {
				colors[i] = 0;
			}
			// white
			int j = i;
			for (; j < counts[0] + counts[1]; j++) {
				colors[j] = 1;
			}
			// blue
			int k = j;
			for (; k < counts[0] + counts[1] + counts[2]; k++) {
				colors[k] = 2;
			}
		}

		if (do2) {
			int index = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < counts[i]; j++) {
					colors[index++] = i;
				}
			}
		}

		return colors;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 2, 0, 2, 1, 1, 0 };
		System.out.println("Input:");
		System.out.println(Arrays.toString(input));

		int[] output = sortColors(input);
		System.out.println("Output:");
		System.out.println(Arrays.toString(output));
	}

}
