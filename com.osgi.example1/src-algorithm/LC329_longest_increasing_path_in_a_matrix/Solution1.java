package LC329_longest_increasing_path_in_a_matrix;

public class Solution1 {

	protected static int[][] DIRECTIONS = new int[][] { //
			{ -1, 0 }, // up
			{ 0, 1 }, // right
			{ 1, 0 }, // down
			{ 0, -1 }, // left
	};

	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public static int getLongestIncreasingPathLength(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int maxLength = 0;
		int m = matrix.length; // row num
		int n = matrix[0].length; // col num
		Integer[][] cache = new Integer[m][n];
		for (int i = 0; i < m; i++) {
			// row i
			for (int j = 0; j < n; j++) {
				// col j
				// use matrix(i,j) as current starting point
				int currMaxLength = walk(matrix, i, j, cache);
				if (currMaxLength > maxLength) {
					maxLength = currMaxLength;
				}
			}
		}
		return maxLength;
	}

	/**
	 * 
	 * @param matrix
	 * @param i
	 * @param j
	 * @param cache
	 * @return
	 */
	protected static int walk(int[][] matrix, int i, int j, Integer[][] cache) {
		if (cache[i][j] != null) {
			return cache[i][j];
		}
		int maxLength = 1;

		int m = matrix.length;
		int n = matrix[0].length;
		int currValue = matrix[i][j];

		for (int[] direction : DIRECTIONS) {
			int new_i = i + direction[0];
			int new_j = j + direction[1];
			if (0 <= new_i && new_i < m && 0 <= new_j && new_j < n) {
				int newValue = matrix[new_i][new_j];
				if (newValue > currValue) {
					int length = 1 + walk(matrix, new_i, new_j, cache);
					if (length > maxLength) {
						maxLength = length;
					}
				}
			}
		}

		cache[i][j] = Integer.valueOf(maxLength);
		return maxLength;
	}

	public static void main(String[] args) {
		{
			int[][] nums = new int[][] { //
					{ 9, 9, 4 }, //
					{ 6, 6, 8 }, //
					{ 2, 1, 1 } //
			};
			int result = getLongestIncreasingPathLength(nums);
			System.out.println("result = " + result);
		}

		{
			int[][] nums = new int[][] { //
					{ 3, 4, 5 }, //
					{ 3, 2, 6 }, //
					{ 2, 2, 1 } //
			};
			int result = getLongestIncreasingPathLength(nums);
			System.out.println("result = " + result);
		}
	}

}
