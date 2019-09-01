package LC238_product_of_array_except_self;

import java.util.Arrays;

public class Solution {

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static int[] productExceptSelf(int[] input) {
		int n = input.length;

		int[] forward = new int[n];
		int[] backward = new int[n];

		for (int i = 0; i < n; i++) {
			if (i == 0) {
				forward[i] = input[i];
			} else {
				forward[i] = forward[i - 1] * input[i];
			}
		}

		for (int j = n - 1; j >= 0; j--) {
			if (j == n - 1) {
				backward[j] = input[j];
			} else {
				backward[j] = backward[j + 1] * input[j];
			}
		}

		int[] result = new int[n];
		for (int x = 0; x < n; x++) {
			if (x == 0) {
				result[x] = backward[x + 1];
			} else if (x == n - 1) {
				result[x] = forward[x - 1];
			} else {
				result[x] = forward[x - 1] * backward[x + 1];
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 1, 2, 3, 4 };
		System.out.println("Input: " + Arrays.toString(input));
		int[] output = productExceptSelf(input);
		System.out.println("Output: " + Arrays.toString(output));
	}

}
