package LC042_trapping_rain_water;

import java.util.Arrays;
import java.util.Stack;

public class Solution4 {

	/**
	 * 
	 * @param height
	 * @return
	 */
	public static int trap(int[] height) {
		int totalArea = 0;

		Stack<Integer> indexStack = new Stack<Integer>();

		int i = 0;
		while (i < height.length) {
			int currHeight = height[i];

			if (indexStack.isEmpty() || currHeight <= height[indexStack.peek()]) {
				indexStack.push(i);
				i++;

			} else {
				int rightHeight = currHeight;
				int bottomHeight = height[indexStack.pop()];
				if (!indexStack.isEmpty()) {
					int leftIndex = indexStack.peek();
					int leftHeight = height[leftIndex];

					int h = Math.min(leftHeight, rightHeight) - bottomHeight;
					int w = i - leftIndex - 1;

					int area = h * w;
					totalArea += area;
				}
			}
		}

		return totalArea;
	}

	public static void main(String[] args) {
		// {
		// // Given [1,0,1], return 1.
		// int[] height = new int[] { 1, 0, 1 };
		// System.out.println("Input: " + Arrays.toString(height));
		// int area = trap(height);
		// System.out.println("Output: " + area);
		// }

		{
			// Given [1,0,0,0,1], return 3.
			int[] height = new int[] { 1, 0, 0, 0, 1 };
			System.out.println("Input: " + Arrays.toString(height));
			int area = trap(height);
			System.out.println("Output: " + area);
		}

		// {
		// // Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
		// int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		// System.out.println("Input: " + Arrays.toString(height));
		// int area = trap(height);
		// System.out.println("Output: " + area);
		// }
	}

}
