package LC378_kth_smallest_element_in_a_sorted_matrix;

public class Solution2 {

	/**
	 * O(log(n) * n)
	 * 
	 * n = 64 -> 6 * 64
	 * 
	 * @param matrix
	 * @param n
	 * @param k
	 * @return
	 */
	public static int kthSmallest(int[][] matrix, int n, int k) {
		int low = matrix[0][0];
		int high = matrix[n - 1][n - 1];

		while (low < high) { // O(log(n))
			int mid = low + (high - low) / 2;
			int count = lessOrEqual(matrix, n, mid);

			if (count < k) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	/**
	 * O(n)
	 * 
	 * @param matrix
	 * @param n
	 * @param target
	 * @return
	 */
	protected static int lessOrEqual(int[][] matrix, int n, int target) {
		int count = 0;
		int i = n - 1; // start from last row
		int j = 0; // start from first column

		while (i >= 0 && j < n) {
			int value = matrix[i][j];
			if (value <= target) {
				count += i + 1; // all values in the upper part of the current column (number is i) + current cell (number is 1)
				j++;
			} else {
				i--;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		{
			int[][] matrix1 = new int[3][3];
			matrix1[0] = new int[] { 1, 100, 1000 };
			matrix1[1] = new int[] { 2, 101, 1001 };
			matrix1[2] = new int[] { 3, 102, 1002 };
			int k1 = 3;
			int result1 = kthSmallest(matrix1, 3, k1);
			System.out.println("result1 = " + result1);
		}

		{
			int[][] matrix2 = new int[3][3];
			matrix2[0] = new int[] { 1, 61, 91 };
			matrix2[1] = new int[] { 41, 65, 128 };
			matrix2[2] = new int[] { 178, 288, 335 };
			int k2 = 6;
			int result2 = kthSmallest(matrix2, 3, k2);
			System.out.println("result2 = " + result2);
		}
	}

}
