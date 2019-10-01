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
public class Solution2 {

	public static int[] sortColors(int[] colors) {
		// red -> white -> blue
		int red = 0;
		int blue = colors.length - 1;

		for (int i = 0; i <= blue; i++) {
			if (colors[i] == 0) {
				swap(colors, i, red);
				red++;
			} else if (colors[i] == 2) {
				swap(colors, i, blue);
				blue--;
			}
		}

		return colors;
	}

	protected static void swap(int[] colors, int i, int j) {
		if (i == j) {
			return;
		}
		int tmp = colors[j];
		colors[j] = colors[i];
		colors[i] = tmp;
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
