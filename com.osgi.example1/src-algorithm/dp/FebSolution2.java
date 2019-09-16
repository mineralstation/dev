package dp;

public class FebSolution2 {

	public static int fibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		int[] fibArray = new int[n];
		fibArray[0] = 1;
		fibArray[1] = 1;

		for (int i = 2; i < n; i++) {
			fibArray[i] = fibArray[i - 2] + fibArray[i - 1];
		}

		return fibArray[n - 1];
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
