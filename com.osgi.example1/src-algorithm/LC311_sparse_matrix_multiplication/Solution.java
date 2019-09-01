package LC311_sparse_matrix_multiplication;

import java.util.Arrays;

/*

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

 */
public class Solution {

	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static int[][] multiply0(int[][] A, int[][] B) {
		int M = A.length;
		int K = B.length;
		int N = (K > 0) ? B[0].length : 0;

		int[][] result = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < K; k++) {
					int val1 = A[i][k];
					int val2 = B[k][j];
					result[i][j] += val1 * val2;
				}
			}
		}

		return result;
	}

	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static int[][] multiply(int[][] A, int[][] B) {
		int M = A.length;
		int K = B.length;
		int N = (K > 0) ? B[0].length : 0;

		int[][] result = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int k = 0; k < K; k++) {
				int val1 = A[i][k];
				if (val1 != 0) {
					for (int j = 0; j < N; j++) {
						int val2 = B[k][j];
						result[i][j] += val1 * val2;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] A = new int[][] { //
				{ 1, 0, 0 }, //
				{ -1, 0, 3 } //
		};

		int[][] B = new int[][] { //
				{ 7, 0, 0 }, //
				{ 0, 0, 0 }, //
				{ 0, 0, 1 } //
		};

		System.out.println("Input A = [");
		for (int i = 0; i < A.length; i++) {
			System.out.println("  " + Arrays.toString(A[i]) + ((i < A.length - 1) ? "," : ""));
		}
		System.out.println("]");
		System.out.println();

		System.out.println("Input B = [");
		for (int i = 0; i < B.length; i++) {
			System.out.println("  " + Arrays.toString(B[i]) + ((i < B.length - 1) ? "," : ""));
		}
		System.out.println("]");
		System.out.println();

		int[][] result1 = multiply0(A, B);
		int[][] result2 = multiply(A, B);

		System.out.println("Result 1:");
		for (int i = 0; i < result1.length; i++) {
			System.out.println(Arrays.toString(result1[i]));
		}
		System.out.println();

		System.out.println("Result 2:");
		for (int i = 0; i < result2.length; i++) {
			System.out.println(Arrays.toString(result2[i]));
		}
		System.out.println();
	}

}
