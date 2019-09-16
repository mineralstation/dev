package dp;

import java.util.HashMap;
import java.util.Map;

public class FebSolution1 {

	public static int fibonacci(int n) {
		Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
		return fibonacci(n, memo);
	}

	public static int fibonacci(int n, Map<Integer, Integer> memo) {
		if (n == 1 || n == 2) {
			return 1;
		}
		int result = fibonacci(n - 2) + fibonacci(n - 1);
		memo.put(n, result);
		return result;
	}

	public static void main(String[] args) {
		String expected = "1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229,832040,";
		System.out.println("Expected: " + expected);
		String results = "";
		for (int i = 1; i <= 30; i++) {
			int result = fibonacci(i);
			results += result + ",";
		}
		System.out.println("Output:   " + results);
	}

}
