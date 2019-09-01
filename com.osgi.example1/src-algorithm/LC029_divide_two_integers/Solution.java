package LC029_divide_two_integers;

public class Solution {

	/**
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static int divide(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MIN_VALUE;
		}

		int result = 0;

		boolean positive = ((dividend >= 0 && divisor >= 0) || (dividend < 0 && divisor < 0)) ? true : false;

		long available = dividend < 0 ? -dividend : dividend;
		long n = divisor < 0 ? -divisor : divisor;

		if (n == 0) {
			return Integer.MAX_VALUE;

		} else if (n == 1) {
			return (positive) ? dividend : -dividend;

		} else {
			// n > 1
			while (available >= n) {
				long value = n << 1; // double the n
				long times = 1; // total times of doubling
				while (available >= value) {
					times = times << 1;
					value = value << 1; // double the current value again
				}
				result += times;
				available -= value;
			}
			return positive ? result : -result;
		}
	}

	public static void main(String[] args) {
		// Integer.MIN_VALUE = -2147483648
		// -Integer.MIN_VALUE = -2147483648
		// Integer.MIN_VALUE/1 = -2147483648
		// Integer.MIN_VALUE/-1 = -2147483648
		// Integer.MIN_VALUE*1 = -2147483648
		// Integer.MIN_VALUE*-1 = -2147483648
		// System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
		// System.out.println("-Integer.MIN_VALUE = " + -Integer.MIN_VALUE);
		// System.out.println("Integer.MIN_VALUE/1 = " + (Integer.MIN_VALUE / 1));
		// System.out.println("Integer.MIN_VALUE/-1 = " + (Integer.MIN_VALUE / -1));
		// System.out.println("Integer.MIN_VALUE*1 = " + (Integer.MIN_VALUE * 1));
		// System.out.println("Integer.MIN_VALUE*-1 = " + (Integer.MIN_VALUE * -1));
		// System.out.println();

		// Integer.MAX_VALUE = 2147483647
		// -Integer.MAX_VALUE = -2147483647
		// Integer.MAX_VALUE/1 = 2147483647
		// Integer.MAX_VALUE/-1 = -2147483647
		// Integer.MAX_VALUE*1 = 2147483647
		// Integer.MAX_VALUE*-1 = -2147483647
		// System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
		// System.out.println("-Integer.MAX_VALUE = " + -Integer.MAX_VALUE);
		// System.out.println("Integer.MAX_VALUE/1 = " + Integer.MAX_VALUE / 1);
		// System.out.println("Integer.MAX_VALUE/-1 = " + Integer.MAX_VALUE / -1);
		// System.out.println("Integer.MAX_VALUE*1 = " + Integer.MAX_VALUE * 1);
		// System.out.println("Integer.MAX_VALUE*-1 = " + Integer.MAX_VALUE * -1);
		// System.out.println();

		// {
		// int input1 = 10;
		// int input2 = 2;
		// System.out.println("Input: " + input1 + " / " + input2);
		// int output = divide(input1, input2);
		// System.out.println("Output: " + output);
		// }

		// {
		// int input1 = 100;
		// int input2 = 2;
		// System.out.println("Input: " + input1 + " / " + input2);
		// int output = divide(input1, input2);
		// System.out.println("Output: " + output);
		// }

		{
			int input1 = 12;
			int input2 = 3;
			System.out.println("Input: " + input1 + " / " + input2);
			int output = divide(input1, input2);
			System.out.println("Output: " + output);
		}
	}

}
