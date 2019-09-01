package LC304_range_sum_query_2d_immutable;

import java.util.Arrays;

public class Solution {

	protected int[][] sumMatrix;

	public Solution(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int M = matrix.length;
		int N = matrix[0].length;

		this.sumMatrix = new int[M][N];

		for (int i = 0; i < M; ++i) {
			for (int j = 0; j < N; ++j) {
				int area_top = (i - 1 >= 0) ? this.sumMatrix[i - 1][j] : 0;
				int area_left = (j - 1 >= 0) ? this.sumMatrix[i][j - 1] : 0;
				int area_overlap = (i - 1 >= 0 && j - 1 >= 0) ? this.sumMatrix[i - 1][j - 1] : 0;

				this.sumMatrix[i][j] = area_top + area_left - area_overlap + matrix[i][j];
			}
		}
	}

	public int sumRegion(int x1, int y1, int x2, int y2) {
		int sum_top = (x1 - 1 >= 0) ? this.sumMatrix[x1 - 1][y2] : 0;
		int sum_left = (y1 - 1 >= 0) ? this.sumMatrix[x2][y1 - 1] : 0;
		int sum_overlap = (x1 - 1 >= 0 && y1 - 1 >= 0) ? this.sumMatrix[x1 - 1][y1 - 1] : 0;
		return this.sumMatrix[x2][y2] - sum_top - sum_left + sum_overlap;
	}

	public static void main(String[] args) {
		int[][] input = new int[][] { // 0 1 2 3 4 5
				{ 3, 0, 1, 4, 2 }, // 0
				{ 5, 6, 3, 2, 1 }, // 1
				{ 1, 2, 0, 1, 5 }, // 2
				{ 4, 1, 0, 1, 7 }, // 3
				{ 1, 0, 3, 0, 5 } // 4
		};
		System.out.println("Input: {");
		System.out.println();
		for (int[] row : input) {
			System.out.println("  " + Arrays.toString(row));
		}
		System.out.println("}");
		System.out.println();

		Solution solution = new Solution(input);
		int output = solution.sumRegion(2, 1, 4, 3);
		System.out.println("Output: " + output);
	}

}
